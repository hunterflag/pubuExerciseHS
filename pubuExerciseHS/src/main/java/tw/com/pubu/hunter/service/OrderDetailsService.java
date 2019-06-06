package tw.com.pubu.hunter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;

@Service
@Transactional
public class OrderDetailsService{
	@Autowired
	private OrderDetailsDao dao;

	public List<OrderDetailsBean> getAllsById(int od_id) {
		List<OrderDetailsBean> list = null;
		list = dao.getAllsById(od_id);
		return list;
	}
}
