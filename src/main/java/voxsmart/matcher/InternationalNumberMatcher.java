package voxsmart.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple class to check whether a user number matches a specific country dialling code
 * example (check US)
 *          userNumber : +1212233200
 *          countryCode: 1
 *
 * This would return true as the code for US is 1 and that would match the regex
 *
 */
public class InternationalNumberMatcher {

    private static final String REGEX = "\\+{1}(%s)\\d+";

    public static Boolean isMatch(final String userNumber, final Integer countryCode) {
        String format = String.format(REGEX, countryCode.toString());
        Pattern pattern = Pattern.compile(format, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userNumber);
        return matcher.find();
    }
}
