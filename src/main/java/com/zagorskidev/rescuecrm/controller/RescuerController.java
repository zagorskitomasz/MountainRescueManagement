package com.zagorskidev.rescuecrm.controller;

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
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.service.RescuerService;

@Controller
@RequestMapping("/rescuer")
public class RescuerController {

	@Autowired
	private RescuerService rescuerService;
	
	@GetMapping("/all")
	public String showAllRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getAllRescuers();
		model.addAttribute("rescuers", rescuers);
		
		return "rescuer/list-rescuers";
	}
	
	@GetMapping("/details")
	public String showDetails(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		model.addAttribute("rescuer", rescuer);
		return "rescuer/rescuer-details";
	}
	
	@GetMapping("/add")
	public String addRescuer(Model model) {
		
		Rescuer rescuer = rescuerService.createEmptyRescuer();
		
		model.addAttribute("rescuer", rescuer);
		model.addAttribute("formTitle", "Add Rescuer");
		
		return "rescuer/rescuer-form";
	}
	
	@GetMapping("/update")
	public String updateRescuer(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		
		model.addAttribute("rescuer", rescuer);
		model.addAttribute("formTitle", "Update Rescuer");
		
		return "rescuer/rescuer-form";
	}
	
	@PostMapping("/save")
	public String saveRescuer(@Valid @ModelAttribute("rescuer") Rescuer rescuer, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("formTitle", "Correct Form");
			return "rescuer/rescuer-form";
		}
		else {
			sendRescuerToService(rescuer);
			return "redirect:/rescuer/all";
		}
	}
	
	@GetMapping("/deleteConfirmation")
	public String showDeleteConfirmation(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		model.addAttribute("rescuer", rescuer);
		
		return "rescuer/delete-rescuer-confirm";
	}
	
	@GetMapping("/delete")
	public String deleteRescuer(@RequestParam("rescuerId") int id, Model model) {
		
		rescuerService.removeRescuer(id);
		
		return "redirect:/rescuer/all";
	}
	
	@GetMapping("/operations")
	public String showOperations(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		List<Operation> operations = rescuer.getOperations();
		
		model.addAttribute("operations", operations);
		model.addAttribute("listTitle", "Operations of "+rescuer);
		
		return "operation/list-operations";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	private void sendRescuerToService(Rescuer rescuer) {
		
		if(rescuer.getId()>0) {
			rescuerService.updateRescuer(rescuer);
		}
		else
			rescuerService.addRescuer(rescuer);
	}
}
