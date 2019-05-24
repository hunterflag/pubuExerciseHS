package tw.com.pubu.hunter.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.idv.hunter.tool.HunterDebug;

public class TestJPA {
	private static EntityManager em;
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
    	HunterDebug.traceMessage();
    	emf = Persistence.createEntityManagerFactory("example");
        em = emf.createEntityManager();
       
        CustomersBean ctmBean = em.find(CustomersBean.class, 1);
        HunterDebug.showKeyValue("ctmBean:", ctmBean.toString());
        ProductsBean pdtBean = em.find(ProductsBean.class, 1);
        HunterDebug.showKeyValue("pdtBean:", pdtBean.toString());
        ShoppingCartsBean scBean = em.find(ShoppingCartsBean.class, 1);
        HunterDebug.showKeyValue("pdtBean:", scBean.toString());
        OrdersBean odBean = em.find(OrdersBean.class, 1);
        HunterDebug.showKeyValue("pdtBean:", odBean.toString());
        OrderDetailsBean oddtBean = em.find(OrderDetailsBean.class, 1);
        HunterDebug.showKeyValue("pdtBean:", oddtBean.toString());
        em.close();
        emf.close();
    }
}
