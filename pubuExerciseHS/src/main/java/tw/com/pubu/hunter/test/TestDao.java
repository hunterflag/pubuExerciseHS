package tw.com.pubu.hunter.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import unUsed.MemberBean;
import unUsed.MemberDao;
import unUsed.MemberDaoImpl;
import unUsed.ShoppingCartBean;
import unUsed.ShoppingCartDao;
import unUsed.ShoppingCartDaoImpl;

public class TestDao {

	public static void main(String[] args) {
//		PropertyConfigurator.configure("/pubuExerciseHS/src/main/resources/log4j.properties");
		Logger logger = Logger.getLogger(TestDao.class);
		MemberDao dao = new MemberDaoImpl();
		List<MemberBean> list = dao.getAlls();
		logger.info(list.size());
		for(MemberBean element : list) {
			logger.info(element.getNo());
		/*
		ShoppingCartDao dao = new ShoppingCartDaoImpl();
		List<ShoppingCartBean> list = dao.getAlls();
		logger.error("TEST");			
		logger.info("TEST");			
		*/
		}
		/*
		for(ShoppingCartBean item : list) {
			System.out.println(item.getNo());
		}
		*/
	}

}
