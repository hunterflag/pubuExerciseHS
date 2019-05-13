package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.bean.ShoppingCartBean;
import tw.com.pubu.hunter.dao.ShoppingCartDao;
import tw.com.pubu.hunter.enums.RecordStatus;
import tw.com.pubu.hunter.utils.HibernateUtils;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
	SessionFactory factory;
	
	public ShoppingCartDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(ShoppingCartBean insObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		
		try {
			tx = session.beginTransaction();
			key = session.save(insObj);
			ShoppingCartBean newBean = session.get(ShoppingCartBean.class, Integer.valueOf(key.toString()));
			newBean.setNo(Integer.valueOf(newBean.getPk()));
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public boolean delete(ShoppingCartBean delObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		ShoppingCartBean persistentBean = null;
		
		try {
			tx = session.beginTransaction();
			persistentBean = session.get(ShoppingCartBean.class, delObj.getPk());
			persistentBean.setStatus(RecordStatus.DELETE);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	@Override
	public ShoppingCartBean getByPk(Integer pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		ShoppingCartBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (ShoppingCartBean) session.get(ShoppingCartBean.class, pk);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ShoppingCartBean getByPk(int ipk) {
		return getByPk(Integer.valueOf(ipk));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartBean> getItemsByMember(int memberId) {
		List<ShoppingCartBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		String hqlStr = "FROM ShoppingCartBean AS scb WHERE scb.member.pk = :memberId";
		try {
			tx = session.beginTransaction();
			
			result = session.createQuery(hqlStr)
							.setParameter("memberId", memberId)
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
	public List<ShoppingCartBean> getAlls() {
		List<ShoppingCartBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM ShoppingCartBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	public boolean update(ShoppingCartBean updObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			ShoppingCartBean persistentBean = session.get(ShoppingCartBean.class, updObj.getPk());
			persistentBean.setMember(updObj.getMember());
			persistentBean.setProduct(updObj.getProduct());
			persistentBean.setNumber(updObj.getNumber());
			persistentBean.setPrice(updObj.getPrice());
			persistentBean.setStatus(updObj.getStatus());
			persistentBean.setNo(updObj.getNo());
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
