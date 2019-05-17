package tw.com.pubu.hunter.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
import tw.com.pubu.hunter.dao.impl.OrderDetailsDaoImpl;
import tw.com.pubu.hunter.dao.impl.OrdersDaoImpl;
import tw.com.pubu.hunter.dao.impl.ProductsDaoImpl;
import tw.com.pubu.hunter.dao.impl.ShoppingCartsDaoImpl;
import tw.com.pubu.hunter.service.ShoppingCartsService;
import tw.idv.hunter.tool.HunterDebug;

public class ShoppingCartsServiceImpl implements ShoppingCartsService {

	@Override
	public int add(int memberId, int productId){
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		//項目已存在, 不再重複加入
		if(dao.isItemExist(memberId, productId)) return 0;
		
		//項目不存在, 新增項目
		CustomersBean cBean = new CustomersDaoImpl().getById(memberId);
		ProductsBean pBean = new ProductsDaoImpl().getById(productId);
		double price = (int)(pBean.getPd_price() / 100) * 100;
		ShoppingCartsBean newObj = new ShoppingCartsBean(cBean, pBean, price);
		Object pk = dao.insert(newObj);
		
		return Integer.valueOf(pk.toString());
	}

	@Override
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId){
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		List<ShoppingCartsBean> list = dao.getItemsByCustomer(ctmId);
		return list;
	}
	
	@Override
	public boolean removeById(int sc_id) {
		boolean result = false;
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		result = dao.delete(sc_id);
		return result;
	}

	@Override
	public int clearByCustomer(int ctmId) {
		int result = 0;
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		result = dao.deleteAllByCustomer(ctmId);
		return result;
	}

	@Override
	public boolean updateNumberOfItem(int sc_id, int newNumber) {
		
		boolean result = false;
		ShoppingCartsDao dao = new ShoppingCartsDaoImpl();
		ShoppingCartsBean bean = dao.getById(sc_id);
		bean.setSc_number(newNumber);
		dao.update(bean);
		
		return result;
	}
	
	@Override
	public int ConfirmToOrder(int ctmId) {		//傳入客戶Id
		int number = 0;	
		//訂單 1: 先建立訂單編號、取得新增訂單的 oid
		OrdersDao odDao = new OrdersDaoImpl();
		int od_id = (int) odDao.insert(ctmId);
		
		//取出會員的購物車內容
		ShoppingCartsDao scDao = new ShoppingCartsDaoImpl();
		List<ShoppingCartsBean> scList = scDao.getItemsByCustomer(ctmId);
		
		//依購物車內容, 建立訂購明細表 & 計算總價
		OrdersBean odBean = odDao.getById(od_id);
		OrderDetailsDao oddtDao = new OrderDetailsDaoImpl();
		int total_price = 0;
		for(ShoppingCartsBean scBean : scList) {
			oddtDao.insert(scBean.getPdtBean(),	
						   scBean.getSc_price().intValue(),
						   scBean.getSc_number(),
						   odBean
					   );
			total_price += scBean.getSc_price() * scBean.getSc_number();
		}
			
		//訂單2：更新總價
		odDao.update(od_id, total_price);

		//移除購物車內容
		scDao.deleteAllByCustomer(ctmId);
		
		return number;
	}
}
