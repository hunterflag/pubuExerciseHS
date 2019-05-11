package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderBean;

public interface OrderDao {
	public Object insert(OrderBean insObj);
	public boolean delete(OrderBean delObj);
	public OrderBean getByPk(Integer pk);
	public OrderBean getByPk(int ipk);
	public List<OrderBean> getAlls();
	public boolean update(OrderBean updObj);
}
