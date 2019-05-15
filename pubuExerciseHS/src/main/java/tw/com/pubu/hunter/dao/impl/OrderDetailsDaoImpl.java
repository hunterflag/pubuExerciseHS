package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.dao.OrdersDao;
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

	
/*
	@Override
	public boolean update(int od_id, int od_total_price) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			OrdersBean bean = session.get(OrdersBean.class, od_id);
			bean.setOd_total_price(od_total_price);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	@Override
	public OrdersBean getById(Integer od_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		OrdersBean result = null;
		
		try {
			tx = session.beginTransaction();
			result = session.get(OrdersBean.class, od_id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public OrdersBean getById(int od_id) {
		OrdersBean result = null;
		result = getById(Integer.valueOf(od_id));
		return result;
	}

	
*/	
}
