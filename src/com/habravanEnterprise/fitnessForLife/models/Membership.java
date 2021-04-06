
package com.habravanEnterprise.fitnessForLife.models;

import java.util.Objects;

/**
 *
 * @author Dorina
 */
public class Membership {
    
    private int membershipID;
    private String membershipType;
    private String membershipPeriod;
    private String instructorName;

    public Membership() {
    }

    public Membership(int membershipID, String membershipType, String membershipPeriod, String instructorName) {
        this.membershipID = membershipID;
        this.membershipType = membershipType;
        this.membershipPeriod = membershipPeriod;
        this.instructorName = instructorName;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipPeriod() {
        return membershipPeriod;
    }

    public void setMembershipPeriod(String membershipPeriod) {
        this.membershipPeriod = membershipPeriod;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.membershipID;
        hash = 17 * hash + Objects.hashCode(this.membershipType);
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
        final Membership other = (Membership) obj;
        if (this.membershipID != other.membershipID) {
            return false;
        }
        if (!Objects.equals(this.membershipType, other.membershipType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Membership{" + "membershipID=" + membershipID + ", membershipType=" + membershipType + ", membershipPeriod=" + membershipPeriod + ", instructorName=" + instructorName + '}';
    }
    
    
}
