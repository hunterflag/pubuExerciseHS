package tw.com.pubu.hunter.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.enums.LoginResult;
import tw.com.pubu.hunter.service.CustomersService;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Override
	@Transactional
	public LoginResult login(String account, String password) {
		LoginResult result = LoginResult.Error;
		CustomersDao dao = new CustomersDaoImpl();
		
		//取得傳入值、確認有值
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

	public int getIdByAccount(String account) {
		int id=0;
		CustomersDao dao = new CustomersDaoImpl();
		id = dao.getIdByAccount(account);
		return id;
	}
}
