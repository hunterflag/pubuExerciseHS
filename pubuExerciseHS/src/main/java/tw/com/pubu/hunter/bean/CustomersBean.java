package tw.com.pubu.hunter.bean;

//@Entity
//@Table(name="customers")
public class CustomersBean {
	private Integer ctm_id ;	
	private String ctm_account;
	private String ctm_password;

	public CustomersBean() {
		super();
	}
	
	public CustomersBean(String ctm_account, String ctm_password) {
		super();
		this.ctm_account = ctm_account;
		this.ctm_password = ctm_password;
	}



//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCtm_id() {
		return ctm_id;
	}
	public void setCtm_id(Integer ctm_id) {
		this.ctm_id = ctm_id;
	}
	
	public String getCtm_account() {
		return ctm_account;
	}
	public void setCtm_account(String ctm_account) {
		this.ctm_account = ctm_account;
	}
	
	public String getCtm_password() {
		return ctm_password;
	}
	public void setCtm_password(String ctm_password) {
		this.ctm_password = ctm_password;
	}
	
	@Override
	public String toString() {
		return "CustomersBean [ctm_id=" + ctm_id + ", ctm_account=" + ctm_account + ", ctm_password=" + ctm_password
				+ "]";
	}
	
}
