module com.team19.priorityqueue {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.team19.priorityqueue to javafx.fxml;
    exports com.team19.priorityqueue;
}