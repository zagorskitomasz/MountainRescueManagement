package com.zagorskidev.rescuecrm.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="rescuer")
public class Rescuer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String lastName;
	
	@Column(name="state")
	private String state;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="rescuer_detail_id")
	@Valid
	private RescuerDetail rescuerDetail;
	
	@ManyToMany(cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JoinTable(name="operation_rescuer",
			joinColumns = @JoinColumn(name="rescuer_id"),
			inverseJoinColumns = @JoinColumn(name="operation_id"))
	private List<Operation> operations;
	
	@OneToMany(mappedBy="rescuer", cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	private List<OperationDetail> operationDetails;

	public Rescuer() {}

	public Rescuer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void addOperation(Operation operation) {
		if(operations==null)
			operations = new LinkedList<>();
		
		operations.add(operation);
	}
	
	public void addOperationDetail(OperationDetail operationDetail) {
		if(operationDetails==null)
			operationDetails = new LinkedList<>();
		
		operationDetails.add(operationDetail);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RescuerDetail getRescuerDetail() {
		return rescuerDetail;
	}

	public void setRescuerDetail(RescuerDetail rescuerDetail) {
		this.rescuerDetail = rescuerDetail;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<OperationDetail> getOperationDetails() {
		return operationDetails;
	}

	public void setOperationDetails(List<OperationDetail> operationDetails) {
		this.operationDetails = operationDetails;
	}
}
