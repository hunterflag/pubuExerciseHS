package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;

@Repository
public class ShoppingCartsDaoImpl implements ShoppingCartsDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public Object insert(ShoppingCartsBean insObj) {
		Session session = factory.getCurrentSession();
		Object key = null;

		key = session.save(insObj);
		return key;
	}

	@Override
	public boolean delete(ShoppingCartsBean delObj) {
		Session session = factory.getCurrentSession();
		boolean isSuccess = false;

		session.delete(delObj);
		isSuccess = true;

		return isSuccess;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		ShoppingCartsBean sc = getById(id);
		result = delete(sc);
		return result;
	}

	@Override
	public int deleteAllByCustomer(int ctmId) {
		int result = 0;
		Session session = factory.getCurrentSession();

		String hqlStr = "DELETE FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).executeUpdate();

		return result;
	}

	@Override
	public boolean isItemExist(int ctmId, int pdId) {
		boolean isExist = true;
		Session session = factory.getCurrentSession();

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId AND scb.pdtBean.pd_id = :pdId";
		int no = session.createQuery(hqlStr).setParameter("ctmId", ctmId).setParameter("pdId", pdId).getResultList()
				.size();
		if (no > 0)
			isExist = true;
		else
			isExist = false;

		return isExist;
	}

	@Override
	public ShoppingCartsBean getById(Integer id) {
		Session session = factory.getCurrentSession();
		ShoppingCartsBean persistentBean = null;

		persistentBean = (ShoppingCartsBean) session.get(ShoppingCartsBean.class, id);

		return persistentBean;
	}

	@Override
	public ShoppingCartsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		List<ShoppingCartsBean> result = null;
		Session session = factory.getCurrentSession();

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getAlls() {
		List<ShoppingCartsBean> result = null;
		Session session = factory.getCurrentSession();
		result = session.createQuery("FROM ShoppingCartsBean").getResultList();

		return result;
	}

	@Override
	public boolean update(ShoppingCartsBean updObj) {
		Session session = factory.getCurrentSession();
		boolean isSuccess = false;

		ShoppingCartsBean persistentBean = session.get(ShoppingCartsBean.class, updObj.getSc_id());
		persistentBean.setCtmBean(updObj.getCtmBean());
		persistentBean.setPdtBean(updObj.getPdtBean());
		persistentBean.setSc_number(updObj.getSc_number());
		persistentBean.setSc_price(updObj.getSc_price());
		isSuccess = true;

		return isSuccess;
	}

}
