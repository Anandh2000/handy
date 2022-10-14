package com.finzly.loanApplicationManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.errorHandler.ErrorHandlerService;
import com.finzly.loanApplicationManagement.response.ErrorResponse;
import com.finzly.loanApplicationManagement.serviceImplementation.LoanServiceImpl;
@SpringBootApplication
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LoanApplicationController{
	@Autowired
	LoanServiceImpl service;
	@Autowired
	private ErrorHandlerService errorHandlerService;
	
	public LoanApplicationController() {}

//	@GetMapping("/menu")
//	public  EntityModel<MenuDisplayer> displayMenuWithLinks(){
//		EntityModel<MenuDisplayer> entity = EntityModel.of(new MenuDisplayer("BookTrade", "PrintTrade", "Exit"));
//		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).printTrade());
//		entity.add(link1.withRel("PrintTrade"));
//		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).bookTrade(new User()));
//		entity.add(link2.withRel("BookTrade"));
//		WebMvcLinkBuilder link3 = linkTo(methodOn(this.getClass()).exit(new String()));
//		entity.add(link3.withRel("Exit"));
//		WebMvcLinkBuilder link4 = linkTo(methodOn(this.getClass()).formatOfBooking());
//		entity.add(link4.withRel("To know format of booking"));
//		return entity;
//	}
	
	@PostMapping("/createNewLoan")
	public ResponseEntity<?> loanBook(@RequestBody @Valid LoanDetails details){
		ResponseEntity<?> storedLoan = service.applyLoan(details);
		return storedLoan;
	}
	
	@GetMapping("/loanDetails")
	public Object printDetails(){
		return service.getAllDetails();
	}
	
	@GetMapping("/loan")
	public Object printDetail(){
		return new LoanDetails();
	}
	
	@GetMapping("/schedule")
	public Object printDetai(){
		return new PaymentSchedule();
	}
	
	
//	@PostMapping("/Exit")
//	public Object exit(@RequestBody String value) {
//		RedirectView redirect = new RedirectView(); 
//		if (value.equalsIgnoreCase("yes")) {
//			redirect.setUrl("Exit/message");
//			return redirect;
//		}
//		else if(value.equalsIgnoreCase("no")) {
//			redirect.setUrl("menu");
//			return redirect;
//		}
//		else {
//			ErrorResponse errorResponse = errorHandlerService.inValidEntry("Invalid Entry", 400, "Enter yes or no");
//			return errorResponse;
//		}
//	}
	
	@GetMapping("/Exit/message")
	public String exitMessage(){
		return "Bye Have a good day";
	}
	


}
