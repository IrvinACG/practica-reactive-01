package com.iacg.app.app.services;

import java.util.Date;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iacg.app.app.services.dtos.OrderDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OrderTask {
	
	private final IUserService userService;
	
	private final IAddressService addressService;

	private final IProductService productService;
	
	private final OrderKafka orderKafka;
	
	public static Long NUMBER_ORDER = 1L;
	
	private final Random random;
	
	
	public OrderTask(IUserService userService, IAddressService addressService, IProductService productService, OrderKafka orderKafka) {
		this.userService = userService;
		this.addressService = addressService;
		this.productService = productService;
		this.orderKafka = orderKafka;
		this.random = new Random();
	}
	
	@Scheduled(initialDelay = 20000,fixedDelay = 5000)
	public void createOrder() {
		userService.count()	//Obtiene total de usuarios
			.flatMap(totalUsers -> {
				Long id = random.nextLong(1, totalUsers); //Obtiene ID aleatorio
				return userService.findById(id)	//Obtiene usuario por ID
									.flatMap(user -> {
										OrderDTO order = new OrderDTO();
										order.setUser(user);
										return Mono.just(order);
									});				
				
			}).flatMap(order -> {	//Obtiene direccion de usuario
				return addressService.findAll(order.getUser().getId())
						.flatMap(addresses -> {
							order.setAddresses(addresses);
							return Mono.just(order);
						});
			}).flatMap(order -> {	//Obtiene un producto aleatorio
				return productService.findAll()
						.flatMap(products -> {
							Long id = random.nextLong(1, products.size());
							order.setProduct(products.get(id.intValue()));
							return Mono.just(order);
						});
			}).subscribe(order -> {	//Envia orden a Kafka
				log.info("Se crea nueva orden, id:{}",OrderTask.NUMBER_ORDER);
				order.setDate(new Date());
				orderKafka.create(order);
				OrderTask.NUMBER_ORDER++;
			});
	}
	
}
