package tw.com.pubu.hunter.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.pubu.hunter.bean.TestEntity;
import tw.com.pubu.hunter.config.TestEntityBeanConfig;
import tw.idv.hunter.tool.HunterDebug;

@PropertySource("test.properties")
public class TestEntityMain {

	public static void main(String[] args) {
		HunterDebug.traceMessage();
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("TestEntityBeans.xml");
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(TestEntityBeanConfig.class);
		ctx.refresh();
		
		TestEntity testEntity = ctx.getBean(TestEntity.class);
//		TestEntity testEntity = (TestEntity) ctx.getBean("testEntity");
//		TestEntity testEntity = ctx.getBean("testEntity");

		
		HunterDebug.showKeyValue("TestEntity", testEntity.toString());
		HunterDebug.showKeyValue("TestEntity.fieldName1", testEntity.getFieldName1());
		HunterDebug.showKeyValue("TestEntity.fieldName2", testEntity.getFieldName2());
		
		ctx.close();
//		((ConfigurableApplicationContext)ctx).close();

	}

}
