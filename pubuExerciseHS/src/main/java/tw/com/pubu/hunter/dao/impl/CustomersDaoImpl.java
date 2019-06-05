package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;

@Repository
public class CustomersDaoImpl implements CustomersDao {
	@Autowired
	private SessionFactory factory;

	@Override
	@SuppressWarnings("unchecked")
	public CustomersBean getByAccount(String account) {
		CustomersBean result = null;
		// 輸入資料檢查
		if (account.isEmpty())
			return result; // 沒輸入
		if (!isAccountExist(account))
			return result; // 無此帳號

		Session session = factory.getCurrentSession();
		String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
		Query<CustomersBean> query = session.createQuery(qryHqlStr);
		query.setParameter("account", account);
		result = (CustomersBean) query.getSingleResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isAccountExist(String account) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		String qryHqlStr = "FROM CustomersBean WHERE ctm_account = :account";
		Query<CustomersBean> query = session.createQuery(qryHqlStr);
		query.setParameter("account", account);
		List<CustomersBean> list = query.getResultList();
		if (list.size() > 0)
			result = true;
		return result;
	}

	@Override
	public int getIdByAccount(String account) {
		int id = 0;
		CustomersBean bean = getByAccount(account);
		id = bean.getCtm_id();
		return id;
	}

	@Override
	public CustomersBean getById(Integer id) {
		CustomersBean bean = null;
		Session session = factory.getCurrentSession();
		bean = (CustomersBean) session.get(CustomersBean.class, id);
		return bean;
	}

	@Override
	public CustomersBean getById(int id) {
		return getById(Integer.valueOf(id));
	}

}
