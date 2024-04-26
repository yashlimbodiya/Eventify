package com.project.eventify.util;

import com.project.eventify.model.User;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//This class validate the input values provided by the user. Its uses several criteria to valid and sanitize inputs
public class InputValidator {

    private String userInput;


    public InputValidator(String userInput) {
        this.userInput = userInput;
    }
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static boolean isEmailValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }


    public static String escapeSingleQuotes(String input) {
        return input.replace("'", "''");
    }

    public static String escapeBackslashes(String input) {
        return input.replace("\\", "\\\\");
    }

    public static String removeHtmlTags(String input) {
        return input.replaceAll("<[^>]*>", "");
    }

    public static String trimWhitespace(String input) {
        return input.trim();
    }

    public static String sanitizeNumericInput(String input) {
        return input.replaceAll("[^0-9]", ""); // Removes all non-digit characters
    }

    public static String limitInputLength(String input, int maxLength) {
        return input.substring(0, Math.min(input.length(), maxLength));
    }

    public static String removeControlCharacters(String input) {
        return input.replaceAll("[\\x00-\\x1F\\x7F]", ""); // Removes control characters
    }

    public static String sanitizeUserInput(String input) {
        input = escapeSingleQuotes(input);
        input = escapeBackslashes(input);
        input = removeHtmlTags(input);
        input = trimWhitespace(input);
        input = removeControlCharacters(input);
        input = limitInputLength(input,100);
        return input;

    }
}
