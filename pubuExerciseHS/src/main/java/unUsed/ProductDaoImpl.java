package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.bean.ProductBean;
import tw.com.pubu.hunter.dao.ProductDao;
import tw.com.pubu.hunter.enums.RecordStatus;
import tw.com.pubu.hunter.utils.HibernateUtils;

public class ProductDaoImpl implements ProductDao {
	SessionFactory factory;
	
	public ProductDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(ProductBean insObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		
		try {
			tx = session.beginTransaction();
			key = session.save(insObj);
			ProductBean newBean = session.get(ProductBean.class, Integer.valueOf(key.toString()));
			newBean.setNo(Integer.valueOf(newBean.getPk()));
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public boolean delete(ProductBean delObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		ProductBean persistentBean = null;
		
		try {
			tx = session.beginTransaction();
			persistentBean = session.get(ProductBean.class, delObj.getPk());
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
	public ProductBean getByPk(Integer pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		ProductBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (ProductBean) session.get(ProductBean.class, pk);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ProductBean getByPk(int ipk) {
		return getByPk(Integer.valueOf(ipk));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAlls() {
		List<ProductBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM ProductBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	public boolean update(ProductBean updObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			ProductBean persistentBean = session.get(ProductBean.class, updObj.getPk());
			persistentBean.setName(updObj.getName());
			persistentBean.setPrice(updObj.getPrice());
			persistentBean.setStatus(updObj.getStatus());
			persistentBean.setNo(updObj.getNo());
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	
}
