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

import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.entity.RescuerDetail;
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
		
		return "list-rescuers";
	}
	
	@GetMapping("/available")
	public String showAvailableRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getAvailableRescuers();
		model.addAttribute("rescuers", rescuers);
		
		return "list-rescuers";
	}
	
	@GetMapping("/busy")
	public String showBusyRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getBusyRescuers();
		model.addAttribute("rescuers", rescuers);
		
		return "list-rescuers";
	}
	
	@GetMapping("/oncall")
	public String showOnCallRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getOnCallRescuers();
		model.addAttribute("rescuers", rescuers);
		
		return "list-rescuers";
	}
	
	@GetMapping("/retired")
	public String showRetiredRescuers(Model model) {
		
		List<Rescuer> rescuers = rescuerService.getRetiredRescuers();
		model.addAttribute("rescuers", rescuers);
		
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
		
		return "rescuer-form";
	}
	
	@GetMapping("/update")
	public String updateRescuer(@RequestParam("rescuerId") int id, Model model) {
		
		Rescuer rescuer = rescuerService.getRescuerById(id);
		
		model.addAttribute("rescuer", rescuer);
		
		return "rescuer-form";
	}
	
	@PostMapping("/save")
	public String saveRescuer(@ModelAttribute("rescuer") Rescuer rescuer) {
		
		if(rescuer.getId()>0)
			rescuerService.updateRescuer(rescuer);
		else
			rescuerService.addRescuer(rescuer);
		
		return "redirect:/rescuer/all";
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
}
