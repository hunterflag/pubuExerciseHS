package tw.com.pubu.hunter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.service.OrderDetailsService;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsDao dao;

	@Override
	public List<OrderDetailsBean> getAllsById(int od_id) {
		List<OrderDetailsBean> list = null;
		list = dao.getAllsById(od_id);
		return list;
	}
}
