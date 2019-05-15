package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;

public interface ShoppingCartsService {
	public int add(int memberId, int productId);
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId);
	public boolean removeById(int sc_id);
	public int clearByCustomer(int ctmId);
	public boolean updateNumberOfItem(int sc_id, int newNumber);
	public int ConfirmToOrder(int ctmId);
//	public boolean isItemExist(int memberId, int productId);
//	public int confirmToOrder(int memberId);
//	public boolean isAccExist(String account);
//	public MemberBean getByAcc(String account);
//	public MemberBean getByNo(int no);


}
