package tw.com.pubu.hunter.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import tw.idv.hunter.tool.HunterDebug;

@Configuration
@EnableTransactionManagement
public class RootAppConfig {
	//DataSource 資料庫連線資訊
	@Bean
	public DataSource dataSource() {
		HunterDebug.traceMessage();
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setUser("root");
		ds.setPassword("123456");
		try {
			ds.setDriverClass("com.mysql.cj.jdbc.Driver");
		}catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/pubu_exercise?useSSL=false&useUnicode=yes&characterEncoding=utf8&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true");
		ds.setInitialPoolSize(4);
		ds.setMaxPoolSize(8);
		return ds;
	}
		
	//從 Bean dataSource, 建立 Hibernate 的 SessionFactory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		HunterDebug.traceMessage();
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());	// 這裡是呼叫, 不是由容器產生
		factory.setPackagesToScan(new String[]{		// Hibernate 掃描 Entity
					"tw.com.pubu.hunter"
				});
		factory.setHibernateProperties(additionalProperties());
		HunterDebug.showKeyValue("factory:", factory.toString());
		return factory;
	}	
	private Properties additionalProperties() {
		HunterDebug.traceMessage();
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.MySQL8Dialect.class);
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		properties.put("default_batch_fetch_size", 10);
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
	@Bean(name="transactionManager")
	@Autowired								// sessionFactory 由前一個 Bean 類別, 產生物件後, 注入至此
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HunterDebug.traceMessage();
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	
	
	
}
