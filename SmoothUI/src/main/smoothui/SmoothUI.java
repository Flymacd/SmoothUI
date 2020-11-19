package main.smoothui;

import java.awt.Graphics;
import java.util.ArrayList;

import main.menu.Menu;
import main.resos.Resolution;

public class SmoothUI {

	public ArrayList<Menu> menus = new ArrayList<Menu>();
	
	private static double sWidth, sHeight;
	
	public static Resolution r;
	
	public SmoothUI (double width, double height) {
		sWidth = width;
		sHeight = height;
		
	}
	
	public void addMenu(Menu m) {
		menus.add(m);
	}
	
	public void removeMenu(Menu m) {
		menus.remove(m);
	}
	
	public void removeMenu(int id) {
		menus.remove(id);
	}
	
	
	public void tick() {
		for (Menu m : menus) {
			if (m.isSelected()) {
				m.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Menu m : menus) {
			if (m.isSelected()) {
				m.render(g);
			}
		}
	}
	
	public void mouseClicked(int mx, int my, int click) {
		for (Menu m : menus) {
			m.mouseClicked(mx, my, click);
		}
	}
	
	public void mouseMoved(int mx, int my) {
		for (Menu m : menus) {
			m.mouseMoved(mx, my);
		}
	}

	public static double getsWidth() {
		return sWidth;
	}

	public static void setsWidth(double sWidth) {
		SmoothUI.sWidth = sWidth;
	}

	public static double getsHeight() {
		return sHeight;
	}

	public static void setsHeight(double sHeight) {
		SmoothUI.sHeight = sHeight;
	}

	public void scaleUIMenus() {
		for (Menu m : menus) {
			m.scaleUI();
		}
	}
	
}
