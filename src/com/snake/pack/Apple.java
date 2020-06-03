package com.snake.pack;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	
public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

private int xCoor, yCoor, width, height;
	
	public Apple(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xCoor * width, yCoor * width, width, height);
		//g.fillRect(xCoor * width, yCoor * width, width, height);
	}
}
