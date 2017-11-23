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
import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.service.OperationService;
import com.zagorskidev.rescuecrm.service.RescuerService;

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
		model.addAttribute("operations", operations);
		model.addAttribute("listTitle", "Rescue Operations List");
		
		return "operation/list-operations";
	}
	
	@GetMapping("/details")
	public String showOperationDetails(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "operation/operation-details";
	}
	
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		
		Operation operation = new Operation();
		OperationDetail operationDetail = new OperationDetail();
		operation.setOperationDetail(operationDetail);
		
		for(int i=0; i<3; i++)
			operation.addRescuer(new Rescuer());
		
		List<Rescuer> candidates = rescuerService.getAllRescuers();
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		model.addAttribute("formTitle", "Create Operation");
		
		return "operation/operation-form";
	}
	
	@PostMapping("/save")
	public String saveOperation(@Valid @ModelAttribute("operation") Operation operation, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			List<Rescuer> candidates = rescuerService.getAllRescuers();
			model.addAttribute("operation", operation);
			model.addAttribute("candidates", candidates);
			model.addAttribute("formTitle", "Complete Form");
			return "operation/operation-form";
		}
		else {
			operation.getRescuers().removeIf(rescuer -> rescuer.getId()==0);
			
			if(operation.getId()>0)
				operationService.updateOperation(operation);
			else
				operationService.addOperation(operation);
			
			return "redirect:/operation/all";
		}
	}
	
	@GetMapping("/update")
	public String updateOperation(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		
		List<Rescuer> candidates = rescuerService.getAllRescuers();
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		model.addAttribute("formTitle", "Update Operation");
		
		return "operation/operation-form";
	}
	
	@GetMapping("/delete")
	public String deleteOperation(@RequestParam("operationId") int id, Model model) {

		operationService.removeOperation(id);
		
		return "redirect:/operation/all";
	}
	
	@GetMapping("/deleteConfirmation")
	public String showDeleteConfirmation(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "operation/delete-operation-confirm";
	}
}
