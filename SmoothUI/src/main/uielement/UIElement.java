package main.uielement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.uielement.animation.AnimType;
import main.uielement.animation.Animation;
import main.uielement.type.Type;

public abstract class UIElement {
	
	protected double x, y, rx, ry, width, height, rw, rh;
	protected boolean selected, hovered, fill, border;
	protected String string;
	
	protected Color defC, hovC, selC, fillDC, fillHC, fillSC;
	protected Type uiType;
	
	protected Animation[] hoverAnimations = new Animation[6];
	protected int fillOpacity = 35, borderOpacity = 125;
	
	protected int ID;
	
	public UIElement (double x, double y, double width, double height, Type uiType) {
		this.x = x;
		this.y = y;
		this.rx = x;
		this.ry = y;
		this.width = width;
		this.height = height;
		this.rw = width;
		this.rh = height;
		this.uiType = uiType;
		
		defC = new Color(255, 255, 255, borderOpacity);
		hovC = new Color(0, 255, 0, borderOpacity);
		selC = new Color(0, 0, 255, borderOpacity);
		
		fillDC = new Color(255, 255, 255, fillOpacity);
		fillHC = new Color(0, 255, 0, fillOpacity);
		fillSC = new Color(0, 0, 255, fillOpacity);
		
		fill = false;
		border = true;
	}
	
	/**
	 * Creates a UI Element with Menus
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param uiType
	 * @param ID
	 * @param string
	 * @param hoverAnimations
	 */
	public UIElement (double x, double y, double width, double height, Type uiType, int ID, String string, Animation[] hoverAnimations) {
		this.x = x;
		this.y = y;
		this.rx = x;
		this.ry = y;
		this.width = width;
		this.height = height;
		this.rw = width;
		this.rh = height;
		this.uiType = uiType;
		this.string = string;
		this.ID = ID;
		this.hoverAnimations = hoverAnimations;
		
		defC = new Color(255, 255, 255, borderOpacity);
		hovC = new Color(0, 255, 0, borderOpacity);
		selC = new Color(0, 0, 255, borderOpacity);
		
		fillDC = new Color(255, 255, 255, fillOpacity);
		fillHC = new Color(0, 255, 0, fillOpacity);
		fillSC = new Color(0, 0, 255, fillOpacity);
		
		fill = false;
		border = true;
	}
	
	public UIElement (double x, double y, double width, double height, Type uiType, int ID) {
		this.x = x;
		this.y = y;
		this.rx = x;
		this.ry = y;
		this.width = width;
		this.height = height;
		this.rw = width;
		this.rh = height;
		this.uiType = uiType;
		
		defC = new Color(255, 255, 255, borderOpacity);
		hovC = new Color(0, 255, 0, borderOpacity);
		selC = new Color(0, 0, 255, borderOpacity);
		
		fillDC = new Color(255, 255, 255, fillOpacity);
		fillHC = new Color(0, 255, 0, fillOpacity);
		fillSC = new Color(0, 0, 255, fillOpacity);
		
		fill = false;
		border = true;
		
		this.ID = ID;
	}


	public void tick() {
		for (Animation a : hoverAnimations) {
			a.tick();
			if (a.getAnimType() == AnimType.fadeFill) {
				fillOpacity = (int) (a.getPlaceVar());
				setFillHC(new Color(getFillHC().getRed(), getFillHC().getGreen(), getFillHC().getBlue(), fillOpacity));
				setFillDC(new Color(getFillHC().getRed(), getFillHC().getGreen(), getFillHC().getBlue(), fillOpacity));
			} else if (a.getAnimType() == AnimType.fadeBorder) {
				borderOpacity = (int) (a.getPlaceVar());
				setHovC(new Color(getHovC().getRed(), getHovC().getGreen(), getHovC().getBlue(), borderOpacity));
				setDefC(new Color(getDefC().getRed(), getDefC().getGreen(), getDefC().getBlue(), borderOpacity));			
			} else if (a.getAnimType() == AnimType.slideHoriz) {
				rx = a.getPlaceVar();
			} else if (a.getAnimType() == AnimType.slideVert) {
				ry = a.getPlaceVar();
			} else if (a.getAnimType() == AnimType.growHoriz) {
				rw = a.getPlaceVar();
			} else if (a.getAnimType() == AnimType.growVert) {
				rh = a.getPlaceVar();
			}
		}
		
	}
	
	public abstract void render(Graphics g);

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
		for (Animation a : hoverAnimations) {
			a.setActivated(hovered);
		}
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Color getDefC() {
		return defC;
	}

	public void setDefC(Color defC) {
		this.defC = defC;
	}

	public Color getHovC() {
		return hovC;
	}

	public void setHovC(Color hovC) {
		this.hovC = hovC;
	}

	public Color getSelC() {
		return selC;
	}

	public void setSelC(Color selC) {
		this.selC = selC;
	}

	public Color getFillDC() {
		return fillDC;
	}

	public void setFillDC(Color fillDC) {
		this.fillDC = fillDC;
	}

	public Color getFillHC() {
		return fillHC;
	}

	public void setFillHC(Color fillHC) {
		this.fillHC = fillHC;
	}

	public Color getFillSC() {
		return fillSC;
	}

	public void setFillSC(Color fillSC) {
		this.fillSC = fillSC;
	}

	public Type getUiType() {
		return uiType;
	}

	public void setUiType(Type uiType) {
		this.uiType = uiType;
	}
	
	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public double getRw() {
		return rw;
	}

	public void setRw(double rw) {
		this.rw = rw;
	}

	public double getRh() {
		return rh;
	}

	public void setRh(double rh) {
		this.rh = rh;
	}

	public int getFillOpacity() {
		return fillOpacity;
	}

	public void setFillOpacity(int fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	public int getBorderOpacity() {
		return borderOpacity;
	}

	public void setBorderOpacity(int borderOpacity) {
		this.borderOpacity = borderOpacity;
	}

	public String toString() {
		return "X: " + this.getX() + " | Y: " + this.getY() + " | WIDTH: " + this.getWidth() + " | HEIGHT: " + this.getHeight() + " | String: " + this.getString();
	}
}
