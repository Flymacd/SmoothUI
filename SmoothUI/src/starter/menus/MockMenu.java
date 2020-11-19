package starter.menus;

import java.awt.Color;

import main.menu.Menu;
import main.resos.ResolutionInfo;
import main.uielement.animation.AnimType;
import main.uielement.animation.Animation;
import main.uielement.type.Type;
import starter.Starter;

public class MockMenu extends Menu {

	
	public MockMenu(boolean selected, int ID) {
		super(selected, ID);
	}

	public void generateUI() {
		double y = ResolutionInfo.currMonitor.getLargestRes().getHeight() - 1;
		ResolutionInfo.updateSelectResoText();
		addUIObject(5, y, 225, 75, "Exit", Type.button, false, false, false, true, initAnims(5, y, 225, 75, 35, 125));
		addUIObject(5, y, 225, 75, ResolutionInfo.selectResoText, Type.button, false, false, false, true, initAnims(5, y, 225, 75, 35, 125));
		addUIObject(5, y, 225, 75, "Apply", Type.button, false, false, false, true, initAnims(5, y, 225, 75, 35, 125));
		for (int i = 0; i < ui.size(); i++) {
			getUIObj(i).setDefC(Color.white);
			getUIObj(i).setFillSC(Color.white);
			getUIObj(i).setHovC(Color.white);
			getUIObj(i).setFillHC(Color.white);
			getUIObj(i).setFill(true);
		}
	}
	
	protected Animation[] initAnims(double xx, double yy, double width, double height, int fillOpacity, int borderOpacity) {
		Animation[] hoverAnims = new Animation[6];
		double x = getScaleX(xx);
		double w = getScaleW(width);
		double h = getScaleH(height);
		double wg = getScaleW(50);
		double hg = getScaleH(50);
		hoverAnims[0] = (new Animation(w, w + wg, 0.25, AnimType.growHoriz));
		hoverAnims[1] = (new Animation(AnimType.none));
		hoverAnims[2] = (new Animation(fillOpacity, fillOpacity + 40, 0.25, AnimType.fadeFill));
		hoverAnims[3] = (new Animation(borderOpacity, borderOpacity + 40, 0.25, AnimType.fadeBorder));
		hoverAnims[4] = (new Animation(AnimType.none));
		hoverAnims[5] = (new Animation(AnimType.none));
		return hoverAnims;
	}

	protected void click(int id, int mc) {
		switch (id) {
			case 0:
				System.exit(0);
				break;
				
			case 1:
				ResolutionInfo.changeResolution(mc);
				getUIObj(id).setString(ResolutionInfo.selectResoText);
				getUIObj(id).setSelected(false);
				break;
				
			case 2:
				Starter.starter.applyResolution(this);
				getUIObj(id).setSelected(false);
				break;
		}
	}

}
