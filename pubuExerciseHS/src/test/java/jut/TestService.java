package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unUsed.MemberService;
import unUsed.MemberServiceImpl;

public class TestService {

	private SessionFactory factory;
	private Session session;
	private Transaction tx;

	@Before
	public void setUp(){
//		factory = HibernateUtils.getSessionFactory();
//		session = factory.openSession();
//		tx = session.beginTransaction();
	}

	@After
	public void tearDown(){
//		tx.commit();
//		session.close();
//		factory.close();
	}

	@Test
	public void test() {
		MemberService service= new MemberServiceImpl();
		service.add("ffff", "ffff");
	}

}
