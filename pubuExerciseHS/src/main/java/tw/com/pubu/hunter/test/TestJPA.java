package tw.com.pubu.hunter.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

public class TestJPA {
	private static EntityManager em;
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
    	HunterDebug.traceMessage();
//    	emf = Persistence.createEntityManagerFactory("example");
    	emf = JpaUtils.getEntityManagerFactory();
        em = emf.createEntityManager();
       
        CustomersBean ctmBean = em.find(CustomersBean.class, 2);
        HunterDebug.showKeyValue("ctmBean:", ctmBean.toString());
        CustomersDao dao = new CustomersDaoImpl();
        HunterDebug.showKeyValue("ccc", dao.getItemsFromShoppingCartByCustomerId(2).toString());
        
//        ProductsBean pdtBean = em.find(ProductsBean.class, 1);
//        HunterDebug.showKeyValue("pdtBean:", pdtBean.toString());
//        ShoppingCartsBean scBean = em.find(ShoppingCartsBean.class, 1);
//        HunterDebug.showKeyValue("pdtBean:", scBean.toString());
//        OrdersBean odBean = em.find(OrdersBean.class, 1);
//        HunterDebug.showKeyValue("pdtBean:", odBean.toString());
//        OrderDetailsBean oddtBean = em.find(OrderDetailsBean.class, 1);
//        HunterDebug.showKeyValue("pdtBean:", oddtBean.toString());
        em.close();
        emf.close();
    }
}
