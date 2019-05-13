package unUsed;

import java.util.List;

public interface ProductDao {
	public Object insert(ProductBean insObj);
	public boolean delete(ProductBean delObj);
	public ProductBean getByPk(Integer pk);
	public ProductBean getByPk(int ipk);
	public List<ProductBean> getAlls();
	public boolean update(ProductBean updObj);
}
