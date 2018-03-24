package oro_project.classes;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	private Integer amount;
	private Date dateOfOrder;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salesman_id", nullable = false)
	private Salesman salesman;

	public Order(Product product, Integer amount, Date dateOfOrder,
			Customer customer, Salesman s) {
		super();
		this.product = product;
		this.amount = amount;
		this.dateOfOrder = dateOfOrder;
		this.customer = customer;
		this.salesman = s;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Salesman getSalesman() {
		return salesman;
	}
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
}
