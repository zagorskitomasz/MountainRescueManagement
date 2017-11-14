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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="operation")
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="destination")
	private String destination;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="operation_detail_id")
	private OperationDetail operationDetail;
	
	@ManyToMany(cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JoinTable(name="operation_rescuer",
			joinColumns = @JoinColumn(name="operation_id"),
			inverseJoinColumns = @JoinColumn(name="rescuer_id"))
	private List<Rescuer> rescuers;
	
	private String[] states = {"current", "past"};
	
	public Operation() {}

	public Operation(String destination) {
		super();
		this.destination = destination;
	}
	
	public void addRescuer(Rescuer rescuer) {
		if(rescuers==null)
			rescuers = new LinkedList<>();
		
		rescuers.add(rescuer);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String present) {
		this.state = present;
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

	public String[] getStates() {
		return states;
	}

	public void setStates(String[] states) {
		this.states = states;
	}
}
