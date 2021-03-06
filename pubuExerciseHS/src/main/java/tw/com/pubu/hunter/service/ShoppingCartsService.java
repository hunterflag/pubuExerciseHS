package tw.com.pubu.hunter.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.service.ShoppingCartsService;

@Service
public class ShoppingCartsService{
	@Autowired
	private ShoppingCartsDao scDao;
	@Autowired
	private CustomersDao ctmDao;
	@Autowired
	private ProductsDao pdtDao;
	@Autowired
	private OrdersDao odDao;
	@Autowired
	private OrderDetailsDao oddtDao;

	@Transactional
	public int add(int memberId, int productId) {
		// 項目已存在, 不再重複加入
		if (scDao.isItemExist(memberId, productId))
			return 0;

		// 項目不存在, 新增項目
		CustomersBean cBean = ctmDao.getById(memberId);
		ProductsBean pBean = pdtDao.getById(productId);
		double price = (int) (pBean.getPd_price() / 100) * 100;
		ShoppingCartsBean newObj = new ShoppingCartsBean(cBean, pBean, price);
		Object pk = scDao.insert(newObj);

		return Integer.valueOf(pk.toString());
	}

	@Transactional
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		List<ShoppingCartsBean> list = scDao.getItemsByCustomer(ctmId);
		return list;
	}

	@Transactional
	public boolean removeById(int sc_id) {
		boolean result = false;
		result = scDao.delete(sc_id);
		return result;
	}

	@Transactional
	public int clearByCustomer(int ctmId) {
		int result = 0;
		result = scDao.deleteAllByCustomer(ctmId);
		return result;
	}

	@Transactional
	public boolean updateNumberOfItem(int sc_id, int newNumber) {

		boolean result = false;
		ShoppingCartsBean bean = scDao.getById(sc_id);
		bean.setSc_number(newNumber);
		scDao.update(bean);

		return result;
	}

	// 這裡用到 2 個 dao
//	@Transactional(rollbackFor=Exception.class)
	@Transactional
	public int confirmToOrder(int ctmId) { // 傳入客戶Id
		int number = 0;
		// 訂單 1: 先建立訂單編號、取得新增訂單的 oid
		int od_id = (int) odDao.insert(ctmId);

		// 取出會員的購物車內容
		List<ShoppingCartsBean> scList = scDao.getItemsByCustomer(ctmId);

		// 依購物車內容, 建立訂購明細表 & 計算總價
		OrdersBean odBean = odDao.getById(od_id);
		int total_price = 0;
		for (ShoppingCartsBean scBean : scList) {
			oddtDao.insert(scBean.getPdtBean(), scBean.getSc_price().intValue(), scBean.getSc_number(), odBean);
			total_price += scBean.getSc_price() * scBean.getSc_number();
		}

		// 訂單2：更新總價
		odDao.update(od_id, total_price);

		// 移除購物車內容
		scDao.deleteAllByCustomer(ctmId);
		
		//測試 rollback 能力
		if(false) {
			try {
				int i = 1/0; 		
				throw new RuntimeException("Manual Test Exception for rollback");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return number;
	}
}
