Testing java 1.8 : I Love functional programming
========================

Testing java 1.8 new features with dates and lambda expression using collections streams.

-  Filter a collection with lambda expression using boolean predicate
 - Example:
   `flightCollection.stream().filter(
				vuelo -> vuelo.getDate().equals((date)));`
-  Reuse predicates with and or and not logical operator
 - Example: 
	` 
	 Stream<Flight> filter = flightCollection.stream().filter(
	 fullFlightPredicate().and(isDatetPredicate(date)));
	`
	
-  Use any and all operator to streams (boolean operation)
 - Example: ```flightCollection.stream().anyMatch(
				fullFlightPredicate().and(isDatetPredicate(date)));
		flightCollection.stream().allMatch(
				fullFlightPredicate().and(isDatetPredicate(date))); ``` 
-  Use max and min comparator with stream and testing  new optional object, null pointer has death
 - Example: ```Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(byNumPassengers.thenComparing(byNumSeats));```  
-  Created lambda function declaration to be reused (Lambda function, lambda int function)
-  Use mapToInt and Map to double to test sum and average calculation operation
 - Example: `flightCollection.stream().filter(isDatetPredicate(date))
				.mapToInt(getNumPassengers()).sum();
OptionalDouble average = flightCollection.stream()
				.filter(isDatetPredicate(date)).mapToDouble(Flight::getPrice)
				.average();`
-  Added to strings method to Flight and Duration Bean.
-  Added Comparator function to order and calculate max and min value with thenComparing. 
   Example: 
           `Comparator<Flight> byNumPassengers = (f1, f2) -> f1.getNumPassengers()
			.compareTo(f2.getNumPassengers());
	     Comparator<Flight> byNumSeats = (f1, f2) -> f1.getNumSeats().compareTo(
			f2.getNumSeats());
		flightCollection.stream().filter(isDatetPredicate(date)).min(byNumPassengers.thenComparing(byNumSeats)); 
				    flightCollection.stream().sorted(byNumPassengers.thenComparing(byNumSeats));`
-  Added Reduce a flight collection. Reduce operation create an "sum iterate operation for each element". We will calculate sum of all flights using Duration class
   
