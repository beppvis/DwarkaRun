package engine;

import java.util.*;

public class Engine {
	private static int instanceCount = 0;

	HashMap<String, GameSystem> systems;
	HashMap<String, Component> components;

	public Engine() {
		if (instanceCount >= 1) {
			throw new Error("ERROR: Attempting to launch multiple Engine instances");
		}
		instanceCount = 1;

		systems = new HashMap<String, GameSystem>();
		components = new HashMap<String, Component>();
	}

	public void init() {
		Iterator<HashMap.Entry<String, GameSystem>> sys= systems.entrySet().iterator();
		while (sys.hasNext()) {
			sys.next().getValue().init();
		}
	}

	public void end(int ret) {
		System.exit(ret);
	}

	public void addSystem(String name, GameSystem sys) {
		systems.put(name, sys);
	}

	public GameSystem getSystem(String name) {
		return systems.get(name);
	}

	public <T> void addComponent (String name, Component<T> c) {
		components.put(name, c);
	}

	public Component getComponent(String name) {
		return components.get(name);
	}

	public void run() {
		while (true) {
			Iterator<HashMap.Entry<String, GameSystem>> sys= systems.entrySet().iterator();
			while (sys.hasNext()) {
				sys.next().getValue().update();
			}
		}
	}

}
