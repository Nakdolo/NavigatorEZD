module com.example.navigatorezd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.navigatorezd to javafx.fxml;
    exports com.example.navigatorezd;
}