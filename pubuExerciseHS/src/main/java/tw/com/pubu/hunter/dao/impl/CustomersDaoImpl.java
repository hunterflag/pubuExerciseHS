package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import util.HibernateUtils;

public class CustomersDaoImpl implements CustomersDao {
	SessionFactory factory;
	public CustomersDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	/**
	 * @author chenhuanzhang
	 * @return null 表示無此帳號
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CustomersBean getByAccount(String account) {
		CustomersBean result = null;
		//輸入資料檢查
		if(account.isEmpty()) return result;			//沒輸入
		if (!isAccountExist(account)) return result;	//無此帳號
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<CustomersBean> query = session.createQuery(qryHqlStr);
			query.setParameter("account", account);
			result = (CustomersBean) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean isAccountExist(String account) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<CustomersBean> query = session.createQuery(qryHqlStr);
			query.setParameter("account", account);
			List<CustomersBean> list = query.getResultList();
			if (list.size() > 0) result = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int getIdByAccount(String account) {
		int id=0;
		CustomersBean bean = getByAccount(account);
		id = bean.getCtm_id();
		return id;
	}
	
	@SuppressWarnings("unchecked")
	
	@Override
	public CustomersBean getById(Integer id) {
		CustomersBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = (CustomersBean) session.get(CustomersBean.class, id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return bean;
	}

	public CustomersBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Customers> getAlls() {
		List<Customers> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM Customers")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	*/
}
