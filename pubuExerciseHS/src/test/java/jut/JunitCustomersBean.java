package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.CustomersBean;
import model.ProductsBean;
import model.ShoppingCartsBean;
import util.HibernateUtils;

public class JunitCustomersBean {

	private SessionFactory factory;
	private Session session;
	private Transaction tx;

	@Before
	public void setUp(){
		factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		tx = session.beginTransaction();
	}

	@After
	public void tearDown(){
		tx.commit();
		session.close();
		factory.close();
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		CustomersBean ctmBean= session.get(CustomersBean.class, 1);
		System.out.println("ctm_account: " + ctmBean.getCtm_account());
		ProductsBean pdBean= session.get(ProductsBean.class, 1);
		System.out.println("pd_name: " + pdBean.getPd_name());
		ShoppingCartsBean scBean= session.get(ShoppingCartsBean.class, 1);
//		System.out.println("sc_id: " + scBean.getSc_id());
//		System.out.println("ctm_account: " + scBean.getCtmb().getCtm_account());
		
	}

}
