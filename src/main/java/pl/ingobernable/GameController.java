package pl.ingobernable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameController implements Initializable, Runnable{

    private final int paneWidth = 60;
    private final int paneHeight = 37;

    private final Field[][] field = new Field[paneWidth][paneHeight];

    @FXML Pane board = new Pane();
    @FXML Button play = new Button();
    @FXML Button stop = new Button();

    private Field headOfASnake;
    private int headLayoutX;
    private int headLayoutY;

    private Field apple;
    private int appleLayoutX;
    private int appleLayoutY;

    private KeyEvent keyEvent;
    private final AtomicBoolean running = new AtomicBoolean();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creteFields();
    }

    @FXML void startTheGame(){
        running.set(true);

        createSnake();
        createApple();

        play.setDisable(true);
        stop.setDisable(false);

    }

    @FXML void stopTheGame() throws IOException {
        running.set(false);

        stop.setDisable(true);
        play.setDisable(false);

        App.setRoot("gameStopScreen");
    }

    @Override
    public void run() {

        while (new AtomicBoolean(true).get()){
            while (running.get()){
                moveTheSnake();
                try {
                    Thread.sleep(150);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

     void moveTheSnake(){
        if (keyEvent.getCode() == KeyCode.W)
            moveUp(keyEvent);
        else if (keyEvent.getCode() == KeyCode.S)
            moveDown(keyEvent);
        else if (keyEvent.getCode() == KeyCode.A)
            moveLeft(keyEvent);
        else if (keyEvent.getCode() == KeyCode.D)
            moveRight(keyEvent);
    }

    private void moveUp(KeyEvent event){
        if (headLayoutY > 0)
            headLayoutY--;
        else
            event.consume();

        headOfASnake = field[headLayoutX][headLayoutY];
        headOfASnake.setGreen();

        field[headLayoutX][headLayoutY + 1].setBlack();

        createAnotherApple();
    }

    private void moveDown(KeyEvent event){
        if (headLayoutY < paneHeight - 1)
            headLayoutY++;
        else
            event.consume();

        headOfASnake = field[headLayoutX][headLayoutY];
        headOfASnake.setGreen();

        field[headLayoutX][headLayoutY - 1].setBlack();

        createAnotherApple();
    }

    private void moveRight(KeyEvent event){
        if (headLayoutX < paneWidth - 1)
            headLayoutX++;
        else
            event.consume();

        headOfASnake = field[headLayoutX][headLayoutY];
        headOfASnake.setGreen();

        field[headLayoutX - 1][headLayoutY].setBlack();

        createAnotherApple();
    }

    private void moveLeft(KeyEvent event){
        if (headLayoutX > 0)
            headLayoutX--;
        else
            event.consume();

        headOfASnake = field[headLayoutX][headLayoutY];
        headOfASnake.setGreen();

        field[headLayoutX + 1][headLayoutY].setBlack();

        createAnotherApple();
    }

    private void creteFields(){
        for (int i = 0; i < paneWidth * 10; i+=10){

            for (int j = 0; j < paneHeight * 10; j+=10){

                Field tmp = new Field(i, j);

                if (i == 0 && j == 0)
                    field[0][0] = tmp;
                else if (i == 0)
                    field[0][j / 10] = tmp;
                else if (j == 0)
                    field[i / 10][0] = tmp;
                else
                    field[i / 10][j / 10] = tmp;

                board.getChildren().add(tmp);
            }
        }
    }

    private void createSnake(){
        Random random = new Random();
        do {
            headLayoutX = random.nextInt(paneWidth - 1);
        }while (headLayoutX <= 2 || headLayoutX >= 56);

        do {
            headLayoutY = random.nextInt(paneHeight - 1);
        }while (headLayoutY <= 2 || headLayoutY >= 33);


        headOfASnake = field[headLayoutX][headLayoutY];
        headOfASnake.setGreen();
    }

    private void createApple(){
        Random random = new Random();

        do {
            appleLayoutX = random.nextInt(paneWidth - 1);
        }while (appleLayoutX == headLayoutX);

        do {
            appleLayoutY = random.nextInt(paneHeight - 1);
        }while (appleLayoutY == headLayoutY);

        apple = field[appleLayoutX][appleLayoutY];
        apple.setRed();

    }

    private void createAnotherApple(){
        if (headLayoutX == appleLayoutX && headLayoutY == appleLayoutY)
            createApple();
    }

    void setKeyEvent(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }
}
