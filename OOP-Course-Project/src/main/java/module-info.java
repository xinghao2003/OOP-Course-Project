module com.mycompany.javafx_fxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.javafx_fxml to javafx.fxml;
    exports com.mycompany.javafx_fxml;
}
