package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static String timeToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
	}

	public static String onlyDateToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	}

	public static String onlyTimeToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("hh:mm"));
	}
}