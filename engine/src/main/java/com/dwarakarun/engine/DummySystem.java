package com.dwarakarun.engine;

public class DummySystem extends GameSystem {
	public DummySystem(Engine e) {
		super(e);
		deps = new Class[]{WindowSystem.class};
	}
	public void init() {
		System.out.println("Initing gamesystem");
	}
	public void update() {}
}
