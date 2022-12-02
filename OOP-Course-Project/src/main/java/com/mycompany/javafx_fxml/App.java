package com.mycompany.javafx_fxml;

import com.mycompany.javafx_fxml.Factory.AC;
import com.mycompany.javafx_fxml.Factory.Factory;
import com.mycompany.javafx_fxml.Factory.Machine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Factory fac = new Factory(50000);
    
    public static Factory getFac() {
        return fac;
    }

    public void setFac(Factory fac) {
        App.fac = fac;
    }
    
    private void initializeFactory(){
        for(int i = 0; i < 5; i++){
            fac.getAc().add(new AC(Math.random() * (26 - 25 + 1) + 25));
            fac.getMachines().add(new Machine(Math.random() * (150 - 75 + 1) + 75));
        }
    }
    
    //Scheduler
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> updateHandler;
    
    public ScheduledFuture<?> getUpdateHandler() {
        return updateHandler;
    }

    public void setUpdateHandler(ScheduledFuture<?> updateHandler) {
        this.updateHandler = updateHandler;
    }
    
    private void updateApplication() {
        final Runnable updateAmbientTemp = () -> {
            App.getFac().updateFactory(1);
            Platform.runLater(() -> App.getFc().updateUI(1));
        };
        this.updateHandler = scheduler.scheduleAtFixedRate(updateAmbientTemp, 0, 1000, TimeUnit.MILLISECONDS);
    }
    
    
    //Scene
    private static Scene scene;
    private static FactoryController fc;

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
    }

    public static FactoryController getFc() {
        return fc;
    }

    public static void setFc(FactoryController fc) {
        App.fc = fc;
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
        
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader primary = loadFXML("factory");
        scene = new Scene(primary.load());
        stage.setScene(scene);
        stage.setTitle("OOP Course Project");
        stage.setResizable(false);
        stage.show();
        
        fc = primary.getController();
        
        initializeFactory();
        updateApplication();
    }
    
    @Override
    public void stop(){
        try (this.scheduler) {
            System.out.println("Application stopping");
            this.updateHandler.cancel(true);
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
    
}