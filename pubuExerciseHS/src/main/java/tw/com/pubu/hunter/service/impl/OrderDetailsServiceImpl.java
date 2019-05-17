package tw.com.pubu.hunter.service.impl;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.dao.impl.OrderDetailsDaoImpl;
import tw.com.pubu.hunter.service.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Override
	public List<OrderDetailsBean> getAllsById(int od_id){
		List<OrderDetailsBean> list = null;
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		list = dao.getAllsById(od_id);
		return list;
	}
}
