package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;

@Repository
public class ProductsDaoImpl implements ProductsDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public ProductsBean getById(Integer id) {
		Session session = factory.getCurrentSession();
		ProductsBean persistentBean = null;

		persistentBean = (ProductsBean) session.get(ProductsBean.class, id);
		return persistentBean;
	}

	@Override
	public ProductsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsBean> getAlls() {
		List<ProductsBean> result = null;
		Session session = factory.getCurrentSession();
		result = session.createQuery("FROM ProductsBean").getResultList();

		return result;
	}
}
