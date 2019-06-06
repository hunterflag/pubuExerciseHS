package tw.com.pubu.hunter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;

@Repository
public class ShoppingCartsDao{
//	@Autowired
//	private SessionFactory factory;
	
	@PersistenceContext
	private EntityManager session;
	
	public Object insert(ShoppingCartsBean insObj) {
//		Session session = factory.getCurrentSession();
		Object key = null;

		session.persist(insObj);
		key = (Object) insObj.getSc_id();
		return key;
	}

	public boolean delete(ShoppingCartsBean delObj) {
//		Session session = factory.getCurrentSession();
		boolean isSuccess = false;

		session.remove(delObj);
		isSuccess = true;

		return isSuccess;
	}

	public boolean delete(int id) {
		boolean result = false;
		ShoppingCartsBean sc = getById(id);
		result = delete(sc);
		return result;
	}

	public int deleteAllByCustomer(int ctmId) {
		int result = 0;
//		Session session = factory.getCurrentSession();

		String hqlStr = "DELETE FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).executeUpdate();

		return result;
	}

	public boolean isItemExist(int ctmId, int pdId) {
		boolean isExist = true;
//		Session session = factory.getCurrentSession();

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId AND scb.pdtBean.pd_id = :pdId";
		int no = session.createQuery(hqlStr).setParameter("ctmId", ctmId).setParameter("pdId", pdId).getResultList()
				.size();
		if (no > 0)
			isExist = true;
		else
			isExist = false;

		return isExist;
	}

	public ShoppingCartsBean getById(Integer id) {
//		Session session = factory.getCurrentSession();
		ShoppingCartsBean persistentBean = null;

		persistentBean = (ShoppingCartsBean) session.find(ShoppingCartsBean.class, id);

		return persistentBean;
	}

	public ShoppingCartsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		List<ShoppingCartsBean> result = null;
//		Session session = factory.getCurrentSession();

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ShoppingCartsBean> getAlls() {
		List<ShoppingCartsBean> result = null;
//		Session session = factory.getCurrentSession();
		result = session.createQuery("FROM ShoppingCartsBean").getResultList();

		return result;
	}

	public boolean update(ShoppingCartsBean updObj) {
//		Session session = factory.getCurrentSession();
		boolean isSuccess = false;

		ShoppingCartsBean persistentBean = session.find(ShoppingCartsBean.class, updObj.getSc_id());
		persistentBean.setCtmBean(updObj.getCtmBean());
		persistentBean.setPdtBean(updObj.getPdtBean());
		persistentBean.setSc_number(updObj.getSc_number());
		persistentBean.setSc_price(updObj.getSc_price());
		isSuccess = true;

		return isSuccess;
	}

}
