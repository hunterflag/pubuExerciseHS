package tw.com.pubu.hunter.dao;

import java.util.Set;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;

public interface CustomersDao {
	public CustomersBean getByAccount(String account);
	public boolean isAccountExist(String account);
	public int getIdByAccount(String account);
	public CustomersBean getById(Integer id);
	public CustomersBean getById(int id);
	public Set<ShoppingCartsBean> getItemsFromShoppingCartByCustomerId(int id);
}
