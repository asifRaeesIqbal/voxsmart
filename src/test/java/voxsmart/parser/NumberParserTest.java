package voxsmart.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voxsmart.validate.Validator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberParserTest {

    private NumberParser numberParser;

    private static final Map<String, Integer> COUNTRY_CODE = Map.of("GB", +44, "FR", +33, "US", +1, "HK", +852);
    private static final Map<String, String> TRUNK_PREFIX = Map.of("GB", "0", "FR", "0", "US", "1", "HK", "0");

    @BeforeEach
    public void before() {
        this.numberParser = new NumberParser(COUNTRY_CODE, TRUNK_PREFIX);
    }

    @Test
    public void should_parse_uk_to_uk_number() {
        final String expected  = "+447277822334";
        final String userNumber = "+447866866886";
        final String dialedNumber = "07277822334";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_parse_us_to_us_number() {
        final String expected  = "+1312233244";
        final String userNumber = "+1212233200";
        final String dialedNumber = "1312233244";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_parse_uk_to_us_number()  {
        final String expected  = "+1312233244";
        final String userNumber = "+1212233200";
        final String dialedNumber = "1312233244";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_parse_us_to_uk_number()  {
        final String expected  = "+1312233244";
        final String userNumber = "+447866866886";
        final String dialedNumber = "+1312233244";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_parse_hk_to_hk_number()  {
        final String expected  = "+85226718821";
        final String userNumber = "+85226718821";
        final String dialedNumber = "026718821";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_parse_uk_to_hk_number()  {
        final String expected  = "+85226718821";
        final String userNumber = "+447866866886";
        final String dialedNumber = "+85226718821";

        String actualNumber = numberParser.parse(dialedNumber, userNumber);

        assertEquals(expected, actualNumber);
    }

    @Test
    public void should_throw_exception_when_number_not_found()  {
        final String userNumber = "+997866866886";
        final String dialedNumber = "226718821";

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            numberParser.parse(dialedNumber, userNumber);
        });
        Assertions.assertEquals("No Country set for dialled user number", exception.getMessage());
    }
}
