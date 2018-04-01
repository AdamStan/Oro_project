package oro_project.classes;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Salesmans")
public class Salesman implements Serializable{
	private static final long serialVersionUID = 3433902234236139248L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	private LocalDate whenStarted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	private Double salary;
	private Double bonus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesman")
	private Set<Order> orders = new HashSet<Order>();

	public Salesman(String name, String surname, LocalDate whenStarted,
			Address address, Double salary, Double bonus) {
		this.name = name;
		this.surname = surname;
		this.whenStarted = whenStarted;
		this.address = address;
		this.salary = salary;
		this.bonus = bonus;
	}

	public Salesman(Salesman p) {
		this.name = p.name;
		this.surname = p.surname;
		this.whenStarted = p.whenStarted;
		this.address = p.address;
		this.salary = p.salary;
		this.bonus = p.bonus;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getWhenStarted() {
		return whenStarted;
	}

	public void setWhenStarted(LocalDate whenStarted) {
		this.whenStarted = whenStarted;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return name + ", " + surname;
	}

}
