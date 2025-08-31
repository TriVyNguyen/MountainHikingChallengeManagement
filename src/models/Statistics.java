/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Admin
 */
public class Statistics {

    private String mountainID;
    private int participants;
    private double totalCost;

    public Statistics() {
    }

    public Statistics(String mountainID, int participants, double totalCost) {
        this.mountainID = mountainID;
        this.participants = participants;
        this.totalCost = totalCost;
    }

    // Getters & setters
    public String getMountainID() {
        return mountainID;
    }

    public void setMountainID(String mountainID) {
        this.mountainID = mountainID;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return String.format("%-11s | %-28s | %-14.2f", this.mountainID, this.participants, this.totalCost);
    }
}
