package tw.com.pubu.hunter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;

@Repository
public class ShoppingCartsDao{
	
	@PersistenceContext
	private EntityManager session;
	
	@Transactional
	public Object insert(ShoppingCartsBean insObj) {
		Object key = null;

		session.persist(insObj);
		key = (Object) insObj.getSc_id();
		return key;
	}

	@Transactional
	public boolean delete(ShoppingCartsBean delObj) {
		boolean isSuccess = false;

		session.remove(delObj);
		isSuccess = true;

		return isSuccess;
	}

	@Transactional
	public boolean delete(int id) {
		boolean result = false;
		ShoppingCartsBean sc = getById(id);
		result = delete(sc);
		return result;
	}

//	@Transactional
	public int deleteAllByCustomer(int ctmId) {
		int result = 0;

		String hqlStr = "DELETE FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).executeUpdate();

		return result;
	}

//	@Transactional
	public boolean isItemExist(int ctmId, int pdId) {
		boolean isExist = true;

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId AND scb.pdtBean.pd_id = :pdId";
		int no = session.createQuery(hqlStr).setParameter("ctmId", ctmId).setParameter("pdId", pdId).getResultList()
				.size();
		if (no > 0)
			isExist = true;
		else
			isExist = false;

		return isExist;
	}

//	@Transactional
	public ShoppingCartsBean getById(Integer id) {
		ShoppingCartsBean persistentBean = null;

		persistentBean = (ShoppingCartsBean) session.find(ShoppingCartsBean.class, id);

		return persistentBean;
	}

//	@Transactional
	public ShoppingCartsBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
//	@Transactional
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		List<ShoppingCartsBean> result = null;

		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctmBean.ctm_id = :ctmId";
		result = session.createQuery(hqlStr).setParameter("ctmId", ctmId).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
//	@Transactional
	public List<ShoppingCartsBean> getAlls() {
		List<ShoppingCartsBean> result = null;
		result = session.createQuery("FROM ShoppingCartsBean").getResultList();

		return result;
	}

//	@Transactional
	public boolean update(ShoppingCartsBean updObj) {
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
