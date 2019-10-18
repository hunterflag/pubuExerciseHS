package tw.com.pubu.hunter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;

@Repository
public class CustomersDao{
	@PersistenceContext 
	private EntityManager session;

	public CustomersBean getByAccount(String account) {
		CustomersBean result = null;
		// 輸入資料檢查
		if (account.isEmpty())
			return result; // 沒輸入
		if (!isAccountExist(account))
			return result; // 無此帳號

		String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
		Query query = session.createQuery(qryHqlStr);
		query.setParameter("account", account);
		result = (CustomersBean) query.getSingleResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean isAccountExist(String account) {
		boolean result = false;
		String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
		Query query = session.createQuery(qryHqlStr);
		query.setParameter("account", account);
		List<CustomersBean> list = query.getResultList();
		if (list.size() > 0)
			result = true;
		return result;
	}

	public int getIdByAccount(String account) {
		int id = 0;
		CustomersBean bean = getByAccount(account);
		id = bean.getCtm_id();
		return id;
	}

	public CustomersBean getById(Integer id) {
		CustomersBean bean = null;
		bean = (CustomersBean) session.find(CustomersBean.class, id);
		return bean;
	}

	public CustomersBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

}
