package com.habravanEnterprise.fitnessForLife.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Dorina
 */
public class User {
// Aici declar variabilele si incapsulez informatia pe care o ofera clientul adica GUEST.

    private int Id;
    private String username;
    private String password;
    private LocalDate connectionDate;
    //Constructorul care da acces la informatie public.
    public User() {

    }

    public User(int Id, String username, String password, LocalDate date) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.connectionDate = date;
    }

  //  public User(int id, String username, String password, LocalDate date) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(LocalDate connectionDate) {
        this.connectionDate = connectionDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", username=" + username + ", password=" + password + ", connectionDate=" + connectionDate + '}';
    }


    
    
    
    
    
    
    }

    


