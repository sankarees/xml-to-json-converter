package com.averydennison.smartjson.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.averydennison.smartjson.exception.InvalidInputException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class SmartJsonService {
	public String toJson(MultipartFile file) throws Exception {
		try {
			XmlMapper xmlMapper = new XmlMapper();
			JsonNode node = xmlMapper.readTree(file.getInputStream());
			ObjectMapper jsonMapper = new ObjectMapper();
			return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new InvalidInputException("Invalid input...");
		}
	}
}
