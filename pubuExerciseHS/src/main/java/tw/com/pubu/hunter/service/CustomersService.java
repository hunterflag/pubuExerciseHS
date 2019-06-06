package tw.com.pubu.hunter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.enums.LoginResult;

@Service
@Transactional
public class CustomersService{
	@Autowired
	private CustomersDao dao;
	
	public LoginResult login(String account, String password) {
		LoginResult result = LoginResult.Error;
		
		//取得傳入值、確認有值
		if(account.isEmpty() || password.isEmpty()) return result;

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
		id = dao.getIdByAccount(account);
		return id;
	}
}
