package voxsmart.parser;

import voxsmart.matcher.InternationalNumberMatcher;
import voxsmart.validate.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * This class is constructed with two maps. One is a mapping of  country to country code and the other is a mapping
 * of country to national trunk prefix. The parse method takes the dialled number and the user
 * number strings and returns the international number as a string.
 */
public class NumberParser {

    private static final Logger logger = LoggerFactory.getLogger(NumberParser.class);

    public static final String INTERNATIONAL_NUMBER_PREFIX = "+";
    private final Map<String, Integer> countryCodes;
    private final Map<String, String> nationalTrunkPrefixes;

    public NumberParser(final Map<String, Integer> countryCodes, final Map<String, String> nationalTrunkPrefixes) {
        this.countryCodes = countryCodes;
        this.nationalTrunkPrefixes = nationalTrunkPrefixes;
    }

    public String parse(final String dialledNumber, final String userNumber) {
        logger.info("Parsing number, dialledNumber[{}], userNumber[{}]", dialledNumber, userNumber);
        Validator.validate(dialledNumber, userNumber);

        if(dialledNumber.startsWith(INTERNATIONAL_NUMBER_PREFIX)) {
            logger.debug("Number already in International format.. nothing to do..");
            return dialledNumber;
        } else{
            logger.debug("Determine country code from user number.");
            final String countryCode = findCountryCode(dialledNumber, userNumber);
            logger.debug("Country code for user number is {}.", countryCode);
            return parseLocalNumber(countryCode, dialledNumber);
        }
    }

    private String findCountryCode(final String dialledNumber, final String userNumber) {
        return countryCodes.entrySet()
                .stream()
                .filter(v -> InternationalNumberMatcher.isMatch(userNumber, v.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("No Country set for dialled user number"));
    }

    private String parseLocalNumber(final String countryCode,final String dialledNumber) {
        final String localDialingCode = this.nationalTrunkPrefixes.get(countryCode);
         if(dialledNumber.startsWith(localDialingCode)) {
             String output = INTERNATIONAL_NUMBER_PREFIX + this.countryCodes.get(countryCode) + dialledNumber.substring(localDialingCode.length());
             logger.info("Parsed dialled number to {}.", output);
             return output;
         }
         throw new IllegalStateException("Unable to process number with current dialing code, please check number is correct");
    }
}
