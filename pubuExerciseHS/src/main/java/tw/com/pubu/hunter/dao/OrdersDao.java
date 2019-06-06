package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrdersDao;

@Repository
public class OrdersDaoImpl implements OrdersDao {
	@Autowired
	private SessionFactory factory;
	@Autowired
	private CustomersDao ctmDao;

	@Override
	public Object insert(OrdersBean insObj) {
		Session session = factory.getCurrentSession();
		Object key = null;
		key = session.save(insObj);
		return key;
	}

	@Override
	public Object insert(int ctm_id) {
		CustomersBean ctmBean = ctmDao.getById(ctm_id);

		OrdersBean insObj = new OrdersBean(ctmBean);
		Object key = insert(insObj);
		return key;
	}

	@Override
	public boolean update(int od_id, int od_total_price) {
		Session session = factory.getCurrentSession();
		boolean isSuccess = false;

		OrdersBean bean = session.get(OrdersBean.class, od_id);
		bean.setOd_total_price(od_total_price);
		bean.setOd_state("close");
		isSuccess = true;

		return isSuccess;
	}

	@Override
	public OrdersBean getById(Integer od_id) {
		Session session = factory.getCurrentSession();
		OrdersBean result = null;

		result = session.get(OrdersBean.class, od_id);
		return result;
	}

	@Override
	public OrdersBean getById(int od_id) {
		OrdersBean result = null;
		result = getById(Integer.valueOf(od_id));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAllsByCustomer(int ctmId) {
		List<OrdersBean> result = null;
		Session session = factory.getCurrentSession();
		String qryHqlStr = "FROM OrdersBean AS ob WHERE ob.ctmBean.ctm_id = :ctmId";
		Query<OrdersBean> query = session.createQuery(qryHqlStr);
		query.setParameter("ctmId", ctmId);
		result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAlls() {
		List<OrdersBean> result = null;
		Session session = factory.getCurrentSession();
		result = session.createQuery("FROM Orders").getResultList();
		return result;
	}

}
