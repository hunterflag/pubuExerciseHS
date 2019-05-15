package unUsed;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.enums.RecordStatus;
import tw.com.pubu.hunter.utils.HibernateUtils;
import tw.idv.hunter.tool.HunterDebug;

public class MemberDaoImpl implements MemberDao {
	SessionFactory factory;
	
	public MemberDaoImpl() {
		HunterDebug.showMessage("gedd");
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		factory.close();
	}
	
	@Override
	public Object insert(MemberBean insObj) {
		HunterDebug.traceMessage();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		
		try {
			tx = session.beginTransaction();
			key = session.save(insObj);
			MemberBean newMember = session.get(MemberBean.class, Integer.valueOf(key.toString()));
			newMember.setNo(newMember.getPk());
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public boolean delete(MemberBean delObj) {
		HunterDebug.traceMessage();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		MemberBean persistentBean = null;
		
		try {
			tx = session.beginTransaction();
			persistentBean = session.get(MemberBean.class, delObj.getPk());
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
	public MemberBean getByPk(Integer pk) {
		HunterDebug.traceMessage();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		MemberBean persistentBean = null;

		try {
			tx = session.beginTransaction();
			persistentBean = (MemberBean) session.get(MemberBean.class, pk);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public MemberBean getByPk(int ipk) {
		HunterDebug.traceMessage();
		return getByPk(Integer.valueOf(ipk));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAlls() {
		List<MemberBean> result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = session.createQuery("FROM MemberBean")
						  .getResultList();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	public boolean update(MemberBean updObj) {
		HunterDebug.traceMessage();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean isSuccess = false;
		
		try {
			tx = session.beginTransaction();
			MemberBean PersistentBean = session.get(MemberBean.class, updObj.getPk());
			PersistentBean.setAccount(updObj.getAccount());
			PersistentBean.setPassword(updObj.getPassword());
			PersistentBean.setStatus(updObj.getStatus());
			PersistentBean.setNo(updObj.getNo());
			isSuccess = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	
		
	
	
	
}
