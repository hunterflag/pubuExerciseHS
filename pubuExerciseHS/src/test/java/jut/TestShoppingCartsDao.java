package jut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartsDaoImpl;
import util.HibernateUtils;

public class TestShoppingCartsDao {
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
		CustomersDao cDao = new CustomersDaoImpl();
		CustomersBean cBean = cDao.getById(1);
		System.out.println(">>>>" + cBean);
		
//		ProductsDao pDao = new ProductsDaoImpl();

		ShoppingCartsDao scDao = new ShoppingCartsDaoImpl();
		ShoppingCartsBean scBean = scDao.getById(1);
		System.out.println(">>>>" + scBean);

//		Object newDId = cDao. CustomersBean("newCtm", "newCtm");
//		ShoppingCartsBean insObj = new ShoppingCartsBean();
//		
//		scDao.insert(insObj);
//		
//		ShoppingCartsBean bean = dao.getById(1);
//		
//		if (bean != null) System.out.println(bean);
//		else System.out.println("沒有");
	
	}

}
