package jut;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.com.pubu.hunter.bean.Customers;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
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
		Logger log = Logger.getLogger(JunitCustomersBean.class);
		
		Customers ctmBean= session.get(Customers.class, 1);
		System.out.println("=========================================");
		System.out.println("ctm_account: " + ctmBean.getCtm_account());
		log.debug("Hello Log4j");
		System.out.println("=========================================");

		ProductsBean pdBean= session.get(ProductsBean.class, 1);
		System.out.println("=========================================");
		System.out.println("pd_name: " + pdBean.getPd_name());
		System.out.println("=========================================");

		ShoppingCartsBean scBean= session.get(ShoppingCartsBean.class, 1);
		System.out.println("=========================================");
		System.out.println("sc_id: " + scBean.getSc_id());
		System.out.println("=========================================");
//		System.out.println("ctm_account: " + scBean.getCtmb().getCtm_account());
		
	}

}
