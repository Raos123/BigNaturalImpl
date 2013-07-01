package com.roman.bignatural.implementations;

import java.util.ArrayList;
import java.util.List;

import com.roman.bignatural.interfaces.BigNatural;

public final class SlowBigNatural implements BigNatural{

	private List<Integer> rep = new ArrayList<Integer>();
	
	public SlowBigNatural() {
		rep.add(0);
	}
	
	public SlowBigNatural(int number) {
		this(String.valueOf(number));
	}
	
	public SlowBigNatural(String number) {
		convertString(number);
	}
	
	public SlowBigNatural(BigNatural number) {
		this(number.toString());
	}

	@Override
	public void increment() {
		boolean foundDigit = false;
		/*
		 * Search through array for the first non-nine digit and increment it
		 * while changing all nine digits to 0. If no non-nine digit is found 
		 * then be sure to prepend 1 to the array. ( i.e. 9999 -> 0000 -> 10000)
		 */
		for(int index = rep.size() - 1; !foundDigit && index >= 0; index--) {
			int digit = rep.get(index);
			if( digit < 9) {
				foundDigit = true;
				rep.set(index, digit + 1);
			}
			else {
				rep.set(index, 0);
			}
		}
		if(!foundDigit) {
			rep.add(0, 1);
		}
	}

	@Override
	public void decrement() {
		if(rep.get(0) != 0) {
			boolean foundDigit = false;
			/*
			 * Search through array for the first non-zero digit and decrement it
			 * while changing all zero digits to 9. Check and remove possible 
			 * leading zero as well. ( i.e. 1000 -> 0999 -> 999)
			 */
			for(int index = rep.size() - 1; !foundDigit && index >= 0; index--) {
				int digit = rep.get(index);
				if(digit > 0) {
					foundDigit = true;
					rep.set(index, digit - 1);
				}
				else {
					rep.set(index, 9);
				}
			}
			if(rep.get(0) == 0) {
				rep.remove(0);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder stringRep = new StringBuilder();
		for(Integer digit : rep) {
			stringRep.append(digit);
		}
		return stringRep.toString();
	}

	@Override
	public int compareTo(BigNatural number) {
		String thisObj = this.toString();
		String thatObj = number.toString();
		if(thisObj.length() > thatObj.length()) {
			return 1;
		}
		else if(thisObj.length() < thatObj.length()) {
			return -1;
		}
		else {
			for(int index = 0; index < thatObj.length(); index++) {
				if(thisObj.charAt(index) > thatObj.charAt(index)) {
					return 1;
				}
				else if(thisObj.charAt(index) < thatObj.charAt(index)) {
					return -1;
				}
			}
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ans = false;
		if(this == obj) {
			ans =  true;
		}
		else if(obj instanceof BigNatural){
			BigNatural number = (BigNatural)obj;
			ans = this.toString().equals(number.toString());
		}
		return ans;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	private void convertString(String stringRep) {
		for(int index = 0; index < stringRep.length(); index++) {
			rep.add(Character.getNumericValue(stringRep.charAt(index)));
		}
	}
}
