package dao;

import java.util.List;

import model.CustomersBean;

public interface CustomersBeanDao {
//	public Object add(CustomersBean ctmb);
	public CustomersBean get(int id);
//	public void update(CustomersBean ctmb);
//	public void delete(CustomersBean ctmb);
//	public Object save(CustomersBean ctmb);
//	public CustomersBean load(int id);
	public List<CustomersBean> getAlls();
	
}
