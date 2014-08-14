package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

	Predicate<Flight> fullFlightPredicate()

	{
		return vuelo -> vuelo.getNumPassengers().equals(vuelo.getNumSeats());
	}

	Predicate<Flight> isDatetPredicate(LocalDate date)

	{
		return vuelo -> vuelo.getDate().compareTo(date) == 0;
	}
	
	ToIntFunction<Flight>  getNumPassengers(){
		return flight->flight.getNumPassengers();
	}
	
	Function<Flight, Integer>  getNumSeats(){
		return flight->flight.getNumSeats();
	}
	 
	
	
	

	@Override
	public long numberFlightByDay(LocalDate date,
			Collection<Flight> flightCollection) {
		// Testing filter method 1, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(
				vuelo -> vuelo.getDate().equals((date)));
		return filter.count();
	}

	@Override
	public long numberFullFlight(Collection<Flight> flightCollection) {
		// Testing filter method 2, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(
				fullFlightPredicate());
		return filter.count();
	}

	@Override
	public long numberFullFlightAndDate(Collection<Flight> flightCollection,
			LocalDate date) {
		// Testing filter method 2, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(
				fullFlightPredicate().and(isDatetPredicate(date)));
		return filter.count();
	}
	
	
	
	@Override
	public boolean fullFlightAndDateAllMatch(Collection<Flight> flightCollection,
			LocalDate date) {
		// using all match operator of a stream
		 
		return flightCollection.stream().allMatch(fullFlightPredicate().and(isDatetPredicate(date)));
	}
	
	
	@Override
	public boolean fullFlightAndDateAnyMatch(Collection<Flight> flightCollection,
			LocalDate date) {
		
		// using any match operator of a stream
		 
		return flightCollection.stream().anyMatch(fullFlightPredicate().and(isDatetPredicate(date)));
	}

	
	@Override
	public Optional<Flight> flightDateMinPrice(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> min = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.min(Comparator.comparing(Flight::getPrice));
		return min;
	}
	
	@Override
	public Optional<Flight> flightDateMaxPrice(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(Comparator.comparing(Flight::getPrice));
		return max;
	}
	
	@Override
	public Optional<Flight> flightDateMaxNumPassengers(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(Comparator.comparingInt(getNumPassengers()));
		return max;
	}
	
	@Override
	public Optional<Flight> flightDateMaxNumSeats(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(Comparator.comparing(getNumSeats()));
		return max;
	}
	
	
	
	
	
	
}
