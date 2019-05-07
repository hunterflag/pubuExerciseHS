package dao;

import java.util.List;

import bean.Customers;

public interface CustomersDao {
	public boolean isAccExist(String account);
	public Customers getByAcc(String account);
	public Customers getById(int id);
	public List<Customers> getAlls();
	
}
