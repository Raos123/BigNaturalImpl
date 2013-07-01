package com.roman.bignatural.tests;

import com.roman.bignatural.implementations.SlowBigNatural;
import com.roman.bignatural.interfaces.BigNatural;

public class TestBigNatural {

	public static void main(String[] args) {
		BigNatural b1 = new SlowBigNatural(Integer.MAX_VALUE);
		BigNatural b2 = new SlowBigNatural(Integer.MAX_VALUE);
		BigNatural b3 = b1;
		
		b1.increment();
		b2.increment();
		
		System.out.println(b1.equals(b2));
		System.out.println(b1.equals(b3));
		System.out.println(b2.equals(null));
	}
}