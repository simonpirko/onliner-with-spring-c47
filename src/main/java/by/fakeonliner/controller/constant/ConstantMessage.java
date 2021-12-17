package by.fakeonliner.controller.constant;

public enum ConstantMessage {
    FIRST_NAME_IS_EMPTY("First name must be not empty."),
    LAST_NAME_IS_EMPTY("Last name must be not empty."),
    USERNAME_IS_EMPTY("Username must be not empty."),
    PASSWORD_IS_EMPTY("Password must be not empty."),
    PHONE_NUMBER_IS_EMPTY("Phone number must be not empty."),
    PHONE_NUMBER_INPUT_INCORRECTLY("Phone number input incorrectly."),
    PHONE_NUMBER_ALREADY_EXIST("Phone number already exist."),
    EMAIL_IS_EMPTY("Email must be not empty."),
    EMAIL_INPUT_INCORRECTLY("Email input incorrectly."),
    EMAIL_ALREADY_EXIST("Email already exist."),
    USER_ALREADY_EXIST("User already exist."),
    PASSWORD_NOT_EQUALS("Passwords not equal.");


    private String message;

    ConstantMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}