package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderDetailsBean;

public interface OrderDetailsService {
	public List<OrderDetailsBean> getAllsById(int od_id);
}
