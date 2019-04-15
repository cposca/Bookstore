package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Address", propOrder= {"street","province","zip","country","phone"})
public class AddressBean {

	private int id;
	@XmlElement
	private String street;
	@XmlElement
	private String province;
	@XmlElement
	private String country;
	@XmlElement
	private String zip;
	@XmlElement
	private String phone;
	
	public AddressBean(int id, String street, String province, String country, String zip, String phone) {
		super();
		this.id = id;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public String getStreet() {
		return street;
	}
	public String getProvince() {
		return province;
	}
	public String getCountry() {
		return country;
	}
	public String getZip() {
		return zip;
	}
	public String getPhone() {
		return phone;
	}
}
