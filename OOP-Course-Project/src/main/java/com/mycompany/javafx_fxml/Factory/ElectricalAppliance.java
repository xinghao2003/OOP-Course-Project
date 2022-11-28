/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafx_fxml.Factory;

public class ElectricalAppliance {
    //Unit kWh
    protected double electricalConsumptionPerHour;
    protected double electricalConsumptionPerDay;
    protected double electricalConsumptionPerMonth;
    
    //Updated per second
    protected double currentPower = 0.0;
    
    
    //Store power statistic in each second
    //protected ArrayList<Double> powerHistory  = new ArrayList<>();
    
    //Average power during online time
    protected double averagePower = 0.0;
    
    //Status
    protected boolean online = false;
    protected boolean warning = false;
    
    //Measure in second
    protected int onlineTime = 0;

    public double getElectricalConsumptionPerHour() {
        return electricalConsumptionPerHour;
    }

    public double getElectricalConsumptionPerDay() {
        return electricalConsumptionPerDay;
    }

    public double getElectricalConsumptionPerMonth() {
        return electricalConsumptionPerMonth;
    }

    public double getCurrentPower() {
        return currentPower;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setElectricalConsumptionPerHour(double electricalConsumptionPerHour) {
        this.electricalConsumptionPerHour = electricalConsumptionPerHour;
    }

    public void setElectricalConsumptionPerDay(double electricalConsumptionPerDay) {
        this.electricalConsumptionPerDay = electricalConsumptionPerDay;
    }

    public void setElectricalConsumptionPerMonth(double electricalConsumptionPerMonth) {
        this.electricalConsumptionPerMonth = electricalConsumptionPerMonth;
    }

    public void setCurrentPower(double currentPower) {
        this.currentPower = currentPower;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public int getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(int onlineTime) {
        this.onlineTime = onlineTime;
    }

    public double getAveragePower() {
        return averagePower;
    }

    public void setAveragePower(double averagePower) {
        this.averagePower = averagePower;
    }
    
    //Logic
    public void increaseOnlineTime(int time) {
        this.onlineTime += time;
    }
    
    public void calculateAveragePower() {
        if(onlineTime <= 2){
            averagePower = (currentPower + averagePower) / onlineTime;
        }else{
            averagePower = ((averagePower * (onlineTime - 1)) + currentPower) / onlineTime;
        }
    }
    
    //Calculation is based on average power
    public void calculateElectricalConsumption() {
        this.setElectricalConsumptionPerHour(averagePower / 1000);
        this.setElectricalConsumptionPerDay(electricalConsumptionPerHour * 24);
        this.setElectricalConsumptionPerMonth(electricalConsumptionPerDay * 30);
    }
    
}
