package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Stream;

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

	public Optional<Flight> flightDateMaxNumSeatsAndNumPassengers(
			Collection<Flight> flightCollection, LocalDate date);
	
	public Optional<Flight> flightDateMinNumSeatsAndNumPassengers(
			Collection<Flight> flightCollection, LocalDate date);
	
	public int flightDateNumPassengersSum(Collection<Flight> flightCollection, LocalDate date);

	public OptionalDouble flightDatePriceAverage(Collection<Flight> flightCollection,
			LocalDate date);
	
	public double flightDatePriceAverageOtherWay(Collection<Flight> flightCollection,
			LocalDate date);

	public List<Flight> flightDateOrderByNumPassengersAndNumSeatsDesc(
			Collection<Flight> flightCollection, LocalDate date);

	public Duration flightDateReduceByDuration(Collection<Flight> flightCollection,
			LocalDate date);

	public void flightDateAddTenPercentAditionalPrice(
			Collection<Flight> flightCollection, LocalDate date);

	public List<Duration> flightDateGetListDuration(
			Collection<Flight> flightCollection, LocalDate date);

	public Set<Duration> flightDateGetSetDuration(Collection<Flight> flightCollection,
			LocalDate date);


	public Map<String, Duration> flightDateGenerateMapDestionationDuration(
			Collection<Flight> flightCollection, LocalDate date);

	public Map<Boolean, List<Flight>> flightDateGenerateMapPriceLowCost(
			Collection<Flight> flightCollection, LocalDate date);

	public Map<Integer, List<Flight>> flightDateGenerateMapGroupByNumSeats(
			Collection<Flight> flightCollection, LocalDate date);

	public Map<Integer, Set<Flight>> flightDateGenerateGroupByNumSeatsSet(
			Collection<Flight> flightCollection, LocalDate date);

	public  Map<String, Double> flightDateGroupByDestinationAveragePrice(
			Collection<Flight> flightCollection, LocalDate date);

	public Map<LocalDate, Double> flightGroupByDateSumPrice(
			Collection<Flight> flightCollection, LocalDate date);

	





	

	
}
