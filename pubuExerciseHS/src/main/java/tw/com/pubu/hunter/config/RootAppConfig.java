package tw.com.pubu.hunter.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import tw.idv.hunter.tool.HunterDebug;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("tw.com.pubu.hunter")	//Spring JPA 掃描 @Repository
public class RootAppConfig {
	//DataSource 資料庫連線資訊, Hibernate、Spring JPA 皆會用到 
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

	 /************* Start Spring JPA config details **************/
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("example");		//XXX 源頭本在 /META-INF/persistence.xml 檔內, 但這裡應該沒用了!
        lcemfb.setPackagesToScan("tw.com.pubu.hunter");	//掃描 Bean
        lcemfb.setJpaProperties(hibernateProperties());
        return lcemfb;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }


    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
            getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
    
    /************* End Spring JPA config details **************/
    
    
    /************* Start Hibernate config details **************
	//從 Bean dataSource, 建立 Hibernate 的 SessionFactory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		HunterDebug.traceMessage();
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());	// 這裡是呼叫, 不是由容器產生
		factory.setPackagesToScan(new String[]{		// Hibernate 掃描 Entity
					"tw.com.pubu.hunter"
				});
		factory.setHibernateProperties(hibernateProperties());
		return factory;
	}	
	 
	@Bean(name="transactionManager")
	@Autowired								// sessionFactory 由前一個 Bean 類別, 產生物件後, 注入至此
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HunterDebug.traceMessage();
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	************* End Hibernate config details **************/
	

	// Hibernate 設定參數, 也是  Hibernate、Spring JPA 皆會用到
	private Properties hibernateProperties() {
		HunterDebug.traceMessage();
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.MySQL8Dialect.class);
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		properties.put("default_batch_fetch_size", 10);
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
	
}
