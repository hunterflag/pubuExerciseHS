package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductBean;

public interface ProductDao {
	public Object insert(ProductBean insObj);
	public boolean delete(ProductBean delObj);
	public ProductBean getByPk(Integer pk);
	public ProductBean getByPk(int ipk);
	public List<ProductBean> getAlls();
	public boolean update(ProductBean updObj);
}
