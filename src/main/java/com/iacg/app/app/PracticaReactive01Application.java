package com.iacg.app.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.javafaker.Faker;
import com.iacg.app.app.services.IAddressService;
import com.iacg.app.app.services.IProductService;
import com.iacg.app.app.services.IUserService;
import com.iacg.app.app.services.dtos.AddressDTO;
import com.iacg.app.app.services.dtos.ProductDTO;
import com.iacg.app.app.services.dtos.UserDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@EnableScheduling
@Slf4j
@SpringBootApplication
public class PracticaReactive01Application implements CommandLineRunner{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired 
	private IProductService productService;
	
	@Autowired
	private Faker faker;
	
	public static void main(String[] args) {
		SpringApplication.run(PracticaReactive01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		createUser();
		createProducts();
	}
	
	public void createUser() {
		
		userService.findAll()
			.flatMap(users -> {
				List<UserDTO> usersDTO = new ArrayList<>();
				if(users.isEmpty()) {
					for(int i=0; i<1; i++) {
						String name = faker.name().fullName();
						String email = faker.internet().emailAddress();
						String phone = faker.phoneNumber().cellPhone();
						UserDTO dto = new UserDTO(null, name, email, phone);
						usersDTO.add(dto);
					}
				}
				return Mono.just(usersDTO);
			}).subscribe(usersDTO -> {
				for(UserDTO userDTO: usersDTO) {
					userService.save(userDTO)
					.subscribe(this::createAddresses);
				}
			});
		
	}
	
	public void createAddresses(UserDTO dto) {
		
		Random r = new Random();
		int totalAddress = r.nextInt(1,3);
		for(int i=0; i<totalAddress; i++) {
			AddressDTO dtoAd = null;
			log.info("Dirrecion: {}",faker.address().fullAddress());
			String street = faker.address().streetName();
			String number = faker.address().streetAddressNumber();
			String state = faker.address().state();
			String zipCode = faker.address().zipCode();
			String country = faker.address().country();
			Long userRandom = dto.getId();
			dtoAd = new AddressDTO(null, street, number, state, zipCode, country, userRandom);
			addressService.save(dtoAd).subscribe();
		}
		
	}

	public void createProducts() {
		productService.findAll()
		.subscribe(products -> {
			if(products.isEmpty()) {
				for(int i=0;i<300;i++) {
					String name = faker.commerce().productName();
					String code = faker.commerce().promotionCode();
					String price = faker.commerce().price();
					ProductDTO dto = new ProductDTO(null, name, code, price);
					productService.save(dto).subscribe();
				}
			}
		});
		
	}
}
