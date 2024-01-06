package springjpa.example.relationships.entity.embeddables;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	private String line1, line2, city;

	public Address() {
	}; // for hibernate

	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", line3=" + city + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, line1, line2);
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
		return Objects.equals(city, other.city) && Objects.equals(line1, other.line1)
				&& Objects.equals(line2, other.line2);
	}

	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
