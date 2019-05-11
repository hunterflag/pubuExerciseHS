package tw.com.pubu.hunter.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tw.com.pubu.hunter.enums.RecordStatus;

@Entity
@Table(name="Order")
public class OrderBean {
	private Integer pk;
	private Integer no;
	private Timestamp time = new Timestamp(new Date().getTime());	//下單時間
	private MemberBean member;	//會員
	private Double price;		//總金額
	private RecordStatus status = RecordStatus.NORMAL;
			

	public OrderBean() {
		super();
	}

	
	public OrderBean(MemberBean member, Double price) {
		super();
		this.member = member;
		this.price = price;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_Member_Id")
	public MemberBean getMember() {
		return member;
	}


	public void setMember(MemberBean member) {
		this.member = member;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public RecordStatus getStatus() {
		return status;
	}


	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "OrderBean [pk=" + pk + ", no=" + no + ", member=" + member + ", time=" + time + ", price=" + price
				+ ", status=" + status + "]";
	}


}
