package starter.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;

import main.smoothui.SmoothUI;
import starter.Starter;


public class Mouse extends MouseAdapter implements MouseWheelListener {

	// Declaring
	public int mouseX = 0, mouseY = 0;
	public boolean canClick = false, canScroll = false, fgrab = false;
	public static int mouseClick = 0;
	
	private Point md = MouseInfo.getPointerInfo().getLocation();
	
	public long tick = 0;
	
	private Starter j;
	
	
	public Mouse(Starter j) {
		this.j = j;
		j.w.addMouseListener(this);
		j.w.addMouseWheelListener(this);
		j.w.addMouseMotionListener(this);
		tick = System.currentTimeMillis();
	}
	
	public void tick() {
		// Mouse Cursor
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		Point c = j.w.getLocationOnScreen();
		mouseX = (int) b.getX() - (int) c.getX();
		mouseY = (int) b.getY() - (int) c.getY();
		// Mouse Cursor End
	
		if (!canClick) {
			if (System.currentTimeMillis() - tick >= 125) {
				canClick = true;
			}
		} else if (!canScroll) {
			if (System.currentTimeMillis() - tick >= 125) {
				canScroll = true;
			}
		}
		
		j.sui.mouseMoved(mouseX, mouseY);
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		int click = e.getButton(); // 1 = Left | 2 = Middle | 3 = Right
		mouseClick = click;
		if (canClick) {
			j.sui.mouseClicked(mx, my, click);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (fgrab) {
			fgrab = false;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
	}
	
	public void resetClick() {
		canClick = false;
		tick = System.currentTimeMillis();
	}
}
