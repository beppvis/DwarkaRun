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
    e.addSystem(new AnimatedSpriteSystem(e));

		e.init();

		String spritePath = "../assets/sprite.png";
    String spritePath1 = "../assets/shinobi/Shinobi/Attack_2.png";
    String spritePath2 = "../assets/vampire/Vampire_Girl/Attack_4.png";
    String spritePath3 = "../assets/vampire/Countess_Vampire/Attack_2.png";
    String spritePath4 = "../assets/shinobi/Samurai/Attack_2.png";
		Sprite s = new Sprite(spritePath2);
		s.load();
		sc.set("testSprite", s);
		s.scale(1f);

		System.out.println("Done engine init");

//SpriteDetails sd = new SpriteDetails(spritePath1);
//    sd.getSpriteTime();
		e.run(30);
	}
}
