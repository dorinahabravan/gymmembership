
package com.habravanEnterprise.fitnessForLife.models;

import java.util.Objects;

/**
 *
 * @author Dorina
 */
public class GymDetails {
    
    private int gymId;
    private String gymName;
    private String gymLocation;
    private int gymContactNumber;

    public GymDetails() {
    }

    public GymDetails(int gymId, String gymName, String gymLocation, int gymContactNumber) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.gymLocation = gymLocation;
        this.gymContactNumber = gymContactNumber;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(String gymLocation) {
        this.gymLocation = gymLocation;
    }

    public int getGymContactNumber() {
        return gymContactNumber;
    }

    public void setGymContactNumber(int gymContactNumber) {
        this.gymContactNumber = gymContactNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final GymDetails other = (GymDetails) obj;
        if (this.gymId != other.gymId) {
            return false;
        }
        if (!Objects.equals(this.gymName, other.gymName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GymDetails{" + "gymId=" + gymId + ", gymName=" + gymName + ", gymLocation=" + gymLocation + ", gymContactNumber=" + gymContactNumber + '}';
    }
    
    
    
    
    
}
