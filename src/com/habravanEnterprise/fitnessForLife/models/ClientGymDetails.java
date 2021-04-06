package com.habravanEnterprise.fitnessForLife.models;

/**
 *
 * @author Dorina
 */
public class ClientGymDetails {

    private int clientID;
    private int gymID;


    public ClientGymDetails() {
    }

    public ClientGymDetails(int clientID, int gymID) {
        this.clientID = clientID;
        this.gymID = gymID;

    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getGymID() {
        return gymID;
    }

    public void setGymID(int gymID) {
        this.gymID = gymID;
    }
// Codul de aici nu am nevoie de el!!!!
//    public String getClientName() {
//        return clientName;
//    }
//
//    public void setClientName(String clientName) {
//        this.clientName = clientName;
//    }
//
//    public String getGymName() {
//        return gymName;
//    }
//
//    public void setGymName(String gymName) {
//        this.gymName = gymName;
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.clientID;
        hash = 89 * hash + this.gymID;
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
        final ClientGymDetails other = (ClientGymDetails) obj;
        if (this.clientID != other.clientID) {
            return false;
        }
        if (this.gymID != other.gymID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientMembership{" + "clientID=" + clientID + ", gymID=" + gymID + '}';

    }

}
