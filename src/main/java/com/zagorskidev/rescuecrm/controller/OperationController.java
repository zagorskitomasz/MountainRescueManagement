package com.zagorskidev.rescuecrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		return "list-operations";
	}
	
	@GetMapping("/current")
	public String showCurrentOperations(Model model) {
		
		List<Operation> operations = operationService.getCurrentOperations();
		model.addAttribute("operations", operations);
		
		return "list-operations";
	}
	
	@GetMapping("/past")
	public String showPastOperations(Model model) {
		
		List<Operation> operations = operationService.getPastOperations();
		model.addAttribute("operations", operations);
		
		return "list-operations";
	}
	
	@GetMapping("/details")
	public String showOperationDetails(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "operation-details";
	}
	
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		
		Operation operation = new Operation();
		OperationDetail operationDetail = new OperationDetail();
		operation.setOperationDetail(operationDetail);
		
		List<Rescuer> candidates = rescuerService.getAvailableRescuers();
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		
		return "operation-form";
	}
	
	@PostMapping("/addRescuer")
	public String addNextRescuer(@ModelAttribute("operation") Operation operation, Model model) {
		
		addCommander(operation);
		
		List<Rescuer> candidates = selectCandidates(operation);
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		
		return "add-rescuers-form";
	}
	
	@GetMapping("/update")
	public String updateOperation(@RequestParam("operationId") int id, Model model) {
		
		Operation operation = operationService.getOperationById(id);
		
		List<Rescuer> candidates = selectCandidates(operation);
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		
		return "operation-form";
	}
	
	@PostMapping("/save")
	public String saveOperation(@ModelAttribute("operation") Operation operation, Model model) {

		addCommander(operation);
		
		if(operation.getId()>0)
			operationService.updateOperation(operation);
		else
			operationService.addOperation(operation);
		
		return "redirect:/operation/all";
	}
	
	@PostMapping("/removeRescuer")
	public String removeRescuer(@RequestParam("rescuerId") int id,
			@ModelAttribute("operation") Operation operation, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		operation.getRescuers().remove(rescuer);
		
		List<Rescuer> candidates = selectCandidates(operation);
		
		model.addAttribute("operation", operation);
		model.addAttribute("candidates", candidates);
		
		return "operation-form";
	}
	
	@GetMapping("/delete")
	public String deleteOperation(@RequestParam("operationId") int id, Model model) {
		
		operationService.removeOperation(id);
		
		return "redirect:/operation/all";
	}

	private List<Rescuer> selectCandidates(Operation operation) {
		
		List<Rescuer> candidates = rescuerService.getAvailableRescuers();
		
		for(Rescuer involved : operation.getRescuers())
			candidates.removeIf(candidate -> candidate.getId()==involved.getId());
		
		return candidates;
	}

	private void addCommander(Operation operation) {
		
		Rescuer commander = operation.getOperationDetail().getRescuer();
		if(operation.getRescuers()==null || !operation.getRescuers().contains(commander))
			operation.addRescuer(commander);
	}
}
