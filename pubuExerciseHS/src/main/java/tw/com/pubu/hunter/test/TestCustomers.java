package tw.com.pubu.hunter.test;

import tw.com.pubu.hunter.enums.LoginResult;
import tw.com.pubu.hunter.service.CustomersService;
import tw.com.pubu.hunter.service.impl.CustomersServiceImpl;
import tw.idv.hunter.tool.HunterDebug;

public class TestCustomers {

	public static void main(String[] args) {
		String account=
				"Tester1"
//				"Tester"
//				""
				;
		String password=
				"123456"
//				"1234"
//				""
				;
		
		CustomersService service = new CustomersServiceImpl();
		HunterDebug.showKeyValue("Result: ", service.login(account, password).toString());
			
	}

}
