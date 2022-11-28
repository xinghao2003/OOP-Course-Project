/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafx_fxml.Factory;

import java.util.ArrayList;

public class Factory {
    private static ArrayList<Machine> machines = new ArrayList<Machine>();
    private static ArrayList<AC> ac = new ArrayList<AC>();
    private double totalPower = 0.0;
    
    //Prediction
    private double totalElectricalConsumptionPerHour = 0.0;
    private double totalElectricalConsumptionPerDay = 0.0;
    private double totalElectricalConsumptionPerMonth = 0.0;
    
    private double maxElectricalConsumptionPerMonth = 0.0;

    public Factory(double maxElectricalConsumptionPerMonth) {
        this.maxElectricalConsumptionPerMonth = maxElectricalConsumptionPerMonth;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public ArrayList<AC> getAc() {
        return ac;
    }

    public void setAc(ArrayList<AC> ac) {
        this.ac = ac;
    }

    public double getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(double totalPower) {
        this.totalPower = totalPower;
    }

    public double getTotalElectricalConsumptionPerHour() {
        return totalElectricalConsumptionPerHour;
    }

    public void setTotalElectricalConsumptionPerHour(double totalElectricalConsumptionPerHour) {
        this.totalElectricalConsumptionPerHour = totalElectricalConsumptionPerHour;
    }

    public double getTotalElectricalConsumptionPerDay() {
        return totalElectricalConsumptionPerDay;
    }

    public void setTotalElectricalConsumptionPerDay(double totalElectricalConsumptionPerDay) {
        this.totalElectricalConsumptionPerDay = totalElectricalConsumptionPerDay;
    }

    public double getTotalElectricalConsumptionPerMonth() {
        return totalElectricalConsumptionPerMonth;
    }

    public void setTotalElectricalConsumptionPerMonth(double totalElectricalConsumptionPerMonth) {
        this.totalElectricalConsumptionPerMonth = totalElectricalConsumptionPerMonth;
    }

    public double getMaxElectricalConsumptionPerMonth() {
        return maxElectricalConsumptionPerMonth;
    }

    public void setMaxElectricalConsumptionPerMonth(double maxElectricalConsumptionPerMonth) {
        this.maxElectricalConsumptionPerMonth = maxElectricalConsumptionPerMonth;
    }
    
    public void calculateTotalPower() {
        totalPower = 0.0;
        for (int i = 0; i < ac.size(); i++) { 		      
            totalPower += ac.get(i).getCurrentPower();
        }  
        for (int i = 0; i < machines.size(); i++) { 		      
            totalPower += machines.get(i).getCurrentPower();
        }
    }
    
    public void calculateTotalElectricalConsumption() {
        totalElectricalConsumptionPerHour = 0.0;
        totalElectricalConsumptionPerDay = 0.0;
        totalElectricalConsumptionPerMonth = 0.0;
        for (int i = 0; i < ac.size(); i++) { 		      
            totalElectricalConsumptionPerHour += ac.get(i).getElectricalConsumptionPerHour();
            totalElectricalConsumptionPerDay += ac.get(i).getElectricalConsumptionPerDay();
            totalElectricalConsumptionPerMonth += ac.get(i).getElectricalConsumptionPerMonth();
        }  
        for (int i = 0; i < machines.size(); i++) { 		      
            totalElectricalConsumptionPerHour += machines.get(i).getElectricalConsumptionPerHour();
            totalElectricalConsumptionPerDay += machines.get(i).getElectricalConsumptionPerDay();
            totalElectricalConsumptionPerMonth += machines.get(i).getElectricalConsumptionPerMonth();
        }
    }
    
    public void updateFactory(int time) {
        for (int i = 0; i < machines.size(); i++) { 		      
            machines.get(i).updateMachine(time);
        } 
        for (int i = 0; i < ac.size(); i++) { 		      
            ac.get(i).updateAC(time);
        }  
        calculateTotalPower();
        calculateTotalElectricalConsumption();
    }
}
