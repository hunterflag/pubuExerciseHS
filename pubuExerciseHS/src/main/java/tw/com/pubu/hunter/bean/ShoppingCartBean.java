package tw.com.pubu.hunter.bean;

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
@Table(name="ShoppingCart")
public class ShoppingCartBean {
	private Integer pk;
	private Integer no;
	private MemberBean member;	//會員
	private ProductBean product;//商品
	private Double price;	//售價
	private Integer number;	//數量
	private RecordStatus status = RecordStatus.NORMAL;
			

	public ShoppingCartBean() {
		super();
	}

	public ShoppingCartBean(MemberBean member, ProductBean product, Integer number, Double price) {
		super();
		this.member = member;
		this.product = product;
		this.number = number;
		this.price = price;
	}
/*
	public ShoppingCartBean(MemberBean member, ProductBean product, int number, int price) {
		super();
		this.member = member;
		this.product = product;
		this.number = (Integer) number;
		this.price = (Integer) price;
		
	}
*/

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


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_Product_Id")
	public ProductBean getProduct() {
		return product;
	}


	public void setProduct(ProductBean product) {
		this.product = product;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public RecordStatus getStatus() {
		return status;
	}


	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShoppingCartBean [pk=" + pk + ", no=" + no + ", member=" + member + ", product=" + product + ", price="
				+ price + ", number=" + number + ", status=" + status + "]";
	}
	
	

}
