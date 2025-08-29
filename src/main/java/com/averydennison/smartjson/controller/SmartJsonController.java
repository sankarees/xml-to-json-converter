package com.averydennison.smartjson.controller;

import com.averydennison.smartjson.exception.InvalidInputException;
import com.averydennison.smartjson.service.SmartJsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/convert")
public class SmartJsonController {
	
	@Autowired
	SmartJsonService smartJsonService;

	@PostMapping(value = "/xml-to-json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile file) {
		String jsonStr = null;
		
		try {
			jsonStr = smartJsonService.toJson(file);
		} catch (InvalidInputException ive) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: XML structure is malformed.");
		} catch (Exception exp) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Contact system manager.");
		}
        return ResponseEntity.ok(jsonStr);
    }
}
