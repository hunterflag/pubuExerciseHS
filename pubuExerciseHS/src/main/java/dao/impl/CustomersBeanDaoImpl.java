package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.CustomersBeanDao;
import model.CustomersBean;
import util.HibernateUtils;

public class CustomersBeanDaoImpl implements CustomersBeanDao {
	SessionFactory factory;
	public CustomersBeanDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
/*
	@Override
	public Object add(CustomersBean ctmb) {
		Object id = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			id = session.save(ctmb);
			System.out.printf("id for new data: %d\n", id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return id;
	}
*/
	@Override
	public CustomersBean get(int id) {
		CustomersBean ctmb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			ctmb = (CustomersBean) session.get(CustomersBean.class, id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return ctmb;
	}

	
	@Override
	public List<CustomersBean> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

}
