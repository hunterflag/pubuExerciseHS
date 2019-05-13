package tw.com.pubu.hunter.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.pubu.hunter.enums.RecordStatus;
import tw.idv.hunter.tool.HunterDebug;

@Entity
@Table(name="Member")
public class MemberBean {
	private Integer pk;	//與表格本身的意義脫鉤, 就是 Primary Key 而已!
	private Integer no;	
	private String account;
	private String password;
	private RecordStatus status=RecordStatus.NORMAL;
	/*
	 *  no 值都是 null, 目標 no = pk,
	 * 	但 物件建構時,  pk 也是 null, 須寫入後才由資料庫產生
	 * 	解：建立後、取出、另賦值、更新 
	 */
	
	public MemberBean() {
		super();
	}

	public MemberBean(Integer pk, String account, String password, RecordStatus status) {
		super();
		this.pk = pk;
		this.account = account;
		this.password = password;
		this.status = status;
	}

	public MemberBean(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}
	
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getAccount() {
		return account;
	}
	
		public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MemberBean [pk=" + pk + ", no=" + no + ", account=" + account + ", password=" + password + ", status="
				+ status + "]";
	}

	
	
}
