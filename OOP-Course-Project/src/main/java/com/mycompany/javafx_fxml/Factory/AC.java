/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafx_fxml.Factory;

public class AC extends ElectricalAppliance {

    //AC attribute
    protected double targetTemperature;
    protected double operationTemperature;
    protected double ambientTemperature;

    //Status
    protected int status;
    //Status type
    public static final int COOLING = 2;
    public static final int MAINTAINING = 3;
    public static final int FAILURE = -1;
    
    private double initialAmbientTemp = 0.0;

    public AC(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
        this.startAC();
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public Double getOperationTemperature() {
        return operationTemperature;
    }

    public void setOperationTemperature(double operationTemperature) {
        this.operationTemperature = operationTemperature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
            case COOLING:
            case MAINTAINING:
                this.setOnline(true);
                break;
            case FAILURE:
                this.setWarning(true);
                break;
            default:
                break;
        }
    }
    
    //AC Logic
    public void updateAC(int time) {
        if(online){
            //Simulating ambient temperature
            double ambientTemp = Math.random() * ((initialAmbientTemp - 0.5) - (initialAmbientTemp + 0.5) + 1) + (initialAmbientTemp + 0.5);
            this.setAmbientTemperature(ambientTemp);
            
            //Simulating power consumption
            double randomPower;
            
            if(ambientTemp > targetTemperature){
                this.setStatus(COOLING);
                this.setOperationTemperature(targetTemperature - 1);
                
                //Simulaiting ambient temperature decrease when online
                initialAmbientTemp -= ((double) onlineTime / 1000);
                randomPower = Math.random() * (3500 - 3000 + 1) + 3000;
            }else{
                this.setStatus(MAINTAINING);
                
                //Simulating temperature maintaining
                double randomTemp = Math.random() * (0.8 - 0.3 + 1) + 0.3;
                this.setOperationTemperature(targetTemperature + randomTemp);
                randomTemp = Math.random() * (0.3 - 0.2 + 1) + 0.2;
                this.setAmbientTemperature(ambientTemperature + randomTemp);
                
                randomPower = Math.random() * (2000 - 1500 + 1) + 1500;
            }
            
            //Logic
            this.increaseOnlineTime(time);
            this.setCurrentPower(randomPower);
            this.calculateAveragePower();
            this.calculateElectricalConsumption();
        }else{
            //Simulating temperature increase when offline
            if(ambientTemperature < initialAmbientTemp){
                ambientTemperature += Math.abs(initialAmbientTemp - ambientTemperature) / 100;
            }
        }
    }
    
    public final void startAC() {
        //Logic
        this.setOnline(true);
        
        //Simulating ambient temperature
        initialAmbientTemp = (initialAmbientTemp == 0.0) ? (Math.random() * (35 - 30 + 1) + 30) : ambientTemperature;
    }
    
    //Shutdown
    public void stopAC() {
        //Logic
        this.setOnline(false);
        this.setOnlineTime(0);
        this.setCurrentPower(0.0);
        this.setAveragePower(0);
        this.calculateElectricalConsumption();
        
        //Simulating ambient temperature
        initialAmbientTemp = Math.random() * (35 - 30 + 1) + 30;
    }
}
