package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.dao.MemberDao;
import tw.com.pubu.hunter.dao.impl.MemberDaoImpl;
import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;

public class TestDao {

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
		MemberDao dao= new MemberDaoImpl();
		MemberBean bean= dao.getByPk(1);
//		HunterDebug.showKeyValue("account: ", bean.getAccount());
//		System.out.println("account: " + bean.getAccount());
		System.out.println("ddd");
		
	}

}
