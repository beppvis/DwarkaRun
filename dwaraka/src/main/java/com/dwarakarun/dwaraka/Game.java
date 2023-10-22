package com.dwarakarun.dwaraka;

import com.dwarakarun.engine.*;

public class Game {
	public static void main(String args[]) {
		Engine e = new Engine();

		e.addSystem("DummySystem", new DummySystem(e));
		e.addSystem("WindowSystem", new WindowSystem(e));

		e.init();

		e.run();
	}
}
