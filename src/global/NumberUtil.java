package global;

import java.text.DecimalFormat;

public class NumberUtil {
    public static String toDecimalFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(number);
    }

    public static double doubleRound(double number, int digit) {
        return Math.round(number * Math.pow(10, digit)) / Math.pow(10, digit);
    }
}
