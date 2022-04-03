package voxsmart.matcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class InternationalNumberMatcherTest {

    @ParameterizedTest
    @MethodSource("paramTestInputFalse")
    public void should_return_false_when_user_number_does_not_match(final String userNumber, final Integer countryCode) {
        Assertions.assertFalse(InternationalNumberMatcher.isMatch(userNumber, countryCode));
    }

    @ParameterizedTest
    @MethodSource("paramTestInputTrue")
    public void should_return_true_when_match(final String userNumber, final Integer countryCode) {
        Assertions.assertTrue(InternationalNumberMatcher.isMatch(userNumber, countryCode));
    }

    private static Stream<Arguments> paramTestInputFalse() {
        return Stream.of(
                Arguments.of("093939399", +44),
                Arguments.of("+493939399", +44),
                Arguments.of("+1212233200", +35),
                Arguments.of("+33149527154", +44),
                Arguments.of("493939399+44", +66));
    }

    private static Stream<Arguments> paramTestInputTrue() {
        return Stream.of(
                Arguments.of("+442323232323", +44),
                Arguments.of("+1212233200", +1),
                Arguments.of("+33149527154", +33),
                Arguments.of("+85225218121", +852));
    }
}
