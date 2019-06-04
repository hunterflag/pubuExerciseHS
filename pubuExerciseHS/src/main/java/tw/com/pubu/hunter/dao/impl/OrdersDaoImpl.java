package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.utils.HibernateUtils;

@Repository
public class OrdersDaoImpl implements OrdersDao {
	SessionFactory factory;
	
	public OrdersDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(OrdersBean insObj) {
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
	public Object insert(int ctm_id) {
		CustomersDao ctmDao = new CustomersDaoImpl();
		CustomersBean ctmBean = ctmDao.getById(ctm_id);
		
		OrdersBean insObj = new OrdersBean(ctmBean); 
		Object key = insert(insObj);
		return key;
	}
	
	@Override
	public boolean update(int od_id, int od_total_price) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			OrdersBean bean = session.get(OrdersBean.class, od_id);
			bean.setOd_total_price(od_total_price);
			bean.setOd_state("close");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAllsByCustomer(int ctmId){
		List<OrdersBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			String qryHqlStr = "FROM OrdersBean AS ob WHERE ob.ctmBean.ctm_id = :ctmId";
			Query<OrdersBean> query = session.createQuery(qryHqlStr);
			query.setParameter("ctmId", ctmId);
			result = query.getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAlls(){
		List<OrdersBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM Orders")
							.getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}

	
}
