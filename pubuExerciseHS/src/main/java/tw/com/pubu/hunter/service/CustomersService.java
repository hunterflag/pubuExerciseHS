package tw.com.pubu.hunter.service;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.enums.LoginResult;

public interface CustomersService {
	public LoginResult login(String account, String password);
	public int getIdByAccount(String account);
	public CustomersBean getById(Integer id);
	public CustomersBean getById(int id);

}
