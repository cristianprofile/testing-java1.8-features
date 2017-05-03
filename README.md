[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/cristianprofile/testing-java1.8-features?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge) [![Build Status](https://travis-ci.org/cristianprofile/testing-java1.8-features.svg?branch=master)](https://travis-ci.org/cristianprofile/testing-java1.8-features)


Testing java 1.8 : I Love functional programming. "Test lambda expression using collections streams".
========================

Lambda is the most powerfull feature added to java language. Now functions are objects so you can send a function param to a method. This was very commont in other programming languages, and now it is available with a lot changues in apis like collections. Let me show you how easy it is to use this pretty new feature.

![Collection flights example](/image/flights.png?raw=true "Spring Actuator values")

-  Filter a collection with lambda expression using boolean predicate
 [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L90) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L65)
 -
     `flightCollection.stream().filter(
				vuelo -> vuelo.getDate().equals((date)));`
-  Reuse predicates with and or and not logical operator
 [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L137) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L82)
 -  
	`
	 Stream<Flight> filter = flightCollection.stream().filter(
	 fullFlightPredicate().and(isDatetPredicate(date)));
	`

-  Use any and all operator to streams (boolean operation)
 [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L147) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L90)
 -  ```flightCollection.stream().anyMatch(
				fullFlightPredicate().and(isDatetPredicate(date)));
		flightCollection.stream().allMatch(
				fullFlightPredicate().and(isDatetPredicate(date))); ```
-Use max and min comparator with stream and testing  new optional object, null pointer has death
[Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L177) and
[Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L110)
 -  ```Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(byNumPassengers.thenComparing(byNumSeats));```  
-  Created lambda function declaration to be reused (Lambda function, lambda int function)
- Use removeIf operation in collections
[Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L504) and
[Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L335)  
-  Use mapToInt and Map to double to test sum and average calculation operation
[Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L278) and
[Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L170)
 -  `flightCollection.stream().filter(isDatetPredicate(date))
				.mapToInt(getNumPassengers()).sum();`
 - `OptionalDouble average = flightCollection.stream()
				.filter(isDatetPredicate(date)).mapToDouble(Flight::getPrice)
				.average();`
-  Added to strings method to Flight and Duration Bean.
-  Added Comparator function to order and calculate max and min value with thenComparing.
 - `flightCollection.stream().filter(isDatetPredicate(date)).min(byNumPassengers.thenComparing(byNumSeats));`
 - `flightCollection.stream().sorted(byNumPassengers.thenComparing(byNumSeats));`
-  Added Reduce a flight collection. Reduce operation create an "sum iterate operation for each element". We will calculate sum of all flights using Duration class
[Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L337) and
[Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L223)
 - `Duration reduceDuration = flightCollection.stream()
				.map(flight -> flight.getDuration())
				.reduce(new Duration(0, 0), (x, y) -> {
					Integer min = x.getMinutes() + y.getMinutes();
					Integer hor = x.getHours() + y.getHours();
					return new Duration(hor + min / 60, min % 60);
				});
		return reduceDuration;`

- Added foreach consumer to be able to make change to elements of stream. Example method flightDateAddTenPercentAditionalPrice foreach stream iterate
 [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L348) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L234)

 - `flightCollection.stream().forEach(addTenPercentAditionalPrice());`
- Added use collect method to transform our collect to set list or map collection
 [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L375) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L240)
 - `List<Duration> flightDateGetListDuration= flightCollection.stream().filter(isDatetPredicate(date)).map(Flight::getDuration).collect(Collectors.toList());`
 - `Set<Duration> flightDateGetSetDuration=flightCollection.stream().filter(isDatetPredicate(date)).map(Flight::getDuration).collect(Collectors.toSet());`
 - `Map<String, Duration> flightDateGenerateMapDestionationDuration= flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.toMap((Flight flight) -> flight.getDestination(),(Flight flight) ->flight.getDuration()));`
 - More information about using map reduction with streams :  [Map example operation](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
- Added use collect method to use groupBy sum and average operation and partition operation Testing new Optional object class on   [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L417) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L275)
 - `Map<Boolean, List<Flight>> flightDateGenerateMapPriceLowCost= flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.partitioningBy(flight->flight.getPrice()<=800));`
 - `Map<Integer, List<Flight>> flightDateGenerateMapGroupByNumSeats= flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getNumSeats()));`
 - `Map<Integer, Set<Flight>> flightDateGenerateGroupByNumSeatsSet = flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getNumSeats(),Collectors.toSet()))`
 - `Map<String, Double> flightDateGroupByDestinationAveragePrice=flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getDestination(),Collectors.averagingDouble((Flight flight)->flight.getPrice())));`
 - `Map<LocalDate, Double> flightGroupByDateSumPrice= flightCollection.stream().collect(Collectors.groupingBy((Flight flight)->flight.getDate(),Collectors.summingDouble((Flight flight)->flight.getPrice())));`

