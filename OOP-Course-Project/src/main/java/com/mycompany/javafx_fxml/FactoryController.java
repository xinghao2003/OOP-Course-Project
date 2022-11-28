package com.mycompany.javafx_fxml;

import com.mycompany.javafx_fxml.Factory.AC;
import com.mycompany.javafx_fxml.Factory.Factory;
import com.mycompany.javafx_fxml.Factory.Machine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FactoryController {
    
    @FXML
    Label totalMachineLbl, totalAcLbl, totalPowerLbl, perHourLbl, perDayLbl, perMonthLbl, monthBillLbl, facWarningLbl;
    
    @FXML
    Label targetTempLbl, ambTempLbl, opTempLbl, acPowerLbl, acStatLbl, acOnlineLbl, acIndexLbl;
    @FXML
    Button onOffAcBtn, previousAcBtn, nextAcBtn;
    
    @FXML
    Label targetUtilizeLbl, currentUtilizeLbl, macPowerLbl, macStatLbl, macOnlineLbl, macIndexLbl;
    @FXML
    Button onOffMacBtn, previousMacBtn, nextMacBtn;
          
    @FXML
    Label timeLbl;
    
    private int totalSecs = 0;
    private Factory fac = App.getFac();
    
    //AC Controller
    private int acIndex = 0;
    
    @FXML
    public void previousAC(ActionEvent e) {
        if(acIndex > 0){
            acIndex -= 1;
        }
        acIndexLbl.setText((acIndex + 1) + " / " + (fac.getAc().size()));
        updateOnOffAc();
    }
    
    @FXML
    public void nextAC(ActionEvent e) {
        if(acIndex < (fac.getAc().size() - 1)){
            acIndex += 1;
        }
        acIndexLbl.setText((acIndex + 1) + " / " + (fac.getAc().size()));
        updateOnOffAc();
    }
    
    @FXML
    public void onOffAC(ActionEvent e) {
        AC currentAc = fac.getAc().get(acIndex);
        if(currentAc.isOnline()){
            currentAc.stopAC();
        }else{
            currentAc.startAC();
        }
        updateOnOffAc();
    }
    
    private void updateOnOffAc() {
        AC currentAc = fac.getAc().get(acIndex);
        if(currentAc.isOnline()){
            onOffAcBtn.setText("Shutdown");
        }else{
            onOffAcBtn.setText("Start");
        }
    }
    
    //Machine Controller
    private int macIndex = 0;
    
    @FXML
    public void previousMac(ActionEvent e) {
        if(macIndex > 0){
            macIndex -= 1;
        }
        macIndexLbl.setText((macIndex + 1) + " / " + (fac.getMachines().size()));
        updateOnOffMac();
    }
    
    @FXML
    public void nextMac(ActionEvent e) {
        if(macIndex < (fac.getAc().size() - 1)){
            macIndex += 1;
        }
        macIndexLbl.setText((macIndex + 1) + " / " + (fac.getMachines().size()));
        updateOnOffMac();
    }
    
    @FXML
    public void onOffMac(ActionEvent e) {
        Machine currentMac = fac.getMachines().get(macIndex);
        if(currentMac.isOnline()){
            currentMac.stopMachine();
        }else{
            currentMac.startMachine();
        }
        updateOnOffMac();
    }
    
    private void updateOnOffMac() {
        Machine currentMac = fac.getMachines().get(macIndex);
        if(currentMac.isOnline()){
            onOffMacBtn.setText("Shutdown");
        }else{
            onOffMacBtn.setText("Start");
        }
    }
    
    //UI
    public void updateUI(int time){
        //Factory
        totalMachineLbl.setText(String.valueOf(fac.getMachines().size()));
        totalAcLbl.setText(String.valueOf(fac.getAc().size()));
        totalPowerLbl.setText(String.format("%.4f",fac.getTotalPower()) + " watt");
        perHourLbl.setText(String.format("%.4f", fac.getTotalElectricalConsumptionPerHour()) + " kWh");
        perDayLbl.setText(String.format("%.4f", fac.getTotalElectricalConsumptionPerDay()) + " kWh / day");
        perMonthLbl.setText(String.format("%.4f", fac.getTotalElectricalConsumptionPerMonth()) + " kWh / month");
        
        //Can be replace with any value
        monthBillLbl.setText(String.format("RM %.2f", fac.getTotalElectricalConsumptionPerMonth() * 26.70 / 100));
        
        double diff = fac.getMaxElectricalConsumptionPerMonth() - fac.getTotalElectricalConsumptionPerMonth();
        if(diff < 0){
            facWarningLbl.setText("Warning: Exceeding Max Electrical Consumption Per Month By " + String.format("%.0f kWh / month", Math.abs(diff)));
        }else{
            facWarningLbl.setText("Factory Working Under Optimal Condition");
        }
        
        //Machine
        if(!fac.getMachines().isEmpty()){
            Machine currentMac = fac.getMachines().get(macIndex);
            targetUtilizeLbl.setText(String.format("%.2f %%",currentMac.getTargetUtilization()));
            currentUtilizeLbl.setText(String.format("%.2f %%",currentMac.getCurrentUtilization()));
            macPowerLbl.setText(String.format("%.2f watt",currentMac.getCurrentPower()));
            if(currentMac.isOnline()){
                switch(currentMac.getStatus()){
                    case 2:
                        macStatLbl.setText("OFF");
                        break;
                    case 3:
                        macStatLbl.setText("ON");
                        break;
                    case -1:
                        macStatLbl.setText("OVERLOAD");
                        break;
                    default:
                        macStatLbl.setText("UNKNOWN");
                        break;
                }
            }else{
                macStatLbl.setText("OFFLINE");
            }
            macOnlineLbl.setText(formatTime(currentMac.getOnlineTime()));
        }
        macIndexLbl.setText((macIndex + 1) + " / " + (fac.getMachines().size()));
        
        //AC
        if(!fac.getAc().isEmpty()){
            AC currentAc = fac.getAc().get(acIndex);
            targetTempLbl.setText(String.format("%.2f °C",currentAc.getTargetTemperature()));
            ambTempLbl.setText(String.format("%.2f °C",currentAc.getAmbientTemperature()));
            opTempLbl.setText(String.format("%.2f °C",currentAc.getOperationTemperature()));
            acPowerLbl.setText(String.format("%.2f watt",currentAc.getCurrentPower()));
            if(currentAc.isOnline()){
                switch(currentAc.getStatus()){
                    case 2:
                        acStatLbl.setText("COOLING");
                        break;
                    case 3:
                        acStatLbl.setText("MAINTAINING");
                        break;
                    case -1:
                        acStatLbl.setText("FAILURE");
                        break;
                    default:
                        acStatLbl.setText("UNKNOWN");
                        break;
                }
            }else{
                acStatLbl.setText("OFFLINE");
            }
            acOnlineLbl.setText(formatTime(currentAc.getOnlineTime()));
        }
        acIndexLbl.setText((acIndex + 1) + " / " + (fac.getAc().size()));
        
        //Application
        totalSecs += time;
        timeLbl.setText("Application Online Time: " + formatTime(totalSecs));
    }
    
    private String formatTime(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }
    
}
