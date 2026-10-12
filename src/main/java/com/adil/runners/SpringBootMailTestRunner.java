package com.adil.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adil.service.IPurchaseMgmtService;

@Component
public class SpringBootMailTestRunner implements CommandLineRunner {
	
	@Autowired
	private IPurchaseMgmtService service;

	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			String resultMsg = service.shopping(new String[] {"trouser","shirt","hat"},new Double[] {1000.0,2000.0,500.0},new String[] {"mdadilfaiz1@gmail.com","mdadilfaiz058@gmail.com"});
			System.out.println(resultMsg);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
