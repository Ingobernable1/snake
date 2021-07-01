module pl.ingobernable {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.ingobernable to javafx.fxml;
    exports pl.ingobernable;
}
