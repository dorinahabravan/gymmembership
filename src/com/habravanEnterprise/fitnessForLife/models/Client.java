package com.habravanEnterprise.fitnessForLife.models;

import java.time.LocalDate;
import java.util.Objects;

public class Client {

    // Declararea cimpurilor(variabilelor) si incapsularea(ascunderea) informatiei despre client.
    private int customerid;
    private int userid;
    private String fullname;
    private String location;
    private int phone_number;
    private String email_address;
    private LocalDate date_of_birth;

    public Client() {

    }
// Inserarea constructorilor.

    public Client(int customerid, int userid, String fullname, String location, int phone_number, String email_address, LocalDate date_of_birth) {
        this.customerid = customerid;
        this.userid = userid;
        this.fullname = fullname;
        this.location = location;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.date_of_birth = date_of_birth;
    }

    // Get si Set(Cu ajutorul lor vedem si setam datele incapsulate publice).
    public int getCustomerid() {
        return customerid;

    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;

    }

    public int getUserID() {
        return userid;

    }

    public void setUserID(int userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;

    }

    public String getEmail_address() {
        return email_address;

    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;

    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;

    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;

    }

    public String getLocation() {
        return location;

    }

    public void setLocation(String location) {
        this.location = location;

    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.fullname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        return true;
    }

    // @Override
    //   public String toString() {
    //     return "Client{" + "customerid=" + customerid + userid=" + userid + ", fullname=" + fullname + ", email_address=" + email_address + ", date_of_birth=" + date_of_birth + ", location=" + location + ", phone_number=" + phone_number + '}';
    @Override
    public String toString() {
        return "Client{" + "customerid=" + customerid + ", userid=" + userid + ", fullname=" + fullname + ", email_address=" + email_address + ", date_of_birth=" + date_of_birth + ", location=" + location + ", phone_number=" + phone_number + '}';
    }
}
