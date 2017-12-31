package com.zagorskidev.rescuecrm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.service.OperationService;
import com.zagorskidev.rescuecrm.service.RescuerService;

/**
 * Handles requests related to rescue operation CRUD operations
 * @author tomek
 *
 */
@Controller
@RequestMapping("/operation")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private RescuerService rescuerService;
	
	@GetMapping("/all")
	public String showAllOperations(Model model) {
		
		List<Operation> operations = operationService.getAllOperations();
		modelOperationsList(model, operations);
		
		return "operation/list-operations";
	}
	
	@GetMapping("/running")
	public String showRunningOperations(Model model) {
		
		List<Operation> operations = operationService.getRunningOperations();
		modelOperationsList(model, operations);
		
		return "operation/list-operations";
	}
	
	@GetMapping("/finished")
	public String showFinishedOperations(Model model) {
		
		List<Operation> operations = operationService.getFinishedOperations();
		modelOperationsList(model, operations);
		
		return "operation/list-operations";
	}

	/**
	 * Adding prepared operations list to model
	 * 
	 * @param model
	 * @param operations
	 */
	private void modelOperationsList(Model model, List<Operation> operations) {
		
		model.addAttribute("operations", operations);
		model.addAttribute("listTitle", "Rescue Operations List");
	}
	
	@GetMapping("/details")
	public String showOperationDetails(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "operation/operation-details";
	}
	
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		
		Operation operation = operationService.createEmptyOperation();
		String formTitle = "Create Operation";
		
		setOperationFormModel(operation, model, formTitle);
		
		return "operation/operation-form";
	}
	
	@PostMapping("/save")
	public String saveOperation(@Valid @ModelAttribute("operation") Operation operation, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			String formTitle = "Correct Form";
			
			setOperationFormModel(operation, model, formTitle);
			
			return "operation/operation-form";
		}
		else {
			sendOperationToService(operation);
			
			return "redirect:/operation/all";
		}
	}
	
	@GetMapping("/update")
	public String updateOperation(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		String formTitle = "Update Operation";
		
		setOperationFormModel(operation, model, formTitle);
		
		return "operation/operation-form";
	}
	
	@GetMapping("/delete")
	public String deleteOperation(@RequestParam("operationId") int id) {

		operationService.removeOperation(id);
		
		return "redirect:/operation/all";
	}
	
	@GetMapping("/deleteConfirmation")
	public String showDeleteConfirmation(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "operation/delete-operation-confirm";
	}
	
	@GetMapping("/finish")
	public String finishOperation(@RequestParam("operationId") int id) {
		
		operationService.finishOperation(id);
		
		return "redirect:/operation/all";
	}

	/**
	 * Adds to model necessary beans for creating/updating operation form
	 * @param operation
	 * @param model
	 * @param formTitle
	 */
	private void setOperationFormModel(Operation operation, Model model, String formTitle) {
		
		List<Rescuer> candidates = rescuerService.prepareCandidates(operation);
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		model.addAttribute("formTitle", formTitle);
	}

	/**
	 * Saves or updates operation depending on its existence
	 * @param operation
	 */
	private void sendOperationToService(Operation operation) {
		
		operation.getRescuers().removeIf(rescuer -> rescuer.getId() == 0);
		
		if(operation.getId()>0)
			operationService.updateOperation(operation);
		else
			operationService.addOperation(operation);
	}
}
