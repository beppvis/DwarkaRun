package dwaraka;

import engine.*;

public class Game {
	public static void main(String args[]) {
		System.out.println("Hello!!!");
		Engine e = new Engine();
		DummyComponent dc = new DummyComponent();
		e.addComponent("DummyComponent", dc);
		e.addComponent("Dummy2Component", new DummyComponent());

		e.init();

		e.getComponent("DummyComponent").set("a", 10);
		e.getComponent("Dummy2Component").set("a", 20);
		e.addSystem("DummySystem", new DummySystem(e));

		System.out.println(e.getComponent("DummyComponent").get("a"));
		System.out.println(e.getComponent("Dummy2Component").get("a"));

		e.run();
	}
}
