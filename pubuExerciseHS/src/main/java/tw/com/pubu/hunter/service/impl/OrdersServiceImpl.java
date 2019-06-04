package tw.com.pubu.hunter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.dao.impl.OrdersDaoImpl;
import tw.com.pubu.hunter.service.OrdersService;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

	@Override
	public List<OrdersBean> getAllsByCustomer(int ctmId){
		List<OrdersBean> list = null;
		OrdersDao dao = new OrdersDaoImpl();
		list = dao.getAllsByCustomer(ctmId);
		return list;
	}
	@Override
	public List<OrdersBean> getAlls(){
		List<OrdersBean> list = null;
		OrdersDao dao = new OrdersDaoImpl();
		list = dao.getAlls();
		return list;
	}

}
