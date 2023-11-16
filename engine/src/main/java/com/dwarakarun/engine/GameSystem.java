package com.dwarakarun.engine;

import java.lang.*;

public class GameSystem {
	protected Engine eng;
	protected Class[] deps;
	public GameSystem(Engine eng) {
		this.eng = eng;
	}
	public void init() {
		System.out.println("Base System Init!");
	}
	public void update() {
		System.out.println("Base System Update!");
	}
	public final Class[] getDependencies() {
		return deps;
	}
	public void end() {
		System.out.println("Base System End!");
	}
}
