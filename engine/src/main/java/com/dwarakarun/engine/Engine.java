package com.dwarakarun.engine;

import java.util.*;

public class Engine {
	private static int instanceCount = 0;

	private Deque<String> depsort;

	private Boolean shouldEnd;

	private HashMap<String, GameSystem> systems;
	private HashMap<String, Component> components;

	public Engine() {
		if (instanceCount >= 1) {
			throw new Error("ERROR: Attempting to launch multiple Engine instances");
		}
		instanceCount = 1;

		systems = new HashMap<String, GameSystem>();
		components = new HashMap<String, Component>();
		shouldEnd = false;
	}

	private void insertWithDeps(String sys, String[] deps, Set<String> visited) {
		if (visited.contains(sys)) {
			return;
		}
		for (String dep: deps) {
			if (!visited.contains(dep)) {
				insertWithDeps(dep, getSystem(dep).getDependencies(), visited);
				visited.add(dep);
			}
		}
		depsort.push(sys);
		visited.add(sys);
	}

	public void init() {
		depsort = new ArrayDeque<String>(systems.size());
		Set<String> visited = new HashSet<String>();

		Iterator<HashMap.Entry<String, GameSystem>> sys= systems.entrySet().iterator();
		while (sys.hasNext() && visited.size() != systems.size()) {
			HashMap.Entry<String, GameSystem> s = sys.next();
			insertWithDeps(s.getKey(), s.getValue().getDependencies(), visited);
		}

		for (String s : depsort) {
			getSystem(s).init();
		}
	}

	public void end() {
		shouldEnd = true;
	}

	private void deinit() {
		Iterator<String> sysNames = depsort.descendingIterator();
		while (sysNames.hasNext()) {
			getSystem(sysNames.next()).end();
		}
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
		WindowSystem ws = (WindowSystem)getSystem("WindowSystem");
		while (!shouldEnd) {
			ws.clear();
			Iterator<HashMap.Entry<String, GameSystem>> sys= systems.entrySet().iterator();
			while (sys.hasNext()) {
				sys.next().getValue().update();
			}
			ws.swapBuffers();
			ws.pollEvents();
		}
		deinit();
	}

}
