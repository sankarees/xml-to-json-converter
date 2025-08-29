package com.averydennison.smartjson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.averydennison.smartjson.model.SmartJson;

public interface SmartJsonRepository extends JpaRepository<SmartJson, Long> {

}
