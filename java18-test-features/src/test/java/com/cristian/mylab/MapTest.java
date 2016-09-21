package com.cristian.mylab;

import org.junit.Assert;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by cristianromeromatesanz on 09/09/16.
 */
public class MapTest {


    @Test
    public void mapTest() {


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
                .collect((Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue())));

        Map<String, Person> collect3 = personMap.entrySet().stream().filter(entry -> entry.getValue().getFirstName().equalsIgnoreCase("Adolf") || entry.getValue().getFirstName().equalsIgnoreCase("George33"))
                .collect((Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue())));

        Assert.assertTrue(collect.size() == 2);
        Assert.assertTrue(collect2.size() == 2);
        Assert.assertTrue(collect3.size() == 1);


        Assert.assertTrue(collect3.get("1").equals(person1));


    }


    @Test
    public void parseUrlParemetersWithExceptionsEmptyMapTest() {


        Map<String, List<String>> result = parseQuery("action;verify&key:10101&key;22222&key;;10101&other::7777");


        Assert.assertTrue(result.isEmpty());


    }


    @Test
    public void parseUrlParemetersExceptionsTest() {

        Map<String, List<String>> result1 = parseQuery("action=verify&key=33&key=22222&key=10101&other=7777");


        Assert.assertFalse(result1.isEmpty());
        Assert.assertTrue(result1.get("key").size()==3);
        Assert.assertTrue(result1.get("key").stream().anyMatch(a->a.equalsIgnoreCase("33")));
        Assert.assertTrue(result1.get("key").stream().anyMatch(a->a.equalsIgnoreCase("22222")));
        Assert.assertTrue(result1.get("key").stream().anyMatch(a->a.equalsIgnoreCase("10101")));
        Assert.assertTrue(result1.get("action").stream().anyMatch(a->a.equalsIgnoreCase("verify")));

    }


    /**
     * Parse url parameters and create map with key values
     * @param query url parameters to parse
     * @return map with key values (list of values). Empty map if parameters are incorrect parsed
     */
    private Map<String, List<String>> parseQuery(String query) {
        if (query == null) {
            return new HashMap<String, List<String>>();
        }

        String[] split = query.split("[&]");


        Map<String, List<String>> mapResult = Arrays.stream(split).map(this::splitKeyValue).filter(a -> a != null).
                collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey,
                        mapping(AbstractMap.SimpleImmutableEntry::getValue, toList())));


        return mapResult;

    }

    /**
     * Method to create an Entry of a map with key=value split func.
     * @param keyValue
     * @return Entry of a map with key=value split value. Null if an error is thrown
     */
    private AbstractMap.SimpleImmutableEntry<String, String> splitKeyValue(String keyValue) {
        String enc = System.getProperty("file.encoding");
        String[] parts = keyValue.split("[=]");
        try {

            return new AbstractMap.SimpleImmutableEntry<>(
                    URLDecoder.decode(parts[0], enc),
                    URLDecoder.decode(parts[1], enc));
        } catch (Exception e) {
            return null;
        }
    }


}
