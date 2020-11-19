package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.menu.Menu;
import main.resos.Resolution;
import main.resos.ResolutionInfo;
import main.smoothui.SmoothUI;
import main.window.Window;
import starter.input.Mouse;
import starter.menus.MockMenu;


public class Starter implements Runnable {

	public static int WIDTH = 1600, HEIGHT = 900;
	public static final String VERSION = "0.0.1";
	public static final String NAME = "SCHMEEUI";
	public static final String FULL_NAME =  NAME + " | " + "v" + VERSION;
	public static Starter game;
	
	
	public static Font fnt = new Font("verdana", 1, 25);
	public int fntSize = 25;
	
	public static Starter starter;
	
	public static boolean frameExist = false;
	public boolean decorated = true;
	
	public int fps = 0;
	
	// Multiplayer
//	public GameServer server;
//	public GameClient client;
	
	
	public boolean running = false;
	public Thread thread;
	
	// SCHMEEUI
	public SmoothUI sui;
	
	public Mouse m;
	
	public static Resolution currRes;
	
	public Window w;
	
	
	public static void main(String[] args) {
		starter = new Starter();
		starter.start();
	}
	
	public Starter() {
		// Initialize
		init();
		checkIfFirstRun();
		
		// for fun
//		int i = 1;
//		for (Resolution r : opt.resos) {
//			db.printLine(i + ": " + r);
//			i++;
//		}
		
		w = new Window(this, currRes, 0);
		
		initInputs();
	}
	
	private void initInputs() {
		m = new Mouse(this);
	}
	
	private void checkIfFirstRun() {
		//readObjects.loadFirstRun(System.getProperty("user.dir") + "/saves/fr.sav");
		
//		if (!firstRun) {
//			h.l.loadStartup();
//		}
	}
	
	public void init() {
		ResolutionInfo.initRes();
		sui = new SmoothUI(WIDTH, HEIGHT);
		sui.addMenu(new MockMenu(true, 0));
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double tps = 60D;
		double nsPerTick = 1000000000D/tps;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try { 
				Thread.sleep(2);;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				if (frames < 144) {
					frames++;
				}
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer > 1000) {
				w.frame.setTitle(FULL_NAME + " | " + ticks);
				lastTimer = System.currentTimeMillis();
				fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick() {
		if (frameExist) {
			m.tick();
			sui.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = w.getBufferStrategy();
		if (bs == null) {
			w.createBufferStrategy(3);
			return;
		}
		
//		// Text Scaling
//		if (WIDTH > 1300 && HEIGHT > 750) {
//			fnt = new Font("Serif", 1, 24);
//			fntSize = 24;
//		} else if (WIDTH < 750 && HEIGHT < 500) {
//			fntSize = 10;
//			fnt = new Font("Serif", 1, 10);
//		} else if (WIDTH < 900 && HEIGHT < 700) {
//			fntSize = 12;
//			fnt = new Font("Serif", 1, 12);
//		} else {
//			fntSize = 20;
//			fnt = new Font("Serif", 1, 20);
//		}
		
		if (frameExist) {
			Graphics g = bs.getDrawGraphics();
			g.setFont(fnt);
			g.setColor(Color.black);
			g.fillRect(0, 0, w.getWidth(), w.getHeight());
	
	//		g.setColor(Color.white);
	//		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
			sui.render(g);
			
			
	//		g.setColor(Color.LIGHT_GRAY);
	//		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
			
			g.dispose();
			bs.show();
		}
	}

	public boolean isDecorated() {
		return decorated;
	}

	public void setDecorated(boolean decorated) {
		this.decorated = decorated;
	}
	
	public void applyResolution(Menu m) {
		if (ResolutionInfo.selectRes == ResolutionInfo.currRes) {
			return;
		}
		
		ResolutionInfo.applyResolution();
		frameExist = false;
//		loading = true;
		m.setSelected(false);
		w.frame.dispose();
		currRes = ResolutionInfo.currRes;
		w.r = currRes; 
		if (currRes.getWidth() == ResolutionInfo.currMonitor.getLargestRes().getWidth() && currRes.getHeight() == ResolutionInfo.currMonitor.getLargestRes().getHeight()) {
			w.fullscreen = true;
			w.borderless = true;
		} else {
			w.fullscreen = false;
			w.borderless = false;
		}
		SmoothUI.setsWidth(currRes.getWidth());
		SmoothUI.setsHeight(currRes.getHeight());
		w.createFrame();
		sui.scaleUIMenus();
		m.setSelected(true);
//		loading = false;
	}

}
