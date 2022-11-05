package com.minwoo.siteparser.dto;

public class ParsingOutput {
	
	private int quotient;
	private int remainder;
	
	public int getQuotient() {
		return quotient;
	}
	public void setQuotient(int quotient) {
		this.quotient = quotient;
	}
	public int getRemainder() {
		return remainder;
	}
	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}
	
	@Override
	public String toString() {
		return "ParseResult [quotient=" + quotient + ", remainder=" + remainder + "]";
	}
	
}
