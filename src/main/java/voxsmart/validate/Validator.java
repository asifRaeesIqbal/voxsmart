package voxsmart.validate;

public class Validator {

    public static void validate(final String dialledNumber, final String userNumber) {
        if (dialledNumber ==null || dialledNumber.length()==0) {
            throw new IllegalStateException("User Number/Dialled Number cannot be null or empty");
        }
        if (userNumber ==null || userNumber.length()==0) {
            throw new IllegalStateException("User Number/Dialled Number cannot be null or empty");
        }
    }
}
