package tw.com.pubu.hunter.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="shopping_carts")
public class ShoppingCartsBean {
	private Integer sc_id;
	private CustomersBean ctmBean;
	private ProductsBean pdtBean;
	private Integer sc_number = 1;
	private Double sc_price;

	public ShoppingCartsBean() {
		super();
	}
	
	
	public ShoppingCartsBean(CustomersBean ctmBean, ProductsBean pdtBean, Double sc_price) {
		super();
		this.ctmBean = ctmBean;
		this.pdtBean = pdtBean;
		this.sc_price = sc_price;
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
	@JoinColumn(name="ctm_id")
	public CustomersBean getCtmBean() {
		return ctmBean;
	}

	public void setCtmBean(CustomersBean ctmBean) {
		this.ctmBean = ctmBean;
	}

	@OneToOne
	@JoinColumn(name="pd_id")
	public ProductsBean getPdtBean() {
		return pdtBean;
	}

	public void setPdtBean(ProductsBean pdtBean) {
		this.pdtBean = pdtBean;
	}


	public Double getSc_price() {
		return sc_price;
	}

	public void setSc_price(Double sc_price) {
		this.sc_price = sc_price;
	}

	public Integer getSc_number() {
		return sc_number;
	}

	public void setSc_number(Integer sc_number) {
		this.sc_number = sc_number;
	}

	@Override
	public String toString() {
		return "ShoppingCartsBean [sc_id=" + sc_id + ", ctmBean=" + ctmBean + ", pdtBean=" + pdtBean + ", sc_price="
				+ sc_price + ", sc_number=" + sc_number + "]";
	}
	
	
	
}
