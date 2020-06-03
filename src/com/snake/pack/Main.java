package com.snake.pack;

import javax.swing.JFrame;

public class Main {
	
	public Main() {
		JFrame frame = new JFrame();
		
		GamePanel gamepanel = new GamePanel();
		
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SNAKE");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new Main();
	}
}
