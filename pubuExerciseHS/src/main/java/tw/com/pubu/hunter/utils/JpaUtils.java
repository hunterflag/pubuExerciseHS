package tw.com.pubu.hunter.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory entityManagerFactory;
	
	//要與 persistence.xml 內的名稱對應
	private static String persistenceUnitName = "example"; 
	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void shutdown() {
		getEntityManagerFactory().close();
	}
}
