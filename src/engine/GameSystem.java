package engine;

import java.lang.*;

public class GameSystem {
	protected engine.Engine eng;
	public GameSystem(engine.Engine eng) {
		this.eng = eng;
	}
	public void init() {
		System.out.println("Base System Init!");
	}
	public void update() {
		System.out.println("Base System Update!");
	}
}
