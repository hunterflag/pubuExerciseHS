package unUsed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.pubu.hunter.enums.RecordStatus;

@Entity
@Table(name="Product")
public class ProductBean {
	private Integer pk;
	private Integer no;
	private String name;
	private Double price;
	private RecordStatus status = RecordStatus.NORMAL;

	public ProductBean() {
		super();
	}

	public ProductBean(Integer pk, Integer no, String name, Double price, RecordStatus status) {
		super();
		this.pk = pk;
		this.no = no;
		this.name = name;
		this.price = price;
		this.status = status;
	}

	public ProductBean(String name, Double price) {
		super();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "ProductBean [pk=" + pk + ", no=" + no + ", name=" + name + ", price=" + price + ", status=" + status
				+ "]";
	}
	
	
	
	
	
}
