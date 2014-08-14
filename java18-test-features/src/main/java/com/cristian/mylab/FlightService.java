package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;

public interface FlightService {

	public long numberFlightByDay(LocalDate date,
			Collection<Flight> flightCollection);

	public long numberFullFlight(Collection<Flight> flightCollection);

	public long numberFullFlightAndDate(Collection<Flight> flightCollection,
			LocalDate date);

	public boolean fullFlightAndDateAllMatch(
			Collection<Flight> flightCollection, LocalDate date);

	public boolean fullFlightAndDateAnyMatch(
			Collection<Flight> flightCollection, LocalDate date);
}
