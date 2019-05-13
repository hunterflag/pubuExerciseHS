package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;

public interface ShoppingCartsService {
	public int add(int memberId, int productId);
//	public boolean isItemExist(int memberId, int productId);
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId);
//	public int confirmToOrder(int memberId);
//	public boolean delete(int ctmId);
//	public boolean isAccExist(String account);
//	public MemberBean getByAcc(String account);
//	public MemberBean getByNo(int no);
//	public boolean update(ShoppingCartsBean updObj);


}
