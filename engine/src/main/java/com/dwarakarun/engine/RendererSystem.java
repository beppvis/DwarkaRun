package com.dwarakarun.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.nio.file.*;
import java.util.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.stb.STBImage.*;


public class RendererSystem extends GameSystem {
	public RendererSystem(Engine eng) {
		super(eng);
		deps = new Class[] {WindowSystem.class};
		System.out.println("RS Constructor done");
	}

	SpriteComponent spriteComponent;
	WindowSystem windowSystem;

	@Override
	public void init() {
		System.out.println("RS init");

		glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
		glLoadIdentity();

		glEnable(GL_TEXTURE_2D);

		//TODO: Fetch and render AnimatedSprite as well once done
		spriteComponent = eng.getComponent(SpriteComponent.class);
		windowSystem = eng.getSystem(WindowSystem.class);
	}

	private void render(Sprite sp) {
		sp.bindTexture();
		glPushMatrix();
		//TODO: Replace with x and y coords
		glTranslatef(100f, 200, 0);

		glBegin(GL_QUADS);

		int width = sp.getWidth();
		int height = sp.getHeight();

		glTexCoord2f(sp.getTexcoord(0, 0), 1-sp.getTexcoord(0, 1));
		glVertex2f(0, 0);

		glTexCoord2f(sp.getTexcoord(0, 0), 1-sp.getTexcoord(1, 1));
		glVertex2f(0, height);

		glTexCoord2f(sp.getTexcoord(1, 0), 1-sp.getTexcoord(1, 1));
		glVertex2f(width, height);

		glTexCoord2f(sp.getTexcoord(1, 0), 1-sp.getTexcoord(0, 1));
		glVertex2f(width, 0);

		glEnd();

		glPopMatrix();
	}

	@Override 
	public void update() {
		glPushMatrix();

		glScalef(2f/windowSystem.getWidth(), -2f/windowSystem.getHeight(), 1.0f);
		glTranslatef(-windowSystem.getWidth()/2f, -windowSystem.getHeight()/2f, 0);

		Iterator iter = spriteComponent.iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			String name = (String)entry.getKey();
			Sprite sprite = (Sprite)entry.getValue();
			render(sprite);
		}
		glPopMatrix();
	}
}
