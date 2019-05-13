package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;
import unUsed.MemberBean;

public class TestBean {

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
		MemberBean bean= session.get(MemberBean.class, 1);
		HunterDebug.showKeyValue("account: ", bean.getAccount());
		System.out.println("account: " + bean.getAccount());
		
	}

}
