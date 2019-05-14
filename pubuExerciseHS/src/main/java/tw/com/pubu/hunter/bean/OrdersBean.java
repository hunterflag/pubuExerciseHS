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

import tw.com.pubu.hunter.enums.OrderStatus;

@Entity
@Table(name="orders")
public class OrdersBean {
	private Integer od_id;	
	private Timestamp od_time = new Timestamp(new Date().getTime());	//下單時間
	private CustomersBean ctmBean;
	private Integer od_total_price;
	private OrderStatus od_state = OrderStatus.OPEN;

	public OrdersBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersBean(Integer od_id, Timestamp od_time, CustomersBean ctmBean, Integer od_total_price,
			OrderStatus od_state) {
		super();
		this.od_id = od_id;
		this.od_time = od_time;
		this.ctmBean = ctmBean;
		this.od_total_price = od_total_price;
		this.od_state = od_state;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOd_id() {
		return od_id;
	}

	public void setOd_id(Integer od_id) {
		this.od_id = od_id;
	}

	public Timestamp getOd_time() {
		return od_time;
	}

	public void setOd_time(Timestamp od_time) {
		this.od_time = od_time;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ctm_id")
	public CustomersBean getCtmBean() {
		return ctmBean;
	}

	public void setCtmBean(CustomersBean ctmBean) {
		this.ctmBean = ctmBean;
	}

	public Integer getOd_total_price() {
		return od_total_price;
	}

	public void setOd_total_price(Integer od_total_price) {
		this.od_total_price = od_total_price;
	}

	public OrderStatus getOd_state() {
		return od_state;
	}

	public void setOd_state(OrderStatus od_state) {
		this.od_state = od_state;
	}

	@Override
	public String toString() {
		return "OrdersBean [od_id=" + od_id + ", od_time=" + od_time + ", ctmBean=" + ctmBean + ", od_total_price="
				+ od_total_price + ", od_state=" + od_state + "]";
	}
	
	
	
}
