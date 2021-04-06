package com.habravanEnterprise.fitnessForLife.models;

import java.util.Objects;

/**
 *
 * @author Dorina
 */
public class ClientMembership {

    private int clientId;
    private int gymId;

    public ClientMembership() {
    }

    public ClientMembership(int clientId, int gymId) {
        this.clientId = clientId;
        this.gymId = gymId;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.clientId;
        hash = 29 * hash + Objects.hashCode(this.gymId);
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
        final ClientMembership other = (ClientMembership) obj;
        if (this.clientId != other.clientId) {
            return false;
        }
        if (!Objects.equals(this.gymId, other.gymId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientMembership{" + "clientId=" + clientId + ", gymId=" + gymId + '}';
    }

}
