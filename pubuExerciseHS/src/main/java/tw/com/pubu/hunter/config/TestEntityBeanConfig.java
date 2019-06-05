package tw.com.pubu.hunter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.TestEntity;
import tw.idv.hunter.tool.HunterDebug;

@Configuration
public class TestEntityBeanConfig {

	public TestEntityBeanConfig() {
		super();
		HunterDebug.traceMessage();
	}
	
	@Bean(name="testEntityConfig11")
	public TestEntity testEntity1() {
		HunterDebug.traceMessage();
		
		return new TestEntity("configName11");
	}

	@Bean(name="testEntityConfig12")
	public TestEntity testEntity2() {
		HunterDebug.traceMessage();
		
		return new TestEntity("configName11", "configName12");
	}
}
