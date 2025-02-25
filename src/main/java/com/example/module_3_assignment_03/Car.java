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
    ImageView Maze;

    public Car(ImageView imageView, int speed, boolean in_use, ImageView Maze) {
        this.imageView = imageView;
        this.speed = speed;
        this.in_use = in_use;
        this.direction = "right";
        this.Maze = Maze;
    }
    public void setIn_Use(boolean in_use) {
        this.in_use = in_use;
        if (!in_use) {
            imageView.setVisible(false);
            imageView.setManaged(false);
            imageView.setLayoutX(-4);
            imageView.setLayoutY(167);
            return;
        }
        imageView.setVisible(true);
        imageView.setManaged(true);
    }

    public boolean getIn_Use() {
        return in_use;
    }

    public void rotate(String future_direction) {
        if (future_direction.equals(direction)) {
            return;
        }
        switch (future_direction) {
            case "right":

                imageView.setScaleX(1);
                imageView.setScaleY(1);
                imageView.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
                imageView.setRotate(0);
                break;

            case "left":
                imageView.setScaleX(-1);
                imageView.setScaleY(1);
                imageView.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
                imageView.setRotate(0);
                break;

            case "up":
                imageView.setScaleX(1);
                imageView.setScaleY(1);
                imageView.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
                imageView.setRotate(270);
                break;

            case "down":
                imageView.setScaleX(1);
                imageView.setScaleY(-1);
                imageView.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
                imageView.setRotate(90);
                break;
        }

        direction = future_direction;
    }


    public boolean validpath() {
        PixelReader pixelReader = Maze.getImage().getPixelReader();

        double imageWidth = Maze.getImage().getWidth();
        double imageHeight = Maze.getImage().getHeight();

        double imageViewWidth = Maze.getFitWidth();
        double imageViewHeight = Maze.getFitHeight();

        double scaleX = imageWidth / imageViewWidth;
        double scaleY = imageHeight / imageViewHeight;

        double relativeX = imageView.getLayoutX() - Maze.getLayoutX();
        double relativeY = imageView.getLayoutY() - Maze.getLayoutY();

        int x = (int) ((relativeX + 50) * scaleX);
        int y = (int) ((relativeY + 13) * scaleY);
        switch (direction) {
            case "right":
                x += (int) (17 * scaleX);
                break;
            case "left":
                x -= (int) (27 * scaleX);
                break;
            case "up":
                y -= (int) (6 * scaleY);
                x -= (int) (7 * scaleX);
                break;
            case "down":
                y += (int) (6 * scaleY);
                break;
            default:
                System.out.println("Invalid direction: " + direction);
                return false;
        }

        try {
            Color color = pixelReader.getColor(x, y);
            System.out.println("Color at (" + x + ", " + y + "): " + color.toString());

            // Compare colors properly
            if (color.equals(Color.web("0x005399ff" )) ) {// || color.equals(Color.web("0x000000ff"))
                System.out.println("false");
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("false (out of bounds) - Exception caught");
        }
        System.out.println("true");
        return true;
    }

    // moved the image of the car depending on what key you press
    public void move(KeyEvent event) {
        //switch case to get what input the user typed and translate it to a direction
        //makes sure to also set the direction which we will use to make sure the car is in bounds
        switch (event.getCode()) {
            case W:
                rotate("up");
                if (validpath()) {
                    imageView.setLayoutY(imageView.getLayoutY() - speed);
                }
                break;
            case S:
                rotate("down");
                if (validpath()) {
                    imageView.setLayoutY(imageView.getLayoutY() + speed);
                }
                break;
            case A:
                rotate("left");
                if (validpath()) {
                    imageView.setLayoutX(imageView.getLayoutX() - speed);
                }
                break;
            case D:
                rotate("right");
                if (validpath()) {
                    imageView.setLayoutX(imageView.getLayoutX() + speed);
                }
                break;
        }
    }
}