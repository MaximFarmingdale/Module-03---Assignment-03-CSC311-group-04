package com.example.module_3_assignment_03;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
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

    public void rotate() {
        switch (direction) {
            case "right" -> imageView.setRotate(180);
            case "left" -> imageView.setRotate(180);
            case "up" -> imageView.setRotate(-90);
            case "down" -> imageView.setRotate(0);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }


    public boolean validpath() {
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        int x = (int) Math.round(imageView.getLayoutX());
        int y = (int) Math.round(imageView.getLayoutY()) ;
        switch (direction) {
            case "right":
                x += 10;
                break;
            case "left":
                x -= 10;
                break;
            case "up":
                y -= 10;
                x += 3;
                break;
            case "down":
                y += 10;
                x += 3;
                break;
        }
        try {
            Color color = pixelReader.getColor(x, y);
            System.out.println("Color at (" + x + ", " + y + "): " + color.toString());

            // Compare colors properly
            if (color.equals(Color.web("0x00000000" )) || color.equals(Color.web("0x000000ff"))) {
                System.out.println("true");
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("false (out of bounds) - Exception caught");
        }
        System.out.println("false");
        return false;
    }

    // moved the image of the car depending on what key you press
    public void move(KeyEvent event) {
        //switch case to get what input the user typed and translate it to a direction
        //makes sure to also set the direction which we will use to make sure the car is in bounds
        switch (event.getCode()) {
            case W:
                direction = "up";
                if (validpath()) {
                    imageView.setLayoutY(imageView.getLayoutY() - speed);
                }
                break;
            case S:
                direction = "down";
                if (validpath()) {
                    imageView.setLayoutY(imageView.getLayoutY() + speed);
                }
                break;
            case A:
                direction = "left";
                if (validpath()) {
                    imageView.setLayoutX(imageView.getLayoutX() - speed);
                }
                break;
            case D:
                direction = "right";
                if (validpath()) {
                    imageView.setLayoutX(imageView.getLayoutX() + speed);
                }
                break;
        }
    }
}