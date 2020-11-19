package main.uielement.button;

import java.awt.Graphics;

import main.gfx.DrawUI;
import main.uielement.UIElement;
import main.uielement.animation.Animation;
import main.uielement.type.Type;

public class Button extends UIElement {

	public Button(double x, double y, double width, double height, int ID, String string, Animation[] hoverAnims) {
		super(x, y, width, height, Type.button, ID, string, hoverAnims);
	}

	public void render(Graphics g) {
		DrawUI.drawButton(this, g);
	}

}
