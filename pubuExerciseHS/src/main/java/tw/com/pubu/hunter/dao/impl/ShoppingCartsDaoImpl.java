package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.utils.HibernateUtils;

public class ShoppingCartsDaoImpl implements ShoppingCartsDao {
	SessionFactory factory;
	
	public ShoppingCartsDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(ShoppingCartsBean insObj) {
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
	
	public boolean delete(ShoppingCartsBean delObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		ShoppingCartsBean persistentBean = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(delObj);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	@Override
	public boolean isItemExist(int ctmId, int pdId) {
		boolean isExist = true;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId AND scb.pdtBean.pd_id = :pdId";
		try {
			tx = session.beginTransaction();
			int no = session.createQuery(hqlStr)
							.setParameter("ctmId", ctmId)
							.setParameter("pdId", pdId)
							.getResultList()
							.size();
			if(no > 0) isExist = true;
			else isExist = false;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isExist;
	}

	
	@Override
	public ShoppingCartsBean getById(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		ShoppingCartsBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (ShoppingCartsBean) session.get(ShoppingCartsBean.class, id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ShoppingCartsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		List<ShoppingCartsBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		try {
			tx = session.beginTransaction();
			
			result = session.createQuery(hqlStr)
							.setParameter("ctmId", ctmId)
							.getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getAlls() {
		List<ShoppingCartsBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM ShoppingCartsBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	public boolean update(ShoppingCartsBean updObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			ShoppingCartsBean persistentBean = session.get(ShoppingCartsBean.class, updObj.getSc_id());
			persistentBean.setCtmBean(updObj.getCtmBean());
			persistentBean.setPdtBean(updObj.getPdtBean());
			persistentBean.setSc_number(updObj.getSc_number());
			persistentBean.setSc_price(updObj.getSc_price());
//			session.saveOrUpdate(updObj);
//			session.merge(updObj);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	
}
