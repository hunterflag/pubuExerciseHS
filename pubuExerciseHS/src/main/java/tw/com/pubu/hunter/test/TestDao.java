package tw.com.pubu.hunter.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.bean.ShoppingCartBean;
import tw.com.pubu.hunter.dao.MemberDao;
import tw.com.pubu.hunter.dao.ShoppingCartDao;
import tw.com.pubu.hunter.dao.impl.MemberDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartDaoImpl;

public class TestDao {

	public static void main(String[] args) {
//		PropertyConfigurator.configure("/pubuExerciseHS/src/main/resources/log4j.properties");
		Logger logger = Logger.getLogger(TestDao.class);
		MemberDao dao = new MemberDaoImpl();
		List<MemberBean> list = dao.getAlls();
		logger.info(list.size());
		for(MemberBean element : list) {
			logger.info(element.getNo());
		
		ShoppingCartDao dao = new ShoppingCartDaoImpl();
		List<ShoppingCartBean> list = dao.getAlls();
		logger.error("TEST");			
		logger.info("TEST");			
		
		}
		/*
		for(ShoppingCartBean item : list) {
			System.out.println(item.getNo());
		}
		*/
	}

}
