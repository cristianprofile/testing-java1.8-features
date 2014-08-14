package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;


@Service
public class FlightServiceImpl implements FlightService {

	@Override
	public long numberFlightByDay(LocalDate date,Collection<Flight> flightCollection) {
		// Testing filter method, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(vuelo->vuelo.getDate().compareTo(date)==0);
		return filter.count();
	}
	
	@Override
	public long numberFullFlight(Collection<Flight> flightCollection) {
		// Testing filter method, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(vuelo->vuelo.getNumPassengers()==vuelo.getNumSeats());
		return filter.count();
	}

}
