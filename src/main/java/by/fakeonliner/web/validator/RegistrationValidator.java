package by.fakeonliner.web.validator;

import by.fakeonliner.controller.constant.Regex;

public class RegistrationValidator {
    public boolean isNullOrEmpty(String str) {
        if (str != null) {
            return str.isEmpty();
        }
        return false;
    }

    public boolean isCorrectEmail(String email) {
        return email.matches(Regex.VALID_EMAIL_ADDRESS_REGEX);
    }

    public boolean isCorrectPhone(String phoneNumber) {
        return phoneNumber.matches(Regex.VALID_PHONE_NUMBER_REGEX);
    }
}
