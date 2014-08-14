package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Predicate;
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

}
