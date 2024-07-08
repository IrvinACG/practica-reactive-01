package com.iacg.app.app.services;

import java.util.Objects;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.iacg.app.app.services.dtos.OrderDTO;
import com.iacg.app.app.utils.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderKafka implements IProducerKafka{
	
	private static final String TOPIC = "topic-prueba";
	
	private final JsonMapper mapper;
	
	private final KafkaTemplate<String, String> kafkaTemplate;

	
	public OrderKafka(JsonMapper mapper, KafkaTemplate<String, String> kafkaTemplate) {
		this.mapper = mapper;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void create(OrderDTO dto) {
		log.info("Kafka: {}",dto.toString());
		if(Objects.nonNull(dto)) {
			String messsage = mapper.convertToJsonString(dto);
			if(Objects.nonNull(messsage)) {
				sendMessage(messsage);
			}
		}		
		
	}
	
	@Override
	public void sendMessage(String menssage) {
		kafkaTemplate.send(TOPIC, menssage);
		
	}

}
