package tw.com.pubu.hunter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;

@Service
public class ProductsService{
	@Autowired
	private ProductsDao dao;

	@Transactional
	public List<ProductsBean> getAlls() {
		List<ProductsBean> list = null;
		list = dao.getAlls();
		return list;
	}

	@Transactional
	public ProductsBean getById(int id) {
		ProductsBean bean = null;
		bean = dao.getById(id);
		return bean;
	}
}
