package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.CustomersDao;
import tw.com.pubu.hunter.bean.Customers;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import util.HibernateUtils;

public class TestCustomers {
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
		Customers ctmBean= session.get(Customers.class, 1);
		System.out.println("=========================================");
		System.out.println("ctm_account: " + ctmBean.getCtm_account());
		System.out.println("ctm: " + ctmBean);
		System.out.println("=========================================");
	}
	
	@Test
	public void testGetByAcc() {
		CustomersDao dao = new CustomersDaoImpl();
		String account = "Tester0";

		Customers ctm =null;
		if (dao.isAccExist(account)) {
			ctm = dao.getByAcc(account);
			System.out.println(account + " 帳號的密碼是: " + ctm.getCtm_password());
		}else {
			System.out.println("沒有" + account + "帳號");
		}
	}

}