Testing java 1.8 : "Dates and optional object"
========================

Dates are new in java 1.8, simmilar to jodatime. A powerfull feature that will  let us to forget headache with old calendar´s api.
Optional is a new object class that will prevent in our code to throw null pointers exception. Let me show you how easy is to use this new 2 features. (more info at  [Optional objetc in java 1.8](http://ticodificando.com/2013/04/24/jdk-opcional-objs/))

- Testing new Optional object class on java 1.8 [Test Optional Example method](/java18-test-features/src/test/java/com/cristian/mylab/NewFeaturesTest.java#L24)
 - <tt>OptionalDouble flightDatePriceAverage = flightService.flightDatePriceAverage(flightCollection, date);
   Assert.assertTrue(flightDatePriceAverage.isPresent());</tt>
 - `Optional<String> optional = Optional.of("bam");
		Assert.assertTrue(optional.isPresent());
		//setting empty value to optional object
		optional = Optional.empty();
		Assert.assertFalse(optional.isPresent()); `
- Testing new localDate LocalTime and LocalDatetime in java 1.8 [Test Dates Example method](/java18-test-features/src/test/java/com/cristian/mylab/NewFeaturesTest.java#L46)

Testing  @FunctionalInterface and default methods, java 1.8 lambda's magic
========================

Functional Interfaces

How does lambda expressions fit into Javas type system? Each lambda corresponds to a given type, specified by an interface. A so called functional interface must contain exactly one abstract method declaration. Each lambda expression of that type will be matched to this abstract method. Since default methods are not abstract you're free to add default methods to your functional interface.

We can use arbitrary interfaces as lambda expressions as long as the interface only contains one abstract method. To ensure that your interface meet the requirements, you should add the @FunctionalInterface annotation. The compiler is aware of this annotation and throws a compiler error as soon as you try to add a second abstract method declaration to the interface.

Example:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted);    // 123

- Testing new @FunctionalInterface in java 1.8 and default methods [Test @FunctionalInterface](/java18-test-features/src/test/java/com/cristian/mylab/FuncionalInterfaceTest.java#L38)
- Another complex example to test funcitional interface with static reference to constructor method (Factory pattern)[Create factory pattern with java 1.8](/java18-test-features/src/test/java/com/cristian/mylab/ComplexTest.java#L16)

Testing  Currying in java 1.8 (functions of multiple parameters)
========================

Currying is the process of transforming a function that takes multiple arguments into a function that takes just a single argument and returns another function if any arguments are still needed. Let's see how to implement this approach in java 1.8:

- Java 1.8 Currying how to [Java 1.8 Currying](/java18-test-features/src/test/java/com/cristian/mylab/ComplexTest.java#L49)


Testing nexus/artictfactory cloud: packagecloud
========================

Used cloud artifact repository with travis and packagecloud:

https://blog.travis-ci.com/2017-03-30-deploy-maven-travis-ci-packagecloud/

If a label is pushed into the master branch then New artifactifact will be deployed to this artifact repository. It will be available to be used by other projects adding the repository:

`<repositories>
  <repository>
    <id>packagecloud</id>
    <url>
      https://packagecloud.io/cristianprofile/github/maven2
    </url>
    <releases>
      <enabled>true</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
<repositories>`


https://packagecloud.io/cristianprofile/github

