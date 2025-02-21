package com.example.module_3_assignment_03;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ImageView Car;

    @FXML
    private ImageView Maze_1;

    @FXML
    private ImageView Maze_2;

    @FXML
    private ImageView Robot;
// car class
    public class Car {
        ImageView vehicle;
        int speed;
        boolean in_use;
        public Car(ImageView vehicle,int speed, boolean in_use) {
            this.vehicle = vehicle;
            this.speed = speed;
            this.in_use = in_use;
        }
        // I have not been able to get the car to move feel free to edit
        @FXML
        void user_move(KeyEvent event) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(500), Car);
            if (event.getCode() == KeyCode.W) {
                tt.setByX(speed);
            }
            if (event.getCode() == KeyCode.S) {
                tt.setByY(-speed);
            }
            if (event.getCode() == KeyCode.A) {
                tt.setByX(-speed);
                //vehicle.setRotate(180);
                //vehicle.setX(-vehicle.getFitWidth());

            }
            if (event.getCode() == KeyCode.D) {
                //tt.setByX(speed);
            }
        }
    }
    Car car_one = new Car(Car,10,true);

}
