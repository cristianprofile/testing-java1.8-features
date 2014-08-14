package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;

public interface FlightService {

	public long numberFlightByDay(LocalDate date,
			Collection<Flight> flightCollection);

	public long numberFullFlight(Collection<Flight> flightCollection);

	long numberFullFlightAndDate(Collection<Flight> flightCollection,
			LocalDate date);
}
