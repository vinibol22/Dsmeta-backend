package br.com.dsmeta.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsmeta.entity.Sales;
import br.com.dsmeta.service.SalesService;
import br.com.dsmeta.service.SmsService;

@RestController
@RequestMapping(value ="/sales")
public class SalesController {
	
	private SalesService service;
	
	private SmsService smsService;
		
	
	public SalesController(SalesService service, SmsService smsService) {
		super();
		this.service = service;
		this.smsService = smsService;
	}


	@GetMapping
	public Page<Sales>findSaless(
			@RequestParam(value = "minDate",defaultValue ="")String minDate,
			@RequestParam(value = "maxDate",defaultValue="")String maxDate,Pageable pageable){
		return service.findSales(minDate,maxDate,pageable);
	}
	
	@GetMapping("/{id}/noti")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}
}