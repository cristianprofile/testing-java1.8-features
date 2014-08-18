Testing java 1.8 : I Love functional programming
========================

Testing java 1.8 new features with dates and lambda expression using collections streams.

-  Filter a collection with lambda expression using boolean predicate
   `flightCollection.stream().filter(
				vuelo -> vuelo.getDate().equals((date)));`
-  Reuse predicates with and or and not logical operator
	```Predicate<Flight> fullFlightPredicate()
		return vuelo -> vuelo.getNumPassengers().equals(vuelo.getNumSeats());
	Predicate<Flight> isDatetPredicate(LocalDate date)
		return vuelo -> vuelo.getDate().compareTo(date) == 0;
	 Stream<Flight> filter = flightCollection.stream().filter(
	 fullFlightPredicate().and(isDatetPredicate(date)));
	```

	
-  Use any and all operator to streams (boolean operation)
-  Use max and min comparator with stream and testing  new optional object, null pointer has death
-  Created lambda function declaration to be reused (Lambda function, lambda int function)
-  Use mapToInt and Map to double to test sum and average calculation operation
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
   
