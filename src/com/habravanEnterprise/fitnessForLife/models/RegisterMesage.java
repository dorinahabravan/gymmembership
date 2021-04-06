package com.habravanEnterprise.fitnessForLife.models;

import java.time.LocalDate;

/**
 *
 * @author Dorina
 */
public class RegisterMesage {

    private LocalDate date;
    private String text;

    public RegisterMesage() {
        text = "no new message";

        date = LocalDate.now();

    }

    public RegisterMesage(LocalDate date, String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return date + "&" + text + '\n';

    }

}
