package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bean.Customers;
import dao.CustomersDao;
import test.HunterDebug;
import util.HibernateUtils;

public class CustomersDaoImpl implements CustomersDao {
	SessionFactory factory;
	public CustomersDaoImpl() {
		HunterDebug.ShowMessage(CustomersDaoImpl.class.toString(), "Constructor()");
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		HunterDebug.ShowMessage(CustomersDaoImpl.class.toString(), "closeFactory()");
		factory.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean isAccExist(String account) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM Customers WHERE ctm_account = :account";
			Query<Customers> query = session.createQuery(qryStr);
			query.setParameter("account", account);
			List<Customers> list = query.getResultList();
			if (list.size() > 0) result = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customers getByAcc(String qryAcc) {
		Customers result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM Customers WHERE ctm_account = :account";
			Query<Customers> query = session.createQuery(qryStr);
			query.setParameter("account", qryAcc);
			result = (Customers) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	@Override
	public Customers getById(int id) {
		Customers ctmb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ctmb = (Customers) session.get(Customers.class, id);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return ctmb;
	}

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
}
