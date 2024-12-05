module com.smansu4.thermalmap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.smansu4.thermalmap to javafx.fxml;
    exports com.smansu4.thermalmap;
}