package tw.com.pubu.hunter.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;

@Repository
public class OrderDetailsDao{
	@Autowired
	private SessionFactory factory;

	public Object insert(OrderDetailsBean insObj) {
		Session session = factory.getCurrentSession();
		Object key = null;

		key = session.save(insObj);
		return key;
	}

	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean) {
		Object key = null;
		OrderDetailsBean insObj = new OrderDetailsBean(pdtBean, number, price, odBean);
		key = insert(insObj);
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDetailsBean> getAllsById(int od_id) {
		List<OrderDetailsBean> result = null;
		Session session = factory.getCurrentSession();
		String qryHqlStr = "FROM OrderDetailsBean AS oddtb WHERE oddtb.odBean.od_id = :od_id";
		Query<OrderDetailsBean> query = session.createQuery(qryHqlStr);
		query.setParameter("od_id", od_id);
		result = query.getResultList();
		return result;
	}
}
