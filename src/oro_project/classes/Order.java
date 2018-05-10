package oro_project.classes;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	private Double amount;
	private Date dateOfOrder;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salesman_id", nullable = false)
	private Salesman salesman;

	public Order(){

	}
	public Order(Product product, Double amount, LocalDate dateOfOrder,
			Customer customer, Salesman s) {
		super();
		this.product = product;
		this.amount = amount;
		this.dateOfOrder = Date.from(dateOfOrder.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder =
				Date.from(dateOfOrder.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
	public StringProperty idProperty() {
		return new SimpleStringProperty(String.valueOf(this.id));
	}
	public StringProperty dateProperty() {
		return new SimpleStringProperty(this.dateOfOrder.toString());
	}
	public StringProperty productNameProperty() {
		return new SimpleStringProperty(this.product.toString());
	}
	public StringProperty clientNameProperty() {
		return new SimpleStringProperty(this.customer.toString());
	}
	public StringProperty salesmanNameProperty() {
		return new SimpleStringProperty(this.salesman.toString());
	}
}
