Testing java 1.8 : I Love functional programming
========================

Testing java 1.8 new features with dates and lambda expression using collections streams.

-  Filter a collection with lambda expression using boolean predicate
 - 
     `flightCollection.stream().filter(
				vuelo -> vuelo.getDate().equals((date)));`
-  Reuse predicates with and or and not logical operator
 -  
	` 
	 Stream<Flight> filter = flightCollection.stream().filter(
	 fullFlightPredicate().and(isDatetPredicate(date)));
	`
	
-  Use any and all operator to streams (boolean operation)
 -  ```flightCollection.stream().anyMatch(
				fullFlightPredicate().and(isDatetPredicate(date)));
		flightCollection.stream().allMatch(
				fullFlightPredicate().and(isDatetPredicate(date))); ``` 
-  Use max and min comparator with stream and testing  new optional object, null pointer has death
 -  ```Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(byNumPassengers.thenComparing(byNumSeats));```  
-  Created lambda function declaration to be reused (Lambda function, lambda int function)
-  Use mapToInt and Map to double to test sum and average calculation operation
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
 - `Duration reduceDuration = flightCollection.stream()
				.map(flight -> flight.getDuration())
				.reduce(new Duration(0, 0), (x, y) -> {
					Integer min = x.getMinutes() + y.getMinutes();
					Integer hor = x.getHours() + y.getHours();
					return new Duration(hor + min / 60, min % 60);
				});
		return reduceDuration;` 

- Added foreach consumer to be able to make change to elements of stream. Example method flightDateAddTenPercentAditionalPrice foreach stream iterate
 - `flightCollection.stream().forEach(addTenPercentAditionalPrice());`
- Added use collect method to transform our collect to set list or map collection
 - `public List<Duration> flightDateGetListDuration(
			Collection<Flight> flightCollection, LocalDate date) 

		return flightCollection.stream().filter(isDatetPredicate(date))
				.map(Flight::getDuration).collect(Collectors.toList());`
 - `public Set<Duration> flightDateGetSetDuration(
			Collection<Flight> flightCollection, LocalDate date) 

		return flightCollection.stream().filter(isDatetPredicate(date))
				.map(Flight::getDuration).collect(Collectors.toSet());`
 - `public Map<String, Duration> flightDateGenerateMapDestionationDuration(
			Collection<Flight> flightCollection, LocalDate date) 
		return flightCollection
				.stream()
				.filter(isDatetPredicate(date))
				.collect(Collectors.toMap((Flight flight) -> flight.getDestination(), (
						Flight flight) ->flight.getDuration()));`
