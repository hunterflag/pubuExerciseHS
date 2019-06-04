package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.enums.RecordStatus;
import tw.com.pubu.hunter.utils.HibernateUtils;

@Repository
public class ProductsDaoImpl implements ProductsDao {
	SessionFactory factory;
	
	public ProductsDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}

	@Override
	public ProductsBean getById(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		ProductsBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (ProductsBean) session.get(ProductsBean.class, id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ProductsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsBean> getAlls() {
		List<ProductsBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM ProductsBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}	
}
