package com.minwoo.siteparser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minwoo.siteparser.dto.ParsingInput;
import com.minwoo.siteparser.dto.ParsingOutput;
import com.minwoo.siteparser.service.ParsingService;

@Controller
public class ParsingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParsingController.class);
	
	@Autowired
	ParsingService parsingService;
	
	@GetMapping("/console")
	public String getParsingConsole() {
		LOGGER.info("Console Page");

		return "parsingConsole";
	}
	
	@ResponseBody
	@PostMapping("/parsing-output")
	public ParsingOutput parseSiteAndReturnOutput(ParsingInput parsingInput) {
		LOGGER.info("Parsing Start - URL : {}", parsingInput.getUrl());
		
		return parsingService.scrapSiteByType(parsingInput);
	}
	
}
