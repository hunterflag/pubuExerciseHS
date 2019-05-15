package jut;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.dao.impl.OrdersDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartsDaoImpl;
import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;

public class TestDao {
	
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
	public void testShoppingCartsDao() {
		ShoppingCartsDao dao= new ShoppingCartsDaoImpl();
		int ctm_id = 3;
		List<ShoppingCartsBean> list = dao.getItemsByCustomer(ctm_id);
		HunterDebug.showKeyValue("size()", list.size());
		HunterDebug.showKeyValue("list: ", list.toString());
		for(ShoppingCartsBean scBean : list) {
			HunterDebug.showKeyValue("ctmBean", scBean.getCtmBean().getClass().getName());
			HunterDebug.showKeyValue("pdtBean", scBean.getPdtBean().getClass().getName());
		
		}
	}

//	@Test
//	public void test() {
//		OrdersDao dao= new OrdersDaoImpl();
//		int ctm_id = 2;
//		int bean = (int) dao.insert(ctm_id);
//		System.out.println(">>>>bean: " + bean);
//		
//	}

//	@Test
//	public void testaa() {
//		OrdersDao dao= new OrdersDaoImpl();
//		int ctm_id = 2;
//		int bean= (int) dao.insert(ctm_id);
//		System.out.println(">>>bean: " + bean);
//		
//	}

}
