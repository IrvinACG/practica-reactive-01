package com.iacg.app.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonMapper {

	@Autowired
	private ObjectMapper mapper;

	public String convertToJsonString(Object object) {
		String jsonObject = null;
		try {
			jsonObject = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error al convtir Objecto a String, error: {}",e.getMessage());
		}
		return jsonObject;
	}
	
}
