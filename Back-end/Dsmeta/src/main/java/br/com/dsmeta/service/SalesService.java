package br.com.dsmeta.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dsmeta.entity.Sales;
import br.com.dsmeta.repository.SalesRepository;

@Service
public class SalesService {
	
	private SalesRepository repository;
	
	


	public SalesService(SalesRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Page<Sales>findSales(String minDate,String maxDate,Pageable pageable){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.equals("")?today.minusDays(365):LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("")?today : LocalDate.parse(maxDate);
		
		return repository.findSales(min,max,pageable);
	}
	
	
}
