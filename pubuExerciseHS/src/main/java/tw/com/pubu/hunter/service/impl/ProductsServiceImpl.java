package tw.com.pubu.hunter.service.impl;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.com.pubu.hunter.service.ProductsService;

public class ProductsServiceImpl implements ProductsService {

	@Override
	public List<ProductsBean> getAlls(){
		List<ProductsBean> list = null;
		ProductsDao dao = new ProductsDaoImpl();
		list = dao.getAlls();
//		for(ProductsBean element : list)
//			System.out.println(element);
		return list;
	}

	
	public ProductsBean getById(int id) {
		ProductsBean bean=null;
		ProductsDao dao = new ProductsDaoImpl();
		bean = dao.getById(id);
		return bean;
	}
	
	//	
//	public int add(String name, Double price) {
//		ProductsDao dao = new ProductsDaoImpl();
//		ProductsBean newObj = new ProductsBean(name, price);
//		Object pk = dao.insert(newObj);
//		int newPk = Integer.valueOf(newObj.getPk().toString());
//		
//		return newPk;
//	}

	
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
		HunterDebug.traceMessage();
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
