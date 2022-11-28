/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafx_fxml.Factory;

public class Machine extends ElectricalAppliance {
    protected double targetUtilization;
    protected double currentUtilization;
    protected int status;
    

    public static final int OFF = 2;
    public static final int ON = 3;
    public static final int OVERLOAD = -1;

    public Machine(double targetUtilization) {
        this.targetUtilization = targetUtilization;
        startMachine();
    }

    public double getTargetUtilization() {
        return targetUtilization;
    }

    public void setTargetUtilization(double targetUtilization) {
        this.targetUtilization = targetUtilization;
    }

    public double getCurrentUtilization() {
        return currentUtilization;
    }

    public void setCurrentUtilizatoin(double currentUtilization) {
        this.currentUtilization = currentUtilization;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void updateMachine(int time) {
        if(online){
            double randomUtilization = Math.random() * (5 - 2 + 1) + 2;
            
            this.currentUtilization = targetUtilization - 3 + randomUtilization;
            
            //Logic
            this.increaseOnlineTime(time);
            this.setCurrentPower((targetUtilization * 100) + (randomUtilization * 100));
            this.calculateAveragePower();
            this.calculateElectricalConsumption();
        }else{
            
        }
    }
    
    public final void startMachine() {
        //Logic
        this.setOnline(true);
        this.status = ON;
        if(targetUtilization > 100){
            this.status = OVERLOAD;
        }
    }
    
    public void stopMachine() {
        //Logic
        this.setOnline(false);
        this.setOnlineTime(0);
        this.setCurrentPower(0.0);
        this.setAveragePower(0);
        this.calculateElectricalConsumption();
        this.currentUtilization = 0;
    }
}
