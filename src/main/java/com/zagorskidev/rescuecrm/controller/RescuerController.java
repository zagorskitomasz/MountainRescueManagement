package com.zagorskidev.rescuecrm.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.entity.RescuerDetail;
import com.zagorskidev.rescuecrm.entity.States;
import com.zagorskidev.rescuecrm.service.RescuerService;

@Controller
@RequestMapping("/rescuer")
public class RescuerController {

	@Autowired
	private RescuerService rescuerService;
	
	@Autowired
	private States states;
	
	@GetMapping("/all")
	public String showAllRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getAllRescuers();
		model.addAttribute("rescuers", rescuers);
		model.addAttribute("listVersion", "All");
		
		return "list-rescuers";
	}
	
	@GetMapping("/available")
	public String showAvailableRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getAvailableRescuers();
		model.addAttribute("rescuers", rescuers);
		model.addAttribute("listVersion", "Available");
		
		return "list-rescuers";
	}
	
	@GetMapping("/busy")
	public String showBusyRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getBusyRescuers();
		model.addAttribute("rescuers", rescuers);
		model.addAttribute("listVersion", "Busy");
		
		return "list-rescuers";
	}
	
	@GetMapping("/oncall")
	public String showOnCallRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getOnCallRescuers();
		model.addAttribute("rescuers", rescuers);
		model.addAttribute("listVersion", "On Call");
		
		return "list-rescuers";
	}
	
	@GetMapping("/retired")
	public String showRetiredRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getRetiredRescuers();
		model.addAttribute("rescuers", rescuers);
		model.addAttribute("listVersion", "Retired");
		
		return "list-rescuers";
	}
	
	@GetMapping("/details")
	public String showDetails(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		model.addAttribute("rescuer", rescuer);
		return "rescuer-details";
	}
	
	@GetMapping("/add")
	public String addRescuer(Model model) {
		
		Rescuer rescuer = new Rescuer();
		RescuerDetail rescuerDetail = new RescuerDetail();
		rescuer.setRescuerDetail(rescuerDetail);
		
		model.addAttribute("rescuer", rescuer);
		model.addAttribute("states", states.getRescuerStates());
		model.addAttribute("formVersion", "Add");
		
		return "rescuer-form";
	}
	
	@GetMapping("/update")
	public String updateRescuer(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		
		model.addAttribute("rescuer", rescuer);
		model.addAttribute("states", states.getRescuerStates());
		model.addAttribute("formVersion", "Update");
		
		return "rescuer-form";
	}
	
	@PostMapping("/save")
	public String saveRescuer(@Valid @ModelAttribute("rescuer") Rescuer rescuer, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("states", states.getRescuerStates());
			return "rescuer-form";
		}
		else {
			if(rescuer.getId()>0)
				rescuerService.updateRescuer(rescuer);
			else
				rescuerService.addRescuer(rescuer);
			
			return "redirect:/rescuer/all";
		}
	}
	
	@GetMapping("/deleteConfirmation")
	public String showDeleteConfirmation(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		model.addAttribute("rescuer", rescuer);
		
		return "delete-rescuer-confirm";
	}
	
	@GetMapping("/delete")
	public String deleteRescuer(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		
		if(rescuer.getOperations()==null || rescuer.getOperations().isEmpty())
			rescuerService.removeRescuer(id);
		else
			anonimize(rescuer);
		
		return "redirect:/rescuer/all";
	}
	
	@GetMapping("/operations")
	public String showOperations(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		List<Operation> operations = rescuer.getOperations();
		
		model.addAttribute("operations", operations);
		model.addAttribute("listTitle", "Operations of "+rescuer.getFirstName()+" "+rescuer.getLastName());
		
		return "list-operations";
	}
	
	@GetMapping("/commanded")
	public String showCommandedOperations(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		List<OperationDetail> operationDetails = rescuer.getOperationDetails();
		
		List<Operation> operations = new LinkedList<>();
		
		for(OperationDetail operationDetail : operationDetails)
			operations.add(operationDetail.getOperation());
		
		model.addAttribute("operations", operations);
		model.addAttribute("listTitle", "Operations commanded by "+rescuer.getFirstName()+" "+rescuer.getLastName());
		
		return "list-operations";
	}
	
	private void anonimize(Rescuer rescuer) {
		
		rescuer.setFirstName("N/A");
		rescuer.setLastName("N/A");
		rescuer.setState("N/A");
		
		RescuerDetail rescuerDetail = rescuer.getRescuerDetail();
		rescuerDetail.setAddress("N/A");
		rescuerDetail.setEmail("N/A");
		rescuerDetail.setPhone("N/A");
		
		rescuerService.updateRescuer(rescuer);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
