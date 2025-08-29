package com.averydennison.smartjson.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.averydennison.smartjson.constants.ApplicationConstant;
import com.averydennison.smartjson.exception.InvalidInputException;
import com.averydennison.smartjson.model.SmartJson;
import com.averydennison.smartjson.repository.SmartJsonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class SmartJsonService {
	
	@Autowired
	private SmartJsonRepository smartJsonRepository;
	
	public String toJson(MultipartFile file) throws Exception {
		String jsonRes = null;
		SmartJson smartJsonEntity = new SmartJson();
		String status = null;
		String message = null;
		try {
			smartJsonEntity.setXmlRequest(new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
			XmlMapper xmlMapper = new XmlMapper();
			JsonNode node = xmlMapper.readTree(file.getInputStream());
			ObjectMapper jsonMapper = new ObjectMapper();
			jsonRes = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
			status = ApplicationConstant.STATUS_SUCCESS;
			message = ApplicationConstant.STATUS_SUCCESS;
		} catch (Exception exp) {
			status = ApplicationConstant.STATUS_INVALID_INPUT;
			message = exp.getMessage();
			exp.printStackTrace();
			throw new InvalidInputException("Invalid input...");
		} finally {
			smartJsonEntity.setJsonResponse(jsonRes);
			smartJsonEntity.setStatus(status);
			smartJsonEntity.setMessage(message);
			smartJsonRepository.save(smartJsonEntity);
		}
		return jsonRes;
	}
}
