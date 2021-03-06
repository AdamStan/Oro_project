package oro_project.classes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;
	private Integer amount;
	private Double priceEach;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<Order> orders = new HashSet<Order>();

	public Product(){

	}
	public Product(String name, Integer amount, Double priceEach) {
		this.name = name;
		this.amount = amount;
		this.priceEach = priceEach;
	}
	public Product(Product p) {
		this.name = p.name;
		this.amount = p.amount;
		this.priceEach = p.priceEach;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getPriceEach() {
		return priceEach;
	}
	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public StringProperty idProperty() {
		return new SimpleStringProperty(String.valueOf(this.id));
	}
	public StringProperty nameProperty() {
		return new SimpleStringProperty(this.name);
	}
	public StringProperty priceProperty() {
		return new SimpleStringProperty(String.valueOf(this.priceEach));
	}
	public StringProperty amountProperty() {
		return new SimpleStringProperty(String.valueOf(this.amount));
	}
	@Override
	public String toString() {
		return name;
	}
}
