package com.example.client.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.client.service.ServerReaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@Autowired
	ServerReaderService serverReaderService;

	@RequestMapping("/")
	public String readFromServer() throws Exception {

		return serverReaderService.readFromServer();
	}

}