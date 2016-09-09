package com.cristian.mylab;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cristianromeromatesanz on 09/09/16.
 */
public class MapTest {



    @Test
    public void contextLoads() {


        Person person1 = new Person();
        person1.setFirstName("Adolf");
        person1.setLastName("Hulmer");
        Person person2 = new Person();
        person2.setFirstName("George");
        person2.setLastName("Lucas");
        Person person3 = new Person();
        person3.setFirstName("Peter");
        person3.setLastName("Creming");
        Person person4 = new Person();
        person4.setFirstName("Manuel");
        person4.setLastName("Seegal");

        Map<String, Person> personMap = new HashMap<String, Person>();

        personMap.put("1", person1);
        personMap.put("2", person2);
        personMap.put("3", person3);
        personMap.put("4", person4);


        Map<String, Person> collect = personMap.entrySet().stream().filter(entry -> entry.getValue().getFirstName().equalsIgnoreCase("Adolf") || entry.getValue().getFirstName().equalsIgnoreCase("George"))
                .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));


        Map<String, Person> collect2 = personMap.entrySet().stream().filter(entry -> entry.getValue().getFirstName().equalsIgnoreCase("Adolf") || entry.getValue().getFirstName().equalsIgnoreCase("George"))
                .collect((Collectors.toMap(entry->entry.getKey(),entry->entry.getValue())));

        Map<String, Person> collect3 = personMap.entrySet().stream().filter(entry -> entry.getValue().getFirstName().equalsIgnoreCase("Adolf") || entry.getValue().getFirstName().equalsIgnoreCase("George33"))
                .collect((Collectors.toMap(entry->entry.getKey(),entry->entry.getValue())));

        Assert.assertTrue(collect.size()==2);
        Assert.assertTrue(collect2.size()==2);
        Assert.assertTrue(collect3.size()==1);


        Assert.assertTrue(collect3.get("1").equals(person1));


    }


}
