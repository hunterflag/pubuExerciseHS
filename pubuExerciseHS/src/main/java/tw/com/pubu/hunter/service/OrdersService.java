package tw.com.pubu.hunter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.service.OrdersService;

@Service
public class OrdersService{
	@Autowired
	private OrdersDao dao;

	@Transactional
	public List<OrdersBean> getAllsByCustomer(int ctmId) {
		List<OrdersBean> list = null;
		list = dao.getAllsByCustomer(ctmId);
		return list;
	}

	@Transactional
	public List<OrdersBean> getAlls() {
		List<OrdersBean> list = null;
		list = dao.getAlls();
		return list;
	}

}
