package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.ShoppingCartBean;

public interface ShoppingCartService {
	public int add(int memberId, int productId, int number);
	public List<ShoppingCartBean> getItemsByMember(int memberId);
//	public int confirmToOrder(int memberId);
//	public boolean delete(String account);
//	public boolean isAccExist(String account);
//	public MemberBean getByAcc(String account);
//	public MemberBean getByNo(int no);
//	public boolean update(MemberBean mb);


}
