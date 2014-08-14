package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

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

	public Optional<Flight> flightDateMinPrice(Collection<Flight> flightCollection,
			LocalDate date);

	public Optional<Flight> flightDateMaxPrice(Collection<Flight> flightCollection,
			LocalDate date);

	public Optional<Flight> flightDateMaxNumPassengers(
			Collection<Flight> flightCollection, LocalDate date);

	public Optional<Flight> flightDateMaxNumSeats(Collection<Flight> flightCollection,
			LocalDate date);

	public int flightDateNumPassengersSum(Collection<Flight> flightCollection, LocalDate date);

	public OptionalDouble flightDatePriceAverage(Collection<Flight> flightCollection,
			LocalDate date);
}
