package tw.com.pubu.hunter.test;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.idv.hunter.tool.HunterDebug;

public class TestProducts {

	public static void main(String[] args) {
		ProductsDao dao = new ProductsDaoImpl();
		
		List<ProductsBean> list = dao.getAlls();
		
		for(ProductsBean element : list) {		
		HunterDebug.showKeyValue("element: ", element.toString());
		}
	
		for(int i=0; i<list.size(); i++)
			HunterDebug.showKeyValue("element: ", list.get(i).toString());
	}

}
