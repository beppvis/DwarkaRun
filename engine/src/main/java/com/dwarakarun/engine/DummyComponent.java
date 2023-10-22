
package com.dwarakarun.engine;

public class DummyComponent extends Component<Integer> {
	public void init() {
		System.out.println("Initing");
	}
	public void update() {
		System.out.println("Updating");
	}
}
