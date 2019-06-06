package tw.com.pubu.hunter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;

@Repository
public class OrderDetailsDao{
	
	@PersistenceContext
	private EntityManager session;
	
	public Object insert(OrderDetailsBean insObj) {
		Object key = null;
		session.persist(insObj);
		key = (Object) insObj.getOddt_id(); 
		return key;
	}

	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean) {
		Object key = null;
		OrderDetailsBean insObj = new OrderDetailsBean(pdtBean, number, price, odBean);
		key = insert(insObj);
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDetailsBean> getAllsById(int od_id) {
		List<OrderDetailsBean> result = null;
		String qryHqlStr = "FROM OrderDetailsBean AS oddtb WHERE oddtb.odBean.od_id = :od_id";
		Query query = session.createQuery(qryHqlStr);
		query.setParameter("od_id", od_id);
		result = query.getResultList();
		return result;
	}
}
