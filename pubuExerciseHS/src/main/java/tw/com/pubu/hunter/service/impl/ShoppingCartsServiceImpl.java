package tw.com.pubu.hunter.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import tw.com.pubu.hunter.ConnectionFactory;
import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.dao.impl.CustomersDaoImpl;
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
	
	public int shoppingCartConfirmOrder(int ctmId) {
		int number = 0;	

		//訂購商品種類數
		//訂單 1: 先建立訂單編號、取得新增訂單的 oid
		
		//取出會員的購物車內容
		ShoppingCartsDao scDao = new ShoppingCartsDaoImpl();
		List<ShoppingCartsBean> scList = scDao.getItemsByCustomer(ctmId);
		
		//依購物車內容, 建立訂購明細表 & 計算總價
		
		//訂單2：更新總價
		//移除購物車內容
		//取出客戶Id
		try {
			conn = ConnectionFactory.getConnection();
			//建立訂單 1: 先建立訂單編號、取得新增訂單的 oid
			int od_id=0;
			String insStmt = "INSERT INTO orders(ctm_id) VALUES (?);"; 
			PreparedStatement ins_stmt = conn.prepareStatement(insStmt, PreparedStatement.RETURN_GENERATED_KEYS);
			ins_stmt.setInt(1, ctm_id);
			ins_stmt.executeUpdate();
			ResultSet rs_od = ins_stmt.getGeneratedKeys();
			if(rs_od.next()) {
				od_id = rs_od.getInt(1);
			}

			//取出購物車內容
			String qryStmt = "SELECT * FROM shopping_carts WHERE ctm_id=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			pstmt.setInt(1, ctm_id);
			ResultSet rs_sc = pstmt.executeQuery();

			//依購物車內容, 建立訂購明細表 & 計算總價
			String oddtInsStmt = "INSERT INTO order_details(pd_id, oddt_price, oddt_number, od_id) " 
								+ "VALUES (?, ?, ?, ?);";
			PreparedStatement oddt_insStmt = conn.prepareStatement(oddtInsStmt);
			
			int total_price=0;
			while (rs_sc.next()) {
				total_price += rs_sc.getInt("sc_price") * rs_sc.getInt("sc_number");
				oddt_insStmt.setInt(1, rs_sc.getInt("pd_id"));
				oddt_insStmt.setInt(2, rs_sc.getInt("sc_price"));
				oddt_insStmt.setInt(3, rs_sc.getInt("sc_number"));
				oddt_insStmt.setInt(4, od_id);
				oddt_insStmt.executeUpdate();
			}
			System.out.println(total_price);
			
			//建立訂單2：更新總價
			String oddtUpdStmt = "UPDATE orders "
								+"SET od_total_price=?, od_state=? " 
								+" WHERE od_id=? ;"; 
			PreparedStatement oddt_updStmt = conn.prepareStatement(oddtUpdStmt);
			oddt_updStmt.setInt(1, total_price);
			oddt_updStmt.setString(2, "close");
			oddt_updStmt.setInt(3, od_id);
			oddt_updStmt.executeUpdate();
			
			//移除購物車內容
			String delStmt = "DELETE FROM shopping_carts WHERE ctm_id=?;"; 
			PreparedStatement del_Stmt = conn.prepareStatement(delStmt);
			del_Stmt.setInt(1, ctm_id);
			del_Stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return number;
	}
	
}
