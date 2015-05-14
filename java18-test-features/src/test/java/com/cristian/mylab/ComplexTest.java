package com.cristian.mylab;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class ComplexTest {
	
	@Test
	public void factoryCreate() {

		
		//  We create a reference to the Person constructor via Person::new. 
		//  The Java compiler automatically chooses the right constructor by matching the signature of PersonFactory.create(). 
		//  In this case constructor with 2 arguments like definition of PersonFactoryInterface create method.
		PersonFactoryInterface<Person> personFactory = Person::new;
		Person person = personFactory.create("Angel", "Pepe");
		assertThat(person.getFirstName(), equalToIgnoringCase("Angel"));
		assertThat(person.getLastName(), equalToIgnoringCase("Pepe"));
		
		
	    //  In this case constructor with 1 argument like definition of PersonFactoryOneParameterInterface create method.
		PersonFactoryOneParameterInterface<Person> personFactoryOneParameter =Person::new;
		
		person = personFactoryOneParameter.create("Carlos");
		
		assertThat(person.getFirstName(), equalToIgnoringCase("Carlos"));
	 	

	}
	
	@Test
	public void curryJava18Test() {

		
		IntFunction<IntUnaryOperator> curriedAdd = a -> b -> a + b;

		IntUnaryOperator adder5 = curriedAdd.apply(5);
		assertThat(adder5.applyAsInt(4),equalTo(9));
		assertThat(adder5.applyAsInt(6),equalTo(11));
	
	

		IntUnaryOperator adder6 = curriedAdd.apply(11);
		assertThat(adder6.applyAsInt(10),equalTo(21));

		Function<Integer, Function<Integer, IntUnaryOperator>> mycurry = x -> y -> z -> x+ y * z;

		assertThat(mycurry.apply(5).apply(3).applyAsInt(12),equalTo(41));
																		

		//curry allowed this function to be called with multiple partial calls 
		
		Function<Integer, IntUnaryOperator> applyCurryFunction = mycurry.apply(2);
		
		IntUnaryOperator applyEndCurry = applyCurryFunction.apply(4);
		
		assertThat(applyEndCurry.applyAsInt(5),equalTo(22));
		

		Function<Integer, UnaryOperator<Integer>> otherVerboseCurryAnonimusFunction = a -> b -> a + b;

		
		assertThat(otherVerboseCurryAnonimusFunction.apply(7).apply(5),equalTo(12));

		Function<Integer, Function<Integer, Integer>> theMostVerboseDeclaration = a -> b -> a + b;


		assertThat(theMostVerboseDeclaration.apply(7).apply(5),equalTo(12));

		//Last declaration 3 parameter function
		Function<Integer, Function<Integer, UnaryOperator<Integer>>> curryMio = x -> y -> z -> x + y * z;


		Function<Integer, UnaryOperator<Integer>> apply2 = curryMio.apply(5);

		UnaryOperator<Integer> apply3 = apply2.apply(4);

		assertThat(apply3.apply(5),equalTo(25));


	}
	
	

}
