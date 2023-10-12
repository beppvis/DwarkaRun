package engine;

public class DummySystem extends GameSystem {
	public DummySystem(Engine e) {
		super(e);
	}
	public void init() {
		System.out.println("Initing gamesystem");
	}
	public void update() {
		System.out.println("Updating gamesystem");
	}
}
