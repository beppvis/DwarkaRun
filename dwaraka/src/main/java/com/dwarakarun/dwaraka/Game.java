package com.dwarakarun.dwaraka;

import com.dwarakarun.engine.*;

public class Game {
	public Game() {
	}
	public static void main(String args[]) {
		Engine e = new Engine();

		SpriteComponent sc = new SpriteComponent();
		e.addComponent(sc);

		e.addSystem(new WindowSystem(e));
		e.addSystem(new RendererSystem(e));

		e.init();

		String spritePath = "../assets/sprite.png";
		Sprite s = new Sprite(spritePath);
		s.load();
		sc.set("testSprite", s);
		s.scale(0.5f);

		System.out.println("Done engine init");


		e.run();
	}
}
