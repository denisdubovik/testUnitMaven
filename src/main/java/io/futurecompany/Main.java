package io.futurecompany;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Main {
    public static void main(String[] args) {
        is18YearsOld("2010-02-29");
    }

    public static boolean is18YearsOld(String dateOfBirth) {
        LocalDate incomingDate = parseDate(dateOfBirth);
        LocalDate today = LocalDate.now();
        return Period.between(incomingDate, today).getYears() >= 18;
    }


    private static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please provide a valid date in the format 'uuuu-MM-dd'", e);
        }
    }

}