package tw.com.pubu.hunter.dao;

import tw.com.pubu.hunter.bean.CustomersBean;

public interface CustomersDao {
	public CustomersBean getByAccount(String account);
	public boolean isAccountExist(String account);


}
