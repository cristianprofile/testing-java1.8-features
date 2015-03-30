package com.cristian.mylab;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class PrintJustifiedTest {

	@Autowired
	private PrintJustifiedText printJustifiedText;

	@Test
	public void contextLoads() {
		String phrase = "Dispatching mechanisms from the core of distributed object computing (DOC) middleware.";
		
		String printJustifiedText2 = printJustifiedText.printJustifiedText(phrase, 25);
		
		System.out.println(printJustifiedText2);
		
		String[] lines = printJustifiedText2.trim().split("\n");
		

		Assert.assertTrue(lines[0].trim().equals("Dispatching    mechanisms"));
		Assert.assertTrue(lines[1].trim().equals("from    the    core    of"));
		Assert.assertTrue(lines[2].trim().equals("distributed        object"));
		Assert.assertTrue(lines[3].trim().equals("computing           (DOC)"));
		Assert.assertTrue(lines[4].trim().equals("middleware."));
	}

}
