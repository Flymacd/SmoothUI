package main.window;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.resos.Resolution;
import main.resos.ResolutionInfo;
import starter.Starter;



public class Window extends Canvas {
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public Resolution r;
	private int id;
	private Starter j;
	public boolean borderless = false, fullscreen = false;
	
	public Window(Starter j, Resolution r, int id) {
		this.j = j;
		this.r = r;
		this.id = id;
		createFrame();
	}
	
	public void createFrame() {
		frame = new JFrame(j.FULL_NAME);
		
		setMinimumSize(new Dimension(r.getWidth(), r.getHeight()));
		setMaximumSize(new Dimension(r.getWidth(), r.getHeight()));
		setPreferredSize(new Dimension(r.getWidth(), r.getHeight()));
		
		if (borderless) {
			frame.setUndecorated(true);
		} else {
			frame.setUndecorated(false);
		}
		
		
		if (fullscreen) {
			frame.setState(JFrame.MAXIMIZED_BOTH);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);
		
		frame.requestFocus();
		Starter.frameExist = true;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "Window: " + getId() + " | Width: " + getWidth() + " | Height: " + getHeight(); 
	}

}
