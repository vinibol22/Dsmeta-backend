package br.com.dsmeta.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.dsmeta.entity.Sales;
import br.com.dsmeta.repository.SalesRepository;

@Service
public class SmsService {
	
	private SalesRepository salesRepository;
	
	
	
	public SmsService(SalesRepository salesRepository) {
		super();
		this.salesRepository = salesRepository;
	}

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long salesId) {
		Sales sale = salesRepository.findById(salesId).get();
		String date = sale.getDate().getMonthValue()+"/"+sale.getDate().getYear();
		String msg = "vendedor "+sale.getSellerName()+"foi destaque em "+date+" Com um valor de R$"+String.format("%.2f",sale.getAmount()) 
		
				;
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from,msg).create();

		System.out.println(message.getSid());
	}
}