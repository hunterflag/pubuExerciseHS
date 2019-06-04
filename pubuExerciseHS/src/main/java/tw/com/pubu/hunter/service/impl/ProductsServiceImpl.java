package tw.com.pubu.hunter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.com.pubu.hunter.service.ProductsService;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Override
	public List<ProductsBean> getAlls(){
		List<ProductsBean> list = null;
		ProductsDao dao = new ProductsDaoImpl();
		list = dao.getAlls();
		return list;
	}

	
	public ProductsBean getById(int id) {
		ProductsBean bean=null;
		ProductsDao dao = new ProductsDaoImpl();
		bean = dao.getById(id);
		return bean;
	}
}
