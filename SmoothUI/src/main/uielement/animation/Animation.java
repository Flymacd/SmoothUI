package main.uielement.animation;

public class Animation {

	// Variable to increase/decrease from = var, var2 = Variable to increase/decrease to
	private double var, var2, placeVar, addVar;
	private boolean activated = false;
	private double animDelay;
	private AnimType animType;
	
	private long tickAnim;
	
	
	/**
	 * 
	 * @param Variable to Move From
	 * @param Variable to Move To
	 * @param Time to Move
	 * @param Animation Type
	 */
	public Animation(double var, double var2, double length, AnimType animType) {
		this.var = var;
		this.var2 = var2;
		this.placeVar = var;
		this.animType = animType;
		animDelay = ((length * 1000) / 60);
		addVar = ((var2 - var) / (60 * length));
		tickAnim = System.currentTimeMillis();
	}
	
	public Animation(AnimType animType) {
		this.animType = animType;
	}
	
	public void tick() {
		if (animType != AnimType.none) {
			if (placeVar != var || placeVar != var2) {
				if (System.currentTimeMillis() - tickAnim >= animDelay) {
					playAnim();
					tickAnim = System.currentTimeMillis();
				}
			}
		}
	}
	
	public void playAnim() {
		if (placeVar < var2) {
			if (activated) {
				if (placeVar + addVar < var2) {
					placeVar += addVar;
				}
			} else if (placeVar > var) {
				if (placeVar - addVar > var) {
					placeVar -= addVar;
				}
			}
		} else if (placeVar > var2) {
			if (activated) {
				if (placeVar + addVar > var2) {
					placeVar += addVar;
				}
			} else if (placeVar < var) {
				if (placeVar - addVar < var) {
					placeVar -= addVar;
				}
			}
		}
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public double getVar() {
		return var;
	}

	public void setVar(double var) {
		this.var = var;
	}

	public double getVar2() {
		return var2;
	}

	public void setVar2(double var2) {
		this.var2 = var2;
	}

	public double getPlaceVar() {
		return placeVar;
	}

	public void setPlaceVar(double placeVar) {
		this.placeVar = placeVar;
	}

	public AnimType getAnimType() {
		return animType;
	}

	public void setAnimType(AnimType animType) {
		this.animType = animType;
	}
	
}
