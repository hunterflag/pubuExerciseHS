package tw.com.pubu.hunter.dao;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;

public interface OrderDetailsDao {
	public Object insert(OrderDetailsBean insObj);
	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean);
//	public OrderDetailsBean getById(Integer id);
//	public OrderDetailsBean getById(int id);
//	public List<OrdersBean> getAllsByCustomer(int ctmId);
//	public List<OrdersBean> getAlls();

}
