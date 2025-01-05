package com.yaksha.assignment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormattingAndParsingAssignment {

	public static void main(String[] args) {

		// Task 1: Date Formatting
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		System.out.println("Formatted Date and Time: " + now.format(dateFormatter));

		// Task 2: Parse Date Strings
		String dateStr = "2025-01-02 12:30 PM";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		System.out.println("Parsed Date and Time: " + dateTime);

		// Task 3: Time Zone Formatting
		ZonedDateTime nowInTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		DateTimeFormatter timeZoneFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
		String formattedDateInTokyo = nowInTokyo.format(timeZoneFormatter);
		System.out.println("Formatted Date and Time in Tokyo: " + formattedDateInTokyo);
	}
}
