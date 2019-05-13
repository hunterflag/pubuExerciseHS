package unUsed;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.enums.RecordStatus;
import tw.com.pubu.hunter.utils.HibernateUtils;

public class OrderDaoImpl implements OrderDao {
	SessionFactory factory;
	
	public OrderDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	@Override
	public Object insert(OrderBean insObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		
		try {
			tx = session.beginTransaction();
			key = session.save(insObj);
			OrderBean newBean = session.get(OrderBean.class, Integer.valueOf(key.toString()));
			newBean.setNo(Integer.valueOf(newBean.getPk()));
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public boolean delete(OrderBean delObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		OrderBean persistentBean = null;
		
		try {
			tx = session.beginTransaction();
			persistentBean = session.get(OrderBean.class, delObj.getPk());
			persistentBean.setStatus(RecordStatus.DELETE);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	@Override
	public OrderBean getByPk(Integer pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		OrderBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (OrderBean) session.get(OrderBean.class, pk);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public OrderBean getByPk(int ipk) {
		return getByPk(Integer.valueOf(ipk));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAlls() {
		List<OrderBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM OrderBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	public boolean update(OrderBean updObj) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
//			OrderBean persistentBean = session.get(OrderBean.class, updObj.getPk());
//			persistentBean.setMember(updObj.getMember());
//			persistentBean.setPrice(updObj.getPrice());
//			persistentBean.setStatus(updObj.getStatus());
//			persistentBean.setNo(updObj.getNo());
//			persistentBean.setPk(updObj.getPk());
//			persistentBean.setTime(updObj.getTime());
			session.saveOrUpdate(updObj);
//			session.merge(updObj);
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	
}
