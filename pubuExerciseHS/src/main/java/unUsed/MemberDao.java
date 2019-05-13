package unUsed;

import java.util.List;

public interface MemberDao {
	public Object insert(MemberBean insObj);
	public boolean delete(MemberBean delObj);
	public MemberBean getByPk(Integer pk);
	public MemberBean getByPk(int ipk);
	public List<MemberBean> getAlls();
	public boolean update(MemberBean updObj);
}
