Testing java 1.8 : I Love functional programming
========================

Testing java 1.8 new features with dates and lambda expression using collections streams.

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
-  Use max and min comparator with stream and testing  new optional object, null pointer has death
[Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L177) and
[Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L110)
 -  ```Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(byNumPassengers.thenComparing(byNumSeats));```  
-  Created lambda function declaration to be reused (Lambda function, lambda int function)
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
- Added use collect method to use groupBy sum and average operation and partition operation Testing new Optional object class on   [Test Class Method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L417) and
 [Implementation Class method](/java18-test-features/src/main/java/com/cristian/mylab/FlightServiceImpl.java#L275)
 - `Map<Boolean, List<Flight>> flightDateGenerateMapPriceLowCost= flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.partitioningBy(flight->flight.getPrice()<=800));`
 - `Map<Integer, List<Flight>> flightDateGenerateMapGroupByNumSeats= flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getNumSeats()));`
 - `Map<Integer, Set<Flight>> flightDateGenerateGroupByNumSeatsSet = flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getNumSeats(),Collectors.toSet()))`
 - `Map<String, Double> flightDateGroupByDestinationAveragePrice=flightCollection.stream().filter(isDatetPredicate(date)).collect(Collectors.groupingBy(flight->flight.getDestination(),Collectors.averagingDouble((Flight flight)->flight.getPrice())));`
 - `Map<LocalDate, Double> flightGroupByDateSumPrice= flightCollection.stream().collect(Collectors.groupingBy((Flight flight)->flight.getDate(),Collectors.summingDouble((Flight flight)->flight.getPrice())));`
- Testing new Optional object class on java 1.8 [Test Optional Example method](/java18-test-features/src/test/java/com/cristian/mylab/FlightServiceImplTest.java#L470)
 - <tt>OptionalDouble flightDatePriceAverage = flightService.flightDatePriceAverage(flightCollection, date);
   Assert.assertTrue(flightDatePriceAverage.isPresent());</tt>

