package com.zagorskidev.rescuecrm.entity;

import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Mapping object represents rescue operation.
 * @author tomek
 *
 */
@Entity
@Table(name="operation")
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="destination")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String destination;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="operation_detail_id")
	@Valid
	private OperationDetail operationDetail;
	
	@Column(name="state")
	private String state;
	
	@ManyToMany(cascade= {},
			fetch=FetchType.EAGER)
	@JoinTable(name="operation_rescuer",
			joinColumns = @JoinColumn(name="operation_id"),
			inverseJoinColumns = @JoinColumn(name="rescuer_id"))
	private List<Rescuer> rescuers;
	
	public Operation() {}

	public Operation(String destination) {
		super();
		this.destination = destination;
	}
	
	public void addRescuer(Rescuer rescuer) {
		if(rescuers==null)
			rescuers = new ArrayList<>();
		
		rescuers.add(rescuer);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public OperationDetail getOperationDetail() {
		return operationDetail;
	}

	public void setOperationDetail(OperationDetail operationDetail) {
		this.operationDetail = operationDetail;
	}

	public List<Rescuer> getRescuers() {
		return rescuers;
	}

	public void setRescuers(List<Rescuer> rescuers) {
		this.rescuers = rescuers;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isRunning() {
		return "Running".equals(state);
	}
	
	public void setRunning() {
		this.state = "Running";
	}
	
	public void setFinished() {
		this.state = "Finished";
	}
}
