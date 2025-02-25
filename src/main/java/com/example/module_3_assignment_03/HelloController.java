package com.example.module_3_assignment_03;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class HelloController {

    @FXML
    private ImageView car_one;

    @FXML
    private ImageView droidImgView;

    @FXML
    private ImageView Maze_1;

    @FXML
    private ImageView Maze_2;

    // car class
    private Car vehicle;

    private Droid droid;

    @FXML
    Button ctrlDroid, ctrlCar, animate;

    //Initializer for the car, needed to make sure the image is loaded
    public void initialize() {
        if (car_one != null && Maze_1 != null) {
            vehicle = new Car(car_one, 3, false, Maze_1);
            vehicle.setIn_Use(false);
        }

        if (droidImgView != null && Maze_1 != null) {
            droid = new Droid(droidImgView, 3, false, Maze_1);
            droid.setIn_Use(false);
        }

    }
    //calls the internal move method of the car
    @FXML
    public void user_move(KeyEvent event) {
        if (vehicle != null && vehicle.getIn_Use()) {
            vehicle.move(event);
            return;
        }
        if (vehicle != null && droid.getIn_Use()) {
            droid.move(event);
            return;
        }
    }
    //debugging method
    /*
    @FXML
    void getpixel(MouseEvent event) {
        int x = (int) Math.round(event.getX());
        int y = (int) Math.round(event.getY());

        System.out.println("Clicked Pixel Coordinates: (" + x + ", " + y + ")");
        PixelReader pixelReader = Maze_1.getImage().getPixelReader();
        System.out.println("The color is " + pixelReader.getColor(x, y));
    }
     */

    //on button press activates the droid
    @FXML
    void use_droid(ActionEvent event) {
        droid.setIn_Use(true);
        vehicle.setIn_Use(false);
    }
    //on button press activates the car
    @FXML
    void use_car(ActionEvent event) {
        vehicle.setIn_Use(true);
        droid.setIn_Use(false);
    }

}
