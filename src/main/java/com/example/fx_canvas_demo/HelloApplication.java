package com.example.fx_canvas_demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    static int fps = 1000;
    static int screen_width = 600;
    static int screen_height = 600;
    static Direction direction = Direction.down;
    static boolean stop = false;

    Rectangle rectangle = new Rectangle(rand.nextInt(screen_width), rand.nextInt(screen_height));
    static Random rand = new Random();

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        Canvas c = new Canvas(screen_width, screen_height);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);
        Scene scene = new Scene(root, screen_width, screen_height);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            onKeyPressed(key.getCode());
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
                case SHIFT -> {
                    System.out.println("SHIFT RELEASED");
                    fps = 30;
                }
            }
        });

        stage.setTitle("DEMO!");
        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {

                if (now - lastTick > 100000000l / fps*10 ) {
                    lastTick = now;
                    tick(gc);
                }
            }
        }.start();

    }

    private void onKeyPressed(KeyCode key) {
        stop = false;
        switch (key) {
            case W -> {
                System.out.println("W");
                direction = Direction.up;
            }
            case A -> {
                System.out.println("A");
                direction = Direction.left;
            }
            case S -> {
                System.out.println("S");
                direction = Direction.down;
            }
            case D -> {
                System.out.println("D");
                direction = Direction.right;
            }
            case SHIFT -> {
                System.out.println("SHIFT");
                fps = 100;
            }

        }
    }

    private void tick(GraphicsContext graphicsContext) {
        if (stop) {
            //System.out.println("stop");
            return;
        }
        clear_screen(graphicsContext);

        switch (direction) {
            case up:
                stop = rectangle.getPosY() < 0;
                if (!stop) rectangle.decrementY();
                break;
            case down:
                stop = rectangle.getPosY() > screen_height - rectangle.getWidth();
                if (!stop) rectangle.incrementY();
                break;
            case left:
                stop = rectangle.getPosX() < 0;
                if (!stop) rectangle.decrementX();
                break;
            case right:
                stop = rectangle.getPosX() > screen_width - rectangle.getWidth();
                if (!stop) rectangle.incrementX();
                break;
        }

        graphicsContext.setFill(Color.LIGHTGREEN);
        for (int i = 0; i < 25; i++) {
            graphicsContext.fillRect(rectangle.posX, rectangle.posY, rectangle.getWidth(), rectangle.getWidth());
        }


    }

    private void clear_screen(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, screen_width, screen_height);
    }

    public static void main(String[] args) {
        launch();
    }
}