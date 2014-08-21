package com.cristian.mylab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class FlightServiceImplTest {

	@Autowired
	private FlightService flightService;

	private Collection<Flight> getFlightCollection(LocalDate date,
			boolean fullFlight) {

		Collection<Flight> flightCollection = new ArrayList<Flight>();

		Flight flight = new Flight();
		flight.setCode("1");
		flight.setDate(date);
		flight.setDestination("London");
		Duration duration = new Duration();
		duration.setHours(1);
		duration.setMinutes(30);
		flight.setDuration(duration);
		flight.setNumPassengers(180);
		flight.setNumSeats(200);
		flight.setPrice(new Double(1000));
		flightCollection.add(flight);

		flight = new Flight();
		flight.setCode("2");
		flight.setDate(date);
		flight.setDestination("Madrid");
		duration = new Duration();
		duration.setHours(2);
		duration.setMinutes(30);
		flight.setDuration(duration);
		flight.setNumPassengers(180);
		flight.setNumSeats(255);
		flight.setPrice(new Double(800));
		flightCollection.add(flight);

		flight = new Flight();
		flight.setCode("3");
		flight.setDate(date);
		flight.setDestination("Paris");
		duration = new Duration();
		duration.setHours(3);
		duration.setMinutes(30);
		flight.setDuration(duration);
		flight.setNumPassengers(199);
		flight.setNumSeats(200);
		flight.setPrice(new Double(400));
		flightCollection.add(flight);

		flight = new Flight();
		flight.setCode("4");
		flight.setDate(date);
		flight.setDestination("Japan");
		duration = new Duration();
		duration.setHours(3);
		duration.setMinutes(30);
		flight.setDuration(duration);
		flight.setNumPassengers(180);
		flight.setNumSeats(200);
		if (fullFlight) {
			flight.setNumSeats(180);
		}
		flight.setPrice(new Double(2000));
		flightCollection.add(flight);
		return flightCollection;
	}

	@Test
	public void numberFlightByDayOk() {

		Assert.assertNotNull(flightService);
		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		long numberFlightByDay = flightService.numberFlightByDay(date,
				flightCollection);
		Assert.assertEquals(4, numberFlightByDay);
	}

	@Test
	public void numberFlightByDayOkWithZeroElements() {

		Assert.assertNotNull(flightService);
		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		date = LocalDate.of(2016, 5, 15);
		long numberFlightByDay = flightService.numberFlightByDay(date,
				flightCollection);
		Assert.assertEquals(0, numberFlightByDay);

	}

	@Test
	public void numberFullFlighZero() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		long numberFullFlight = flightService
				.numberFullFlight(flightCollection);
		Assert.assertEquals(0, numberFullFlight);

	}

	@Test
	public void numberFullFligh() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);

		long numberFullFlight = flightService
				.numberFullFlight(flightCollection);
		Assert.assertEquals(1, numberFullFlight);

	}

	@Test
	public void numberFlightByDayOkAndFull() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		long numberFlightByDayAndFull = flightService.numberFullFlightAndDate(
				flightCollection, date);
		Assert.assertEquals(1, numberFlightByDayAndFull);
	}

	@Test
	public void fullFlightAndDateAllMatchFalse() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		boolean allMatch = flightService.fullFlightAndDateAllMatch(
				flightCollection, date);
		Assert.assertFalse(allMatch);
	}

	@Test
	public void fullFlightAndDateAnyMatchTrue() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		boolean anyMatch = flightService.fullFlightAndDateAnyMatch(
				flightCollection, date);
		Assert.assertTrue(anyMatch);
	}

	@Test
	public void fullFlightAndDateAnyMatchFalse() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		boolean anyMatch = flightService.fullFlightAndDateAnyMatch(
				flightCollection, date);
		Assert.assertFalse(anyMatch);
	}

	@Test
	public void flightDateMinPrice() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Optional<Flight> flightDateMinPrice = flightService.flightDateMinPrice(
				flightCollection, date);
		Assert.assertTrue(flightDateMinPrice.isPresent());
		Assert.assertTrue(flightDateMinPrice.get().getDestination()
				.equals("Paris"));

	}

	@Test
	public void flightDateMinPriceEmptyFlightList() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = new ArrayList<Flight>();
		Optional<Flight> flightDateMinPrice = flightService.flightDateMinPrice(
				flightCollection, date);
		Assert.assertFalse(flightDateMinPrice.isPresent());

	}

	@Test
	public void flightDateMaxPrice() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Optional<Flight> flightDateMaxPrice = flightService.flightDateMaxPrice(
				flightCollection, date);
		Assert.assertTrue(flightDateMaxPrice.isPresent());
		Assert.assertTrue(flightDateMaxPrice.get().getDestination()
				.equals("Japan"));

	}

	@Test
	public void flightDateMaxPriceEmptyFlightList() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = new ArrayList<Flight>();
		// testing new optional object, null pointer has death
		Optional<Flight> flightDateMaxPrice = flightService.flightDateMaxPrice(
				flightCollection, date);
		Assert.assertFalse(flightDateMaxPrice.isPresent());

	}

	@Test
	public void flightDateMaxNumPassengers() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Optional<Flight> flightDateMaxPrice = flightService
				.flightDateMaxNumPassengers(flightCollection, date);
		Assert.assertTrue(flightDateMaxPrice.isPresent());
		Assert.assertTrue(flightDateMaxPrice.get().getDestination()
				.equals("Paris"));

	}

	@Test
	public void flightDateMaxNumSeats() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Optional<Flight> flightDateMaxPrice = flightService
				.flightDateMaxNumSeats(flightCollection, date);
		Assert.assertTrue(flightDateMaxPrice.isPresent());
		Assert.assertTrue(flightDateMaxPrice.get().getDestination()
				.equals("Madrid"));

	}

	@Test
	public void flightflightDateMaxNumSeatsAndNumPassengers() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Optional<Flight> maxNumSeatsAndNumPassengers = flightService
				.flightDateMaxNumSeatsAndNumPassengers(flightCollection, date);
		Assert.assertTrue(maxNumSeatsAndNumPassengers.isPresent());
		Assert.assertTrue(maxNumSeatsAndNumPassengers.get().getDestination()
				.equals("Paris"));

	}

	@Test
	public void flightflightDateMinNumSeatsAndNumPassengers() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		Optional<Flight> minNumSeatsAndNumPassengers = flightService
				.flightDateMinNumSeatsAndNumPassengers(flightCollection, date);
		Assert.assertTrue(minNumSeatsAndNumPassengers.isPresent());
		Assert.assertTrue(minNumSeatsAndNumPassengers.get().getDestination()
				.equals("Japan"));

	}

	@Test
	public void flightDateNumPassengersSum() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		int flightDateNumPassengersSum = flightService
				.flightDateNumPassengersSum(flightCollection, date);
		Assert.assertEquals(flightDateNumPassengersSum, 739);

	}

	@Test
	public void flightDatePriceAverage() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		OptionalDouble flightDatePriceAverage = flightService
				.flightDatePriceAverage(flightCollection, date);
		Assert.assertTrue(flightDatePriceAverage.isPresent());
		Assert.assertTrue(flightDatePriceAverage.getAsDouble() == 1050);

	}

	@Test
	public void flightDatePriceAverageOtherWay() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		double flightDatePriceVariance = flightService
				.flightDatePriceAverageOtherWay(flightCollection, date);
		Assert.assertTrue(flightDatePriceVariance == 1050);

	}

	@Test
	public void flightDatePriceAverageOtherWayEmptyCollection() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = new ArrayList<Flight>();
		double flightDatePriceVariance = flightService
				.flightDatePriceAverageOtherWay(flightCollection, date);
		Assert.assertTrue(flightDatePriceVariance == 0);

	}

	@Test
	public void flightDateOrderByNumSeatsAndPassengers() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		Stream<Flight> flightDateOrderByNumSeatsAndNumPassengers = flightService
				.flightDateOrderByNumSeatsAndNumPassengers(flightCollection,
						date);

	}

	@Test
	public void flightDateReduceByDuration() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		Duration flightDateReduceByDuration = flightService
				.flightDateReduceByDuration(flightCollection, date);
		Assert.assertTrue(flightDateReduceByDuration.getHours() == 11);
		Assert.assertTrue(flightDateReduceByDuration.getMinutes() == 0);
	}

	@Test
	public void flightDateflightDateAddTenPercentAditionalPrice() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, true);
		flightService.flightDateAddTenPercentAditionalPrice(flightCollection,
				date);

		flightCollection.forEach(flight -> {
			if (flight.getDestination().equals("Japan")) {

				Assert.assertTrue(flight.getPrice() == 2200);
			} else {
				if (flight.getDestination().equals("Madrid")) {

					Assert.assertTrue(flight.getPrice() == 880);
				}

			}

		});

	}

	@Test
	public void flightDateGetListDuration() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		List<Duration> flightDateGetListDuration = flightService
				.flightDateGetListDuration(flightCollection, date);
		Assert.assertTrue(!flightDateGetListDuration.isEmpty());
		Assert.assertTrue(flightDateGetListDuration.size() == 4);
	}

	@Test
	public void flightDateGetSetDuration() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Set<Duration> flightDateGetSetDuration = flightService
				.flightDateGetSetDuration(flightCollection, date);
		Assert.assertTrue(!flightDateGetSetDuration.isEmpty());
		Assert.assertTrue(flightDateGetSetDuration.size() == 4);
	}

	@Test
	public void flightDateGetMapDuration() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		Map<String, Duration> flightDateGenerateMapDestionationDuration = flightService
				.flightDateGenerateMapDestionationDuration(flightCollection,
						date);
		Assert.assertTrue(!flightDateGenerateMapDestionationDuration.isEmpty());
		Assert.assertTrue(flightDateGenerateMapDestionationDuration.size() == 4);

		Duration duration = flightDateGenerateMapDestionationDuration
				.get("Madrid");
		// foreach of a map example
		// flightDateGenerateMapDestionationDuration.forEach((destination,durationNew)->
		// System.out.println(destination));
		Assert.assertTrue(duration.getMinutes() == 30);
		Assert.assertTrue(duration.getHours() == 2);
	}

	@Test
	public void flightDateGenerateMapPriceLowCost() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		// low cost is a flight cost less or equal 800 euros
		Map<Boolean, List<Flight>> flightDateGenerateMapPriceLowCost = flightService
				.flightDateGenerateMapPriceLowCost(flightCollection, date);
		Assert.assertTrue(!flightDateGenerateMapPriceLowCost.isEmpty());
		Assert.assertTrue(flightDateGenerateMapPriceLowCost.size() == 2);
		// assert to test low cost flight and premium flight are correct in the
		// map
		Assert.assertTrue(flightDateGenerateMapPriceLowCost.get(true).stream()
				.anyMatch(flight -> flight.getDestination().equals("Madrid")));
		Assert.assertTrue(flightDateGenerateMapPriceLowCost.get(true).stream()
				.anyMatch(flight -> flight.getDestination().equals("Paris")));
		Assert.assertTrue(flightDateGenerateMapPriceLowCost
				.get(false)
				.stream()
				.filter(flight -> flight.getDestination().equals("Japan")
						|| flight.getDestination().equals("London")).count() == 2);
	}

	@Test
	public void flightDateGenerateMapGroupByNumSeats() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		// low cost is a flight cost less or equal 800 euros
		Map<Integer, List<Flight>> flightDateGenerateMapGroupByNumSeats = flightService
				.flightDateGenerateMapGroupByNumSeats(flightCollection, date);
		Assert.assertTrue(!flightDateGenerateMapGroupByNumSeats.isEmpty());
		Assert.assertTrue(flightDateGenerateMapGroupByNumSeats.size() == 2);
		// assert to test flight of 200 seats are 3 and 255 seats there is only
		// one object in the list
		Assert.assertTrue(flightDateGenerateMapGroupByNumSeats.get(200).size() == 3);
		Assert.assertTrue(flightDateGenerateMapGroupByNumSeats.get(255).size() == 1);
		// assert to test flight of 200 seats are 3 and 255 seats there is only
		// one object in the list
		Assert.assertFalse(flightDateGenerateMapGroupByNumSeats
				.containsKey(344));
	}

	@Test
	public void flightDateGroupByDestinationAveragePrice() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		// low cost is a flight cost less or equal 800 euros
		Map<String, Double> flightGroupByDateSumPrice = flightService
				.flightDateGroupByDestinationAveragePrice(flightCollection,
						date);
		Assert.assertTrue(!flightGroupByDateSumPrice.isEmpty());
		Assert.assertTrue(flightGroupByDateSumPrice.size() == 4);
		Assert.assertTrue(flightGroupByDateSumPrice.containsKey("Madrid"));
		Assert.assertTrue(flightGroupByDateSumPrice.get("Madrid") == 800);

	}

	@Test
	public void flightGroupByDateSumPrice() {

		LocalDate date = LocalDate.of(2014, 5, 15);
		Collection<Flight> flightCollection = getFlightCollection(date, false);
		// low cost is a flight cost less or equal 800 euros
		Map<LocalDate, Double> flightGroupByDateSumPrice = flightService
				.flightGroupByDateSumPrice(flightCollection, date);
		Assert.assertTrue(!flightGroupByDateSumPrice.isEmpty());
		Assert.assertTrue(flightGroupByDateSumPrice.size() == 1);
		Assert.assertTrue(flightGroupByDateSumPrice.containsKey(date));

	}

	@Test(expected = IllegalStateException.class)
	public void testOptionalNewJavaClass() {

		Optional<String> optional = Optional.of("bam");
		Assert.assertTrue(optional.isPresent());
		optional = Optional.empty();
		Assert.assertFalse(optional.isPresent());
		optional.ifPresent(x -> System.out.println(x));
		String orElse = optional.orElse("pepe");
		Assert.assertTrue(orElse.equals("pepe"));
		optional.orElseThrow(IllegalStateException::new);
	}

}
