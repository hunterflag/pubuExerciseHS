package tw.com.pubu.hunter.service.impl;

import javax.servlet.http.HttpSession;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.enums.LoginResult;
import tw.com.pubu.hunter.service.CustomersService;
import tw.idv.hunter.tool.HunterDebug;

public class CustomersServiceImpl implements CustomersService {

	@Override
	public LoginResult login(String account, String password) {
		LoginResult result = LoginResult.Error;
		CustomersDao dao = new CustomersDaoImpl();
		
		//取得傳入值、確認有值
		HunterDebug.showKeyValue("login account", account);
		HunterDebug.showKeyValue("login password", password);
		if(account.isEmpty() || password.isEmpty()) return result;
//		if (account.equals("")) result = LoginResult.AccountIsEmpty;
//		else if (password.equals("")) result = LoginResult.AccountIsEmpty;

		//從資料庫內找出帳號資料
		CustomersBean bean = dao.getByAccount(account);
		if(bean!=null) {									//帳號存在
			if (password.equals(bean.getCtm_password())) {	//密碼相同
				result = LoginResult.OK;
			} else {											
				result = LoginResult.PasswordNotCorrect;
			}
		} else {
			result = LoginResult.AccountNotExist;
		}
		return result;
	}

}
