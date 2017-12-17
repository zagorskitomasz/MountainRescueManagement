package com.zagorskidev.rescuecrm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * Mapping object represents details of mountain rescuer.
 * @author tomek
 *
 */
@Entity
@Table(name="rescuer_detail")
public class RescuerDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="address")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String address;
	
	@Column(name="email")
	@Email
	private String email;
	
	@Column(name="phone_number")
	@Pattern(regexp="^[0-9]{9}", message="9 digits required")
	private String phone;
	
	@OneToOne(mappedBy="rescuerDetail", cascade=CascadeType.ALL)
	private Rescuer rescuer;
	
	public RescuerDetail() {}

	public RescuerDetail(String address, String email, String phone) {
		super();
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Rescuer getRescuer() {
		return rescuer;
	}

	public void setRescuer(Rescuer rescuer) {
		this.rescuer = rescuer;
	}
}