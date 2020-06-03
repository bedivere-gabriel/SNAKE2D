package com.snake.pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	
	private boolean running = false;
	
	//
	private int SCORE = -1;
	private int SPEED = 0;
	private int speedvalue = 250000;
	
	// snake body part
	private Bodypart b;
	private ArrayList<Bodypart> snake;
	private int xCoor = 10, yCoor = 10, size = 5;
	
	private int ticks = 0;
	
	//apple
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	// snake controls
	public boolean right = true, left = false, up = false, down = false;
	
	
	public GamePanel() {
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
		snake = new ArrayList<Bodypart>();
		apples = new ArrayList<Apple>();
		
		r = new Random();
		start();
	}
	
	public void start() {
		this.requestFocus();
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();	
		} 
		
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void tick() {
		if (snake.size() == 0) {
			b = new Bodypart(xCoor, yCoor, 10);
			snake.add(b);
		}
		ticks++;
		
		if (ticks > speedvalue) {
			if (right) xCoor++;
			if (left) xCoor--;
			if (up) yCoor--;
			if (down) yCoor++;
			
			ticks = 0;
			
			b = new Bodypart(xCoor, yCoor, 10);
			snake.add(b);
			
			if (snake.size() > size) {
				snake.remove(0);
			}
			
			if (apples.size() == 0) {
				int xCoor = r.nextInt(49);
				int yCoor = r.nextInt(49);
				
				apple = new Apple(xCoor, yCoor, 10);
				apples.add(apple);
				SCORE++;
				SPEED++;
				speedvalue = speedvalue- 7000;
			}
			for (int i = 0; i < apples.size(); i++) {
				if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
					size++;
					apples.remove(i);
					i++;
				}
			}
			//collision from the snake
			for (int i = 0; i < snake.size(); i ++) {
				if (xCoor == snake.get(i).getxCoor() && yCoor ==snake.get(i).getyCoor()) {
					if (i != snake.size()-1) {
						System.out.println("Game Over");
						stop();
					}
				}
			}
			//collision from the border
			//if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {	
			//}
			if (xCoor < 0) {
				xCoor = 49;
			}
			if (xCoor > 49) {
				xCoor = 0;
			}
			if (yCoor < 0) {
				yCoor = 49;
			}
			if (yCoor > 49) {
				yCoor = 0;
			}
		}
		
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawString("SCORE : " + SCORE, 20, 20);
		g.drawString("SPEED : " + SPEED, 130, 20);
		
		g.setColor(Color.blue);
		g.drawString("BY J.G. PAGTALUNAN", 20, HEIGHT-20);
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < WIDTH/10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
			}
		for (int i = 0; i < HEIGHT; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		while (running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false; 
			down = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
