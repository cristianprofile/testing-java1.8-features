package com.cristian.mylab;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

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

}
