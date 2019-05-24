package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;

public class TestBean {

/**** 設定區 *******************************************/	
	private static SessionFactory factory;
	private Session session;
	private Transaction tx;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = HibernateUtils.getSessionFactory();
	}

	@Before
	public void setUp(){
		session = factory.openSession();
		tx = session.beginTransaction();
	}

	@After
	public void tearDown(){
		try {
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			factory.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
/**** 測試區 *******************************************/	
	@Test
	public void testOrdersBean() {
		OrdersBean bean = session.get(OrdersBean.class, 2);
		bean.setOd_total_price(2000);
		HunterDebug.showKeyValue("bean: ", bean.toString());
		
	}

	@Test
	public void testCustomersBean() {
		CustomersBean bean = session.get(CustomersBean.class, 1);
		HunterDebug.showKeyValue("bean: ", bean.toString());
		
	}

	
	
		
}
