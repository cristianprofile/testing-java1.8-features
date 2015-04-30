package com.cristian.mylab;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class FuncionalInterfaceTest {

	
	
	
//	double sum(int a,int b);
//	
//	 default double printSum(int a,int b) {
//		    double sum = sum(a, b);
//		    System.out.println(sum);
//	        return sum;
//	    }
	
	
	
//	Functional Interfaces
//
//	How does lambda expressions fit into Javas type system? 
//	Each lambda corresponds to a given type, specified
//	by an interface. 
//	A so called functional interface must contain exactly one abstract method declaration.
//	Each lambda expression of that type will be matched to this abstract method. 
//	Since default methods are not abstract you're free to add default methods to your functional interface.
//
//	We can use arbitrary interfaces as lambda expressions as long as the interface only contains one abstract method. To ensure that your interface meet the requirements, you should add the @FunctionalInterface annotation. The compiler is aware of this annotation and throws a compiler error as soon as you try to add a second abstract method declaration to the interface.
	
	@Test
	public void contextLoads() {
		
	    // if we have a functional interface with only  exactly one abstract method 
		// java 1.8 allows to  match or lambda expresion to this abstract method. Is java 1.8 magic!!!!
		FuncionalInterface x=(a,b)->a+b;
		double sum = x.sum(2, 2);
		Assert.assertTrue(4==sum);
	    sum = x.printSum(4, 4);
	    Assert.assertTrue(8==sum); 
		
	    //If we have constructors or static methods with the same signature method
	    //of our fuctional interface method we will be allowed to write easier.
	    
	    FuncionalInterface y=Math::addExact;
	    
	    sum = y.printSumAddOne(2, 2);
	    
	    Assert.assertTrue(5==sum); 
		
	}

}
