package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductsBean;

public interface ProductsDao {
//	public Object insert(ProductBean insObj);
//	public boolean delete(ProductBean delObj);
	public ProductsBean getByPk(Integer pk);
	public ProductsBean getByPk(int ipk);
	public List<ProductsBean> getAlls();
//	public boolean update(ProductBean updObj);
}
