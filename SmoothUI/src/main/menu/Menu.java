package main.menu;

import java.awt.Graphics;
import java.util.ArrayList;

import main.resos.ResolutionInfo;
import main.smoothui.SmoothUI;
import main.uielement.UIElement;
import main.uielement.animation.Animation;
import main.uielement.button.Button;
import main.uielement.type.Type;

public abstract class Menu {
	
	public ArrayList<UIElement> ui = new ArrayList<UIElement>();
	
	private boolean selected = false;
	public int ID;
	
	protected double scaleW, scaleH;
	
	protected abstract Animation[] initAnims(double x, double y, double w, double h, int fillOpacity, int borderOpacity);
	
	public Menu(boolean selected, int ID) {
		this.selected = selected;
		this.ID = ID;
		getScale();
		generateUI();
	}
	
	public void tick() {
		for (UIElement uie : ui) {
			uie.tick();
		}
	}
	
	public abstract void generateUI();
	
	public void getScale() {
		double wwidth = SmoothUI.getsWidth();
		double wheight = SmoothUI.getsHeight();
		scaleW = wwidth / ResolutionInfo.currMonitor.getLargestRes().getWidth();
		scaleH = wheight / ResolutionInfo.currMonitor.getLargestRes().getHeight();
	}
	
	int count = 0;
	
	protected void addUIObject(double xx, double yy, double width, double height, String s, Type t, boolean horizInc, boolean vertInc, boolean horizDec, boolean vertDec, Animation[] hoverAnims) {
		double wwidth = SmoothUI.getsWidth();
		double wheight = SmoothUI.getsHeight();
		
		scaleW = wwidth / ResolutionInfo.currMonitor.getLargestRes().getWidth();
		scaleH = wheight / ResolutionInfo.currMonitor.getLargestRes().getHeight();
		int x = (int) (xx * scaleW);
		int y = (int) (yy * scaleH);
		int w = (int) (width * scaleW);
		int h = (int) (height * scaleH);
		
		
		if (horizInc || vertInc || horizDec || vertDec) {
			count++;
		} else {
			count = 0;
		}
		
		if (horizInc) {
			if (vertInc) {
				addUIObj(x + (w * count) + ((w / 3) * count), y + (h * count) + ((h / 5) * count), w, h, s, t, hoverAnims);
			} else if (vertDec) {
				addUIObj(x + (w * count) + ((w / 3) * count), y - (h * count) - ((h / 5) * count), w, h, s, t, hoverAnims);
			} else {
				addUIObj(x + (w * count) + ((w / 3) * count), y, w, h, s, t, hoverAnims);
			}
		} else if (horizDec) {
			if (vertInc) {
				addUIObj(x - (w * count) - ((w / 3) * count), y + (h * count) + ((h / 5) * count), w, h, s, t, hoverAnims);
			} else if (vertDec) {
				addUIObj(x - (w * count) - ((w / 3) * count), y - (h * count) - ((h / 5) * count), w, h, s, t, hoverAnims);
			} else {
				addUIObj(x - (w * count) - ((w / 3) * count), y, w, h, s, t, hoverAnims);
			}
		} else {
			if (vertInc) {
				addUIObj(x, y + (h * count) + ((h / 5) * count), w, h, s, t, hoverAnims);
			} else if (vertDec) {
				addUIObj(x, y - (h * count) - ((h / 5) * count), w, h, s, t, hoverAnims);
			} else {
				addUIObj(x, y, w, h, s, t, hoverAnims);
			}
		}
	}
	
	private void addUIObj(double x, double y, double width, double height, String s, Type t, Animation[] hoverAnims) {
		if (t == Type.button) {
			ui.add(new Button(x, y, width, height, ui.size(), s, hoverAnims));
		} 
	}
	
	public void render(Graphics g) {
		for (UIElement uie : ui) {
			uie.render(g);
		}
	}

	public void mouseMoved(int mx, int my) {
		for (UIElement obj : ui) {
			if (mouseOver(mx, my, obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				if (!obj.isSelected()) {
					obj.setHovered(true);
				}
			} else {
				obj.setHovered(false);
			}
		}
	}
	
	protected double getScaleX(double xx) {
		double wwidth = SmoothUI.getsWidth();
		scaleW = wwidth / ResolutionInfo.currMonitor.getLargestRes().getWidth();
		return (xx * scaleW);
	}
	
	protected double getScaleY(double yy) {
		double wheight = SmoothUI.getsHeight();
		scaleH = wheight / ResolutionInfo.currMonitor.getLargestRes().getHeight();
		return (yy * scaleH);
	}
	
	protected double getScaleW(double width) {
		double wwidth = SmoothUI.getsWidth();
		scaleW = wwidth / ResolutionInfo.currMonitor.getLargestRes().getWidth();
		return (width * scaleW);
	}
	
	protected double getScaleH(double height) {
		double wheight = SmoothUI.getsHeight();
		scaleH = wheight / ResolutionInfo.currMonitor.getLargestRes().getHeight();
		return (height * scaleH);
	}
	
	public void mouseClicked(int mx, int my, int click) {
		for (UIElement obj : ui) {
			if (mouseOver(mx, my, obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				obj.setSelected(!obj.isSelected());
				if (obj.isSelected()) {
					click(obj.getID(), click);
				}
				if (obj.isSelected()) {
					obj.setHovered(false);
				}
			} else {
				obj.setSelected(false);
			}
		}
	}
	
	public void scaleUI() {
		ArrayList<UIElement> rtm = new ArrayList<UIElement>();
		
		for (int i = 0; i < ui.size(); i++) {
			rtm.add(ui.get(i));
		}
		
		for (int i = 0; i < rtm.size(); i++) {
			ui.remove(rtm.get(i));
		}
		
		count = 0;
		generateUI();
	}
	
	protected abstract void click(int id, int mc);
	
	protected UIElement getUIObj(int id) {
		return ui.get(id);
	}
	
	protected boolean mouseOver(int mx, int my, double x, double y, double width, double height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
