package unUsed;

import java.util.List;

public interface ShoppingCartDao {
	public Object insert(ShoppingCartBean insObj);
	public boolean delete(ShoppingCartBean delObj);
	public ShoppingCartBean getByPk(Integer pk);
	public ShoppingCartBean getByPk(int ipk);
	public List<ShoppingCartBean> getItemsByMember(int memberId);
	public List<ShoppingCartBean> getAlls();
	public boolean update(ShoppingCartBean updObj);
}
