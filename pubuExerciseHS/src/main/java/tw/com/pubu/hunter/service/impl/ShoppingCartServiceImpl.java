package tw.com.pubu.hunter.service.impl;

import java.util.List;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.bean.ProductBean;
import tw.com.pubu.hunter.bean.ShoppingCartBean;
import tw.com.pubu.hunter.dao.ShoppingCartDao;
import tw.com.pubu.hunter.dao.impl.MemberDaoImpl;
import tw.com.pubu.hunter.dao.impl.ProductDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartDaoImpl;
import tw.com.pubu.hunter.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public int add(int memberId, int productId, int number){
		MemberBean mb = new MemberDaoImpl().getByPk(memberId);
		ProductBean pb = new ProductDaoImpl().getByPk(productId);
		
		ShoppingCartDao dao = new ShoppingCartDaoImpl();
		double price = (int)(pb.getPrice() / 100) * 100;
		ShoppingCartBean newObj = new ShoppingCartBean(mb, pb, number, price);
		Object pk = dao.insert(newObj);
		int newPk = Integer.valueOf(newObj.getPk().toString());
		
		return newPk;
	}

	public List<ShoppingCartBean> getItemsByMember(int memberId){
		List<ShoppingCartBean> list = null;
		
		return list;
	}
//	public int confirmToOrder(int memberId) {
//		int number = 0;	//訂購商品種類數
//		//訂單 1: 先建立訂單編號、取得新增訂單的 oid
//		//取出會員的購物車內容
//		//依購物車內容, 建立訂購明細表 & 計算總價
//		//訂單2：更新總價
//		//移除購物車內容
//		
//		return number;
//	}
//	
/*	
	@SuppressWarnings("unchecked")
	public boolean isAccExist(String account) {
		HunterDebug.traceMessage();
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<ShoppingCartBean> query = session.createQuery(qryStr);
			query.setParameter("account", account);
			List<ShoppingCartBean> list = query.getResultList();
			if (list.size() > 0) result = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			session.clear();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShoppingCartBean getByAcc(String qryAcc) {
		HunterDebug.traceMessage();
		ShoppingCartBean result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<ShoppingCartBean> query = session.createQuery(qryStr);
			query.setParameter("account", qryAcc);
			result = (ShoppingCartBean) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
*/
}
