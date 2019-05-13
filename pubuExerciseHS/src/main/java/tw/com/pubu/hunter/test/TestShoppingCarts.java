package tw.com.pubu.hunter.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;
import unUsed.MemberBean;
import unUsed.ProductBean;
import unUsed.ShoppingCartBean;

public class TestShoppingCarts {
	
	public static void main(String[] args) {
//		CustomersBean cb =  new CustomersBean();
//		ProductsBean pb = new ProductsBean();
//		ShoppingCartsBean scb = new ShoppingCartsBean();
		
		//Request
		Session session= HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			int oid=27;
			tx = session.beginTransaction();
			ShoppingCartBean scb = session.get(ShoppingCartBean.class, oid);
			HunterDebug.showMessage(String.valueOf(oid), scb.toString());
			tx.commit();
		} catch(Exception ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		
		/*
		//Create
		Session session= HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Object key = null;
		try {
			tx = session.beginTransaction();
			key = session.save(cb);
			HunterDebug.showMessage(String.valueOf(key), cb.toString());
			tx.commit();
		} catch(Exception ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		//Update
		Session session= HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			int oid=12;
			tx = session.beginTransaction();
			CustomersBean cbu  = session.get(CustomersBean.class, oid);
			cbu.setCtm_password("0110");
			cbu.setCtm_account("PUBU0");
			session.update(cbu);
			HunterDebug.showMessage(String.valueOf(oid), cbu.toString());
			tx.commit();
		} catch(Exception ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		//Delete
		Session session= HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			int oid=13;
			tx = session.beginTransaction();
			CustomersBean cbd = session.get(CustomersBean.class, oid);
			session.delete(cbd);
			tx.commit();
		} catch(Exception ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
*/
		
		HibernateUtils.getSessionFactory().close();
	}
}
