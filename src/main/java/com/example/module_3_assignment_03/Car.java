package com.example.module_3_assignment_03;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Car {
    ImageView imageView;
    double speed;
    boolean in_use;
    String direction;

    public Car(ImageView imageView, int speed, boolean in_use) {
        this.imageView = imageView;
        this.speed = speed;
        this.in_use = in_use;
        this.direction = "right";
    }
    //commenting this method as it doesnt work yet.
    /*
    public Color getPixelColor() {
        double x = imageView.getLayoutX();
        double y = imageView.getLayoutY();
        javafx.scene.image.PixelReader pixelReader = imageView.getImage().getPixelReader();
        Color color = new Color(0,0,1.0);
        color = switch (direction) {
            case "right" -> pixelReader.getColor((int) x + 3, (int) y);
            case "left" -> pixelReader.getColor((int) x - 3, (int) y);
            case "up" -> pixelReader.getColor((int) x, (int) y + 3);
            case "down" -> pixelReader.getColor((int) x, (int) y - 3);
            default -> color;
        };

        return color;
    }
    */
    // moved the image of the car depending on what key you press
    public void move(KeyEvent event) {
        //switch case to get what input the user typed and translate it to a direction
        //makes sure to also set the direction which we will use to make sure the car is in bounds
        switch (event.getCode()) {
            case W:
                imageView.setLayoutY(imageView.getLayoutY() - speed);
                direction = "up";
                break;
            case S:
                imageView.setLayoutY(imageView.getLayoutY() + speed);
                direction = "down";
                break;
            case A:
                imageView.setLayoutX(imageView.getLayoutX() - speed);
                direction = "left";
                break;
            case D:
                imageView.setLayoutX(imageView.getLayoutX() + speed);
                direction = "right";
                break;
        }
    }
}