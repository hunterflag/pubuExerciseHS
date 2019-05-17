package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.utils.HibernateUtils;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
	SessionFactory factory;
	
	public OrderDetailsDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(OrderDetailsBean insObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		
		try {
			tx = session.beginTransaction();
			key = session.save(insObj);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}

	@Override
	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean) {
		Object key = null;
		OrderDetailsBean insObj = new OrderDetailsBean(pdtBean, number, price, odBean);
		key = insert(insObj);
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetailsBean> getAllsById(int od_id){
		List<OrderDetailsBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			String qryHqlStr = "FROM OrderDetailsBean AS oddtb WHERE oddtb.odBean.od_id = :od_id";
			Query<OrderDetailsBean> query = session.createQuery(qryHqlStr);
			query.setParameter("od_id", od_id);
			result = query.getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
}
