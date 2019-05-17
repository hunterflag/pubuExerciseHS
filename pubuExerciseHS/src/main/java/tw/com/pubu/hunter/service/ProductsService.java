package tw.com.pubu.hunter.service;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductsBean;

public interface ProductsService {
	public List<ProductsBean> getAlls();
	public ProductsBean getById(int id);
}
