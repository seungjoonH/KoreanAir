package global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static String timeToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	public static LocalDateTime stringToTime(String string) {
		if (string.length() == "yyyy-MM-dd HH:mm".length()) string += ":00";
		return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static String onlyDateToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String onlyTimeToString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}