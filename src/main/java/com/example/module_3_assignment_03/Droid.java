package com.example.module_3_assignment_03;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Droid {
    ImageView imageView;
    double speed;
    boolean in_use;
    String direction;
    ImageView Maze;

    public Droid(ImageView imageView, int speed,boolean in_use, ImageView Maze) {
        this.imageView = imageView;
        this.speed = speed;
        this.direction = "right";
        this.in_use = in_use;
        this.Maze = Maze;
    }

    public void setIn_Use(boolean in_use) {
        this.in_use = in_use;
    }

    public boolean getIn_Use() {
        return in_use;
    }


    public boolean validpath() {
        PixelReader pixelReader = Maze.getImage().getPixelReader();

        // Get maze image dimensions
        double imageWidth = Maze.getImage().getWidth();
        double imageHeight = Maze.getImage().getHeight();

        // Get ImageView displayed dimensions
        double imageViewWidth = Maze.getFitWidth();
        double imageViewHeight = Maze.getFitHeight();

        // Compute scale factors
        double scaleX = imageWidth / imageViewWidth;
        double scaleY = imageHeight / imageViewHeight;

        double relativeX = imageView.getLayoutX() - Maze.getLayoutX();
        double relativeY = imageView.getLayoutY() - Maze.getLayoutY();

        int x = (int) ((relativeX + 55) * scaleX);
        int y = (int) ((relativeY + 10) * scaleY);
        switch (direction) {
            case "right":
                x += (int) (17 * scaleX);
                break;
            case "left":
                x -= (int) (17 * scaleX);
                break;
            case "up":
                y -= (int) (6 * scaleY);
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