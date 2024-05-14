package com.example.fx_canvas_demo;

public class Rectangle {
    int posX;
    int posY;
    int width = 25;


    public Rectangle(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void incrementX(){
        posX++;
    }
    public void incrementY(){
        posY++;
    }

    public void decrementX(){
        posX--;
    }

    public void decrementY(){
        posY--;
    }

    public int getWidth() {
        return width;
    }
}
