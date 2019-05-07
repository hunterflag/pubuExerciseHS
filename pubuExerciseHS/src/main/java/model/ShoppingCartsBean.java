package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import bean.Customers;

@Entity
@Table(name="shopping_carts")
public class ShoppingCartsBean {
	private Integer sc_id;
//	private Integer ctm_id;
	private Customers ctmb;
//	private Integer pd_id;
	private ProductsBean pdb;
	private Integer sc_price;
	private Integer sc_number;

	public ShoppingCartsBean() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSc_id() {
		return sc_id;
	}

	public void setSc_id(Integer sc_id) {
		this.sc_id = sc_id;
	}

	/*
	public Integer getCtm_id() {
		return ctm_id;
	}

	public void setCtm_id(Integer ctm_id) {
		this.ctm_id = ctm_id;
	}

	public Integer getPd_id() {
		return pd_id;
	}

	public void setPd_id(Integer pd_id) {
		this.pd_id = pd_id;
	}
*/
	@OneToOne
	@JoinColumn(name="FK_CsutomersBean_id")
	public Customers getCtmb() {
		return ctmb;
	}

	public void setCtmb(Customers ctmb) {
		this.ctmb = ctmb;
	}

	@OneToOne
	@JoinColumn(name="FK_ProductsBean_id")
	public ProductsBean getPdb() {
		return pdb;
	}

	public void setPdb(ProductsBean pdb) {
		this.pdb = pdb;
	}


	public Integer getSc_price() {
		return sc_price;
	}

	public void setSc_price(Integer sc_price) {
		this.sc_price = sc_price;
	}

	public Integer getSc_number() {
		return sc_number;
	}

	public void setSc_number(Integer sc_number) {
		this.sc_number = sc_number;
	}
	
	
	
}
