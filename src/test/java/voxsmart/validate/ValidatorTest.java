package voxsmart.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidatorTest {

    @ParameterizedTest
    @MethodSource("paramTestInput")
    public void should_throw_exception_when_input_is_not_valid(final String dialledNumber, final String userNumber) {
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            Validator.validate(dialledNumber, userNumber);
        });

        Assertions.assertEquals("User Number/Dialled Number cannot be null or empty", exception.getMessage());
    }

    @Test
    public void should_throw_no_exception_when_input_valid() {
        Validator.validate("07655788777", "0763488777");
    }

    private static Stream<Arguments> paramTestInput() {
        return Stream.of(
                Arguments.of(null, ""),
                Arguments.of("", null),
                Arguments.of("", ""),
                Arguments.of("07655788777", null),
                Arguments.of(null, "08878976896"));
    }
}
