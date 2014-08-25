package com.cristian.mylab;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class NewFeaturesTest {

	@Test(expected = IllegalStateException.class)
	public void testOptionalNewJavaClass() {
		// create an optional object
		Optional<String> optional = Optional.of("bam");
		Assert.assertTrue(optional.isPresent());

		// setting empty value to optional object
		optional = Optional.empty();
		Assert.assertFalse(optional.isPresent());
		// this line do not write to console output
		optional.ifPresent(x -> System.out.println(x));

		// testing orelse method to set a value if optional object is empty
		String orElse = optional.orElse("pepe");
		Assert.assertTrue(orElse.equals("pepe"));
		optional.orElseThrow(IllegalStateException::new);
	}

	@Test
	public void testNewDateApiLocalTime() {
		// create localDateTime with 12:59:59 and 23:59:59
		LocalTime localTime1 = LocalTime.of(12, 59, 59);
		LocalTime localTime2 = LocalTime.of(23, 59, 59);

		// calculate difference between 2 localtime
		long hoursBetween = ChronoUnit.HOURS.between(localTime1, localTime2);
		long minutesBetween = ChronoUnit.MINUTES
				.between(localTime1, localTime2);
		// access localtime methods like to compare if a localTime is before or
		// after, and hours minutes of difference between 2 dates
		Assert.assertTrue(localTime1.isBefore(localTime2));
		Assert.assertFalse(localTime1.isAfter(localTime2));
		Assert.assertTrue(hoursBetween == 11);
		Assert.assertTrue(minutesBetween == 660);

		// deduct hours and minutes of a localtime

		Duration ofHours = Duration.ofHours(1);
		LocalTime newTime = localTime1.minus(ofHours);
		Assert.assertTrue(newTime.getHour() == 11);

		Duration minusSeconds = ofHours.minusSeconds(10);
		LocalTime newTimeMinusSecond = localTime1.minus(minusSeconds);
		Assert.assertTrue(newTimeMinusSecond.getHour() == 12);
		Assert.assertTrue(newTimeMinusSecond.getSecond() == 9);

		Duration minusMinutes = Duration.ofMinutes(10);
		LocalTime minus = newTimeMinusSecond.minus(minusMinutes);
		Assert.assertTrue(minus.getMinute() == 50);

		// create a datetime with uk formatter

		DateTimeFormatter ukFormatter = DateTimeFormatter.ofLocalizedTime(
				FormatStyle.SHORT).withLocale(Locale.UK);

		LocalTime leetTime = LocalTime.parse("13:37", ukFormatter);

		Assert.assertTrue(leetTime.getHour() == 13);
		Assert.assertTrue(leetTime.getMinute() == 37);
		Assert.assertTrue(leetTime.getSecond() == 0);
		// period (for date) versus duration (for time)

	}

	@Test
	public void testNewDateApiLocalDate() {

		// created a localdate 2018-12-11 (yyyy mm dd)

		LocalDate localDate = LocalDate.of(2018, 12, 11);

		// Access day of week day of month day of year and month

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		int dayOfMonth = localDate.getDayOfMonth();
		Month month = localDate.getMonth();

		Assert.assertTrue(dayOfWeek.equals(DayOfWeek.TUESDAY));
		Assert.assertTrue(dayOfMonth == 11);
		Assert.assertTrue(localDate.getDayOfYear() == 345);
		Assert.assertTrue(month.equals(Month.DECEMBER));

		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
				FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);

		Assert.assertTrue(xmas.getMonth().equals(Month.DECEMBER));
		Assert.assertTrue(xmas.getYear() == 2014);

	}

	@Test
	public void testNewDateApiLocalDateTime() {

		LocalDateTime localDateTime = LocalDateTime.of(2014, Month.DECEMBER,
				31, 23, 59, 59);

		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
		Assert.assertTrue(dayOfWeek.equals(DayOfWeek.WEDNESDAY));

		Month month = localDateTime.getMonth();
		Assert.assertTrue(month.equals(Month.DECEMBER));

		long minuteOfDay = localDateTime.getLong(ChronoField.MINUTE_OF_DAY);
		Assert.assertTrue(minuteOfDay == 1439);

		long dayOfYear = localDateTime.getLong(ChronoField.DAY_OF_YEAR);
		Assert.assertTrue(dayOfYear == 365);

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("MMM dd, yyyy - HH:mm");

		LocalDateTime parseLocalDateTime = LocalDateTime.parse(
				"Nov 03, 2014 - 07:13", formatter);

		Assert.assertTrue(parseLocalDateTime.getMonth().equals(Month.NOVEMBER));
		Assert.assertTrue(parseLocalDateTime.getYear() == 2014);
		Assert.assertTrue(parseLocalDateTime.getHour() == 7);
		Assert.assertTrue(parseLocalDateTime.getMinute() == 13);
		Assert.assertTrue(parseLocalDateTime.getSecond() == 0);

	}

}
