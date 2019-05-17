package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.OrdersBean;

public interface OrdersService {
	public List<OrdersBean> getAllsByCustomer(int ctmId);
	public List<OrdersBean> getAlls();
}
