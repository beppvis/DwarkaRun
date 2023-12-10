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
    String spritePath1 = "../assets/shinobi/Shinobi/Attack_2.png";
    String spritePath2 = "../assets/vampire/Vampire_Girl/Attack_4.png";
    String spritePath3 = "../assets/vampire/Countess_Vampire/Run.png";
    String spritePath4 = "../assets/shinobi/Samurai/Attack_2.png";
    String spritePath5 = "../assets/shinobi/Shinobi/Run.png";

    String background = "../assets/background.jpg";


		Sprite s = new Sprite(spritePath5);
    Sprite s2 = new Sprite(spritePath3);
    Sprite s3 = new Sprite(background);

		//s.load();
    //s2.load();
    s3.load();

		//sc.set("shinobiSprite", s);
    //sc.set("vampSprite", s2);
    sc.set("background",s3);

		//s.scale(1f);
    //s2.scale(2f);
    s3.scale(2.8f);

		System.out.println("Done engine init");

//SpriteDetails sd = new SpriteDetails(spritePath1);
//    sd.getSpriteTime();

    AnimatorSystem as = new AnimatorSystem(); 
    
    e.run(as.getSpriteTime("shinobiSprite"));

  }
}

