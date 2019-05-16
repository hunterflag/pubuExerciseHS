package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderDetailsBean;

public interface OrderDetailsService {
	public List<OrderDetailsBean> getAllsById(int od_id);
//	public List<OrderDetailsBean> getAlls();
//	public ProductsBean getById(int id);
//	public int add(String name, Double price);
//	public boolean delete(String account);
//	public boolean isAccExist(String account);
//	public MemberBean getByAcc(String account);
//	public boolean update(MemberBean mb);

}
