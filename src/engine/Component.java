package engine;

import java.util.HashMap;

public class Component<T> {
	private HashMap<String, T> data;

	public Component() {
		data = new HashMap<String, T>();
	}

	public T get(String entityName) {
		return data.get(entityName);
	}

	public void set(String entityName, T comp) {
		data.put(entityName, comp);
	}
}
