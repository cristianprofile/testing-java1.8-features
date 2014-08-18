package com.cristian.mylab;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

	BinaryOperator<Duration> sumTime() {
		return (x, y) -> {
			Integer min = x.getMinutes() + y.getMinutes();
			Integer hor = x.getHours() + y.getHours();
			return new Duration(hor + min / 60, min % 60);
		};
	}

	Consumer<Flight> addTenPercentAditionalPrice() {
		return flight -> flight.setPrice(flight.getPrice()
				+ (flight.getPrice() * 0.1));
	}

	Comparator<Flight> byNumPassengers = (f1, f2) -> f1.getNumPassengers()
			.compareTo(f2.getNumPassengers());

	Comparator<Flight> byNumSeats = (f1, f2) -> f1.getNumSeats().compareTo(
			f2.getNumSeats());

	Predicate<Flight> fullFlightPredicate()

	{
		return vuelo -> vuelo.getNumPassengers().equals(vuelo.getNumSeats());
	}

	Predicate<Flight> isDatetPredicate(LocalDate date)

	{
		return vuelo -> vuelo.getDate().compareTo(date) == 0;
	}

	ToIntFunction<Flight> getNumPassengers() {

		return flight -> flight.getNumPassengers();
	}

	Function<Flight, Integer> getNumSeats() {
		return flight -> flight.getNumSeats();
	}

	@Override
	public long numberFlightByDay(LocalDate date,
			Collection<Flight> flightCollection) {
		// Testing filter method 1, it used to filter an stream
		Stream<Flight> filter = flightCollection.stream().filter(
				flight -> flight.getDate().equals((date)));
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
	public boolean fullFlightAndDateAllMatch(
			Collection<Flight> flightCollection, LocalDate date) {
		// using all match operator of a stream

		return flightCollection.stream().allMatch(
				fullFlightPredicate().and(isDatetPredicate(date)));
	}

	@Override
	public boolean fullFlightAndDateAnyMatch(
			Collection<Flight> flightCollection, LocalDate date) {

		// using any match operator of a stream

		return flightCollection.stream().anyMatch(
				fullFlightPredicate().and(isDatetPredicate(date)));
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

	@Override
	public Optional<Flight> flightDateMaxNumSeatsAndNumPassengers(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.max(byNumPassengers.thenComparing(byNumSeats));
		return max;
	}

	@Override
	public Optional<Flight> flightDateMinNumSeatsAndNumPassengers(
			Collection<Flight> flightCollection, LocalDate date) {

		Optional<Flight> max = flightCollection.stream()
				.filter(isDatetPredicate(date))
				.min(byNumPassengers.thenComparing(byNumSeats));
		return max;
	}

	@Override
	public int flightDateNumPassengersSum(Collection<Flight> flightCollection,
			LocalDate date) {

		int sum = flightCollection.stream().filter(isDatetPredicate(date))
				.mapToInt(getNumPassengers()).sum();
		return sum;
	}

	@Override
	public OptionalDouble flightDatePriceAverage(
			Collection<Flight> flightCollection, LocalDate date) {

		OptionalDouble average = flightCollection.stream()
				.filter(isDatetPredicate(date)).mapToDouble(Flight::getPrice)
				.average();
		return average;
	}

	@Override
	public Stream<Flight> flightDateOrderByNumSeatsAndNumPassengers(
			Collection<Flight> flightCollection, LocalDate date) {

		Stream<Flight> sortedFlights = flightCollection.stream().sorted(
				byNumPassengers.thenComparing(byNumSeats));

		return sortedFlights;
	}

	/**
	 * Reduce a flight collection. Reduce operation create an "add iterate"
	 * operation for each element In this example we are going to sum all
	 * duration flights
	 */
	@Override
	public Duration flightDateReduceByDuration(
			Collection<Flight> flightCollection, LocalDate date) {

		Duration reduceDuration = flightCollection.stream()
				.map(flight -> flight.getDuration())
				.reduce(new Duration(0, 0), sumTime());
		return reduceDuration;

	}

	@Override
	public void flightDateAddTenPercentAditionalPrice(
			Collection<Flight> flightCollection, LocalDate date) {
		flightCollection.stream().forEach(addTenPercentAditionalPrice());

	}

}
