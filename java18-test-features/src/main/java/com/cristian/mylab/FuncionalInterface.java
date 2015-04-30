package com.cristian.mylab;


@FunctionalInterface
public interface FuncionalInterface {
	
	 double sum(int a,int b);
	
	
	 default double printSum(int a,int b) {
		    double sum = sum(a, b);
		    System.out.println(sum);
	        return sum;
	    }
	 
	 default double printSumAddOne(int a,int b) {
		    double sum = sum(a, b)+1;
		    System.out.println(sum);
	        return sum;
	    }

}
