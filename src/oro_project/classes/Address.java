package oro_project.classes;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")
public class Address implements Serializable {
	private static final long serialVersionUID = 123456L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String numberOfBuilding;
	private String street;
	private String city;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	private Set<Salesman> salesmen = new HashSet<Salesman>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	private Set<Customer> customers = new HashSet<Customer>();

	public Address(){

	}
	public Address(String numberOfBuilding, String street, String city) {
		this.numberOfBuilding = numberOfBuilding;
		this.street = street;
		this.city = city;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((numberOfBuilding == null) ? 0 : numberOfBuilding.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (numberOfBuilding == null) {
			if (other.numberOfBuilding != null)
				return false;
		} else if (!numberOfBuilding.equals(other.numberOfBuilding))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumberOfBuilding() {
		return numberOfBuilding;
	}
	public void setNumberOfBuilding(String numberOfBuilding) {
		this.numberOfBuilding = numberOfBuilding;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return numberOfBuilding + ", " + street + ", " + city;
	}
	public Set<Salesman> getSalesmen() {
		return salesmen;
	}
	public void setSalesmen(Set<Salesman> salesmen) {
		this.salesmen = salesmen;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
}

