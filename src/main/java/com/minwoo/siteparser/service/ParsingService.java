package com.minwoo.siteparser.service;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.minwoo.siteparser.constants.Constants;
import com.minwoo.siteparser.dto.ParsingInput;
import com.minwoo.siteparser.dto.ParsingOutput;

@Service
public class ParsingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParsingService.class);
	
	public ParsingOutput scrapSiteByType(ParsingInput parsingInput) {
		String scrapedString = null; 

		try {
			Document doc = Jsoup.connect(parsingInput.getUrl()).get();
			
			if(Constants.ONLY_TEXT.equals(parsingInput.getType())) {
				scrapedString = doc.text();
			} else {
				scrapedString = doc.toString(); // html 태그를 포함한 모든 문자열
			}
		} catch (IOException e) {
			LOGGER.error("make doc is fail : Check connection");
			LOGGER.error(e.toString());
		}
		
		return divideSize(parseSrapedString(scrapedString), parsingInput.getSize());
	}

	private String parseSrapedString(String scrapedString) {
		// TODO 대소문자를 구분해서 처리해야 함
		String alphabet = scrapedString.replaceAll(Constants.ALPHABET_REGEX, "");
		String number = scrapedString.replaceAll(Constants.NUMBER_REGEX, "");
	
		return arrangeParsedString(alphabet, number);
	}

	private String arrangeParsedString(String alphabet, String number) {
		char[] arrangedAlphabet = alphabet.toCharArray();
		Arrays.sort(arrangedAlphabet);
		
		char[] arrangedNumber = number.toCharArray();
		Arrays.sort(arrangedNumber);
		
		return assembleOutputString(arrangedAlphabet, arrangedNumber);
	}
	
	private String assembleOutputString(char[] arrangedAlphabet, char[] arrangedNumber) {
		StringBuilder outputString = new StringBuilder();

		boolean isAlphabetLonger = arrangedAlphabet.length > arrangedNumber.length;
		
		if(isAlphabetLonger) {
			for (int i = 0; i < arrangedAlphabet.length-1; i++) {
				outputString.append(arrangedAlphabet[i]);
				
				if(i <= arrangedNumber.length-1) {
					outputString.append(arrangedNumber[i]);
				}
			}
		} else {
			for (int i = 0; i < arrangedNumber.length-1; i++) {
				outputString.append(arrangedNumber[i]);
				
				if(i <= arrangedAlphabet.length-1) {
					outputString.append(arrangedAlphabet[i]);
				}
			}
		}
		
		return outputString.toString();
	}

	private ParsingOutput divideSize(String outputString, String size) {
		ParsingOutput parsingOutput = new ParsingOutput();
		
		int outputLength = outputString.length();
		int sizeNum = Integer.parseInt(size);
		
		parsingOutput.setQuotient(outputLength/sizeNum);
		parsingOutput.setRemainder(outputLength%sizeNum);
		
		return parsingOutput;
	}
	
}
