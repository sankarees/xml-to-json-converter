package com.averydennison.smartjson.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "xml_json_log")
@Data
public class SmartJson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(name = "req_xml", columnDefinition = "LONGTEXT")
	private String xmlRequest;
	
	@Column(name = "res_json", columnDefinition = "JSON")
	private String jsonResponse;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public String getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(String xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	public String getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
