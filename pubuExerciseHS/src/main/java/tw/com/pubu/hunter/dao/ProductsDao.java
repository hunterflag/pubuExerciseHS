package tw.com.pubu.hunter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;

@Repository
public class ProductsDao{
	
	@PersistenceContext
	private EntityManager session;
	
	public ProductsBean getById(Integer id) {
		ProductsBean persistentBean = null;

		persistentBean = (ProductsBean) session.find(ProductsBean.class, id);
		return persistentBean;
	}

	public ProductsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	public List<ProductsBean> getAlls() {
		List<ProductsBean> result = null;
		result = session.createQuery("FROM ProductsBean").getResultList();

		return result;
	}
}
