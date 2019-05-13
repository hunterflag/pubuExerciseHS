package tw.com.pubu.hunter.service.impl;

import java.util.List;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartsDaoImpl;
import tw.com.pubu.hunter.service.ShoppingCartsService;

public class ShoppingCartsServiceImpl implements ShoppingCartsService {

	@Override
	public int add(int memberId, int productId){
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		//項目已存在, 不再重複加入
		if(dao.isItemExist(memberId, productId)) return 0;
		
		//項目不存在, 新增項目
		CustomersBean cBean = new CustomersDaoImpl().getById(memberId);
		ProductsBean pBean = new ProductsDaoImpl().getById(productId);
		double price = (int)(pBean.getPd_price() / 100) * 100;
		ShoppingCartsBean newObj = new ShoppingCartsBean(cBean, pBean, price);
		Object pk = dao.insert(newObj);
		
		return Integer.valueOf(pk.toString());
	}

	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId){
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		List<ShoppingCartsBean> list = dao.getItemsByCustomer(ctmId);
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
