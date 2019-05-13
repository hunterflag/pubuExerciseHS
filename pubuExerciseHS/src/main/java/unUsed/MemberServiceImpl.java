package tw.com.pubu.hunter.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.dao.MemberDao;
import tw.com.pubu.hunter.dao.impl.MemberDaoImpl;
import tw.com.pubu.hunter.service.MemberService;
import tw.idv.hunter.tool.HunterDebug;

public class MemberServiceImpl implements MemberService {

	@Override
	public int add(String account, String password) {
		MemberDao dao = new MemberDaoImpl();
		MemberBean newObj = new MemberBean(account, password);
		Object pk = dao.insert(newObj);
		int newPk = Integer.valueOf(newObj.getPk().toString());
		
//		HunterDebug.showKeyValue("pk", newPk);
		return newPk;
	}

	
/*	
	@SuppressWarnings("unchecked")
	public boolean isAccExist(String account) {
		HunterDebug.traceMessage();
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<MemberBean> query = session.createQuery(qryStr);
			query.setParameter("account", account);
			List<MemberBean> list = query.getResultList();
			if (list.size() > 0) result = true;
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			session.clear();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MemberBean getByAcc(String qryAcc) {
		HunterDebug.traceMessage();
		MemberBean result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<MemberBean> query = session.createQuery(qryStr);
			query.setParameter("account", qryAcc);
			result = (MemberBean) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
*/
}
