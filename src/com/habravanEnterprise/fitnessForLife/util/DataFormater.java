package com.habravanEnterprise.fitnessForLife.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataFormater {

    public static LocalDate convertStringToLocaldate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;

    }

}
