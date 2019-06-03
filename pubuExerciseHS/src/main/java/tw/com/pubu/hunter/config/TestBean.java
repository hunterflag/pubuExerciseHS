package tw.com.pubu.hunter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.idv.hunter.tool.HunterDebug;

@Configuration
public class TestBean {

	public TestBean() {
		super();
		HunterDebug.traceMessage();
	}
	@Bean(name="customers2")
	public CustomersBean customersBean() {
		return new CustomersBean();
	}
}
