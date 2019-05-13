package unUsed;

public class ProductServiceImpl implements ProductService {

	@Override
	public int add(String name, Double price) {
		ProductDao dao = new ProductDaoImpl();
		ProductBean newObj = new ProductBean(name, price);
		Object pk = dao.insert(newObj);
		int newPk = Integer.valueOf(newObj.getPk().toString());
		
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
			Query<ProductBean> query = session.createQuery(qryStr);
			query.setParameter("account", account);
			List<ProductBean> list = query.getResultList();
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
	public ProductBean getByAcc(String qryAcc) {
		HunterDebug.traceMessage();
		ProductBean result = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String qryStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query<ProductBean> query = session.createQuery(qryStr);
			query.setParameter("account", qryAcc);
			result = (ProductBean) query.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
*/
}
