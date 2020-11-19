package main.gfx;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import main.smoothui.SmoothUI;
import main.uielement.UIElement;
import main.uielement.button.Button;
import starter.Starter;


public class DrawUI {

	public static void drawButton(Button b, Graphics g) {
		if (b.isHovered()) {
			g.setColor(b.getHovC());
		} else if (b.isSelected()) {
			g.setColor(b.getSelC());
		} else {
			g.setColor(b.getDefC());
		}
		
		g.drawRect((int) b.getRx(), (int) b.getRy(), (int) b.getRw(), (int) b.getRh());
		
		if (!b.getString().trim().equalsIgnoreCase("")) {
			drawCenteredString(b, g);
		}
		
		if (b.isFill()) {
			if (b.isHovered()) {
				g.setColor(b.getFillHC());
			} else if (b.isSelected()) {
				g.setColor(b.getFillSC());
			} else {
				g.setColor(b.getFillDC());
			}
			g.fillRect((int) b.getRx(), (int) b.getRy(), (int) b.getRw(), (int) b.getRh());
		}
		
	}
	
//	public void drawRadialButton(UIElement b, Graphics g) {
//		RadialButton r = (RadialButton) b;
//		
//		g.drawOval(r.getX() - (r.getRadius() / 10), r.getY() - (r.getRadius() / 10), r.getRadius() * 2 + ((r.getRadius() / 10) * 2), r.getRadius() * 2 + ((r.getRadius() / 10) * 2));
//		
//		if (r.isSelected()) {
//			g.fillOval(r.getX(), r.getY(), r.getRadius() * 2, r.getRadius() * 2);
//		}
//	}
//	
	public static void drawCenteredString(UIElement b, Graphics g) {
		Starter.fnt = new Font("Serif", 1, (int) (SmoothUI.getsWidth() / 56));
		FontMetrics fm = g.getFontMetrics();
	    int x = (int) (b.getRx() + (b.getRw() - fm.stringWidth(b.getString())) / 2 - 1);
	    int y = (int) (b.getRy() + (fm.getAscent() + (b.getRh() - (fm.getAscent() + fm.getDescent())) / 2) - 1);
	    g.drawString(b.getString(), x, y);
	}
	
//	public void drawTitle(String s, int y, Color titleColor, Graphics g) {
//		// TEXT SCALING
//		Font fntT = new Font(j.fnt.getFontName(), 1, j.getWidth() / 20);
//
//		g.setFont(fntT);
//		FontMetrics fm = g.getFontMetrics();
//		int x = (j.w.getWidth() - fm.stringWidth(s)) / 2;
//	    g.setColor(titleColor);
//	    g.drawString(s, x, y);
//	    g.setFont(j.fnt);
//	}
	
}
