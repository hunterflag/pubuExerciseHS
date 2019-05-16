package tw.com.pubu.hunter.service.impl;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.dao.impl.OrderDetailsDaoImpl;
import tw.com.pubu.hunter.service.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Override
	public List<OrderDetailsBean> getAllsById(int od_id){
		List<OrderDetailsBean> list = null;
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		list = dao.getAllsById(od_id);
//		for(OrdersBean element : list)
//			System.out.println(element);
		return list;
	}
	
//	@Override
//	public List<OrdersBean> getAlls(){
//		List<OrdersBean> list = null;
//		OrdersDao dao = new OrdersDaoImpl();
//		list = dao.getAlls();
////		for(OrdersBean element : list)
////			System.out.println(element);
//		return list;
//	}

//	
//	public OrdersBean getById(int id) {
//		OrdersBean bean=null;
//		OrdersDao dao = new OrdersDaoImpl();
//		bean = dao.getById(id);
//		return bean;
//	}
//	
	//	
//	public int add(String name, Double price) {
//		OrdersDao dao = new OrdersDaoImpl();
//		OrdersBean newObj = new OrdersBean(name, price);
//		Object pk = dao.insert(newObj);
//		int newPk = Integer.valueOf(newObj.getPk().toString());
//		
//		return newPk;
//	}

	
/*	
	@SuppressWarnings("unchecked")
	public boolean isAccExist(String account) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<ProductBean> query = session.createQuery(qryStr);
			query.setParameter("account", account);
			List<ProductBean> list = query.getResultList();
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
	public ProductBean getByAcc(String qryAcc) {
		ProductBean result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<ProductBean> query = session.createQuery(qryStr);
			query.setParameter("account", qryAcc);
			result = (ProductBean) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
*/
}
