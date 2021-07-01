package pl.ingobernable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StopScreenController implements Initializable {

    @FXML Button newGame = new Button();

    @FXML void setPlayScreen() throws IOException {
        App.setRoot("game");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
