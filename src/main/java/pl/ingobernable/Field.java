package pl.ingobernable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Field extends Rectangle {

     Field(int axisX, int axisY){
        setWidth(10);
        setHeight(10);
        setLayoutX(axisX);
        setLayoutY(axisY);
        setFill(new Color(0, 0, 0, 1));

    }

    void setBlack(){
        this.setFill(new Color(0, 0, 0, 1));
    }

    void setGreen(){
        this.setFill(new Color(0, 1, 0, 1));
    }

    void setRed(){
        this.setFill(new Color(1, 0, 0, 1));
    }

}
