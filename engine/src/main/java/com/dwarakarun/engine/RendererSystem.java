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
  static int sprite_count;
	public RendererSystem(Engine eng) {
		super(eng);
		deps = new Class[] {WindowSystem.class};
		System.out.println("RS Constructor done");
	}

	SpriteComponent spriteComponent;
	TransformComponent transformComponent;
	WindowSystem windowSystem;
  AnimatorSystem as = new AnimatorSystem();

	@Override
	public void init() {
		System.out.println("RS init");

		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glLoadIdentity();

		glEnable(GL_TEXTURE_2D);

		//TODO: Fetch and render AnimatedSprite as well once done
		spriteComponent = eng.getComponent(SpriteComponent.class);
		windowSystem = eng.getSystem(WindowSystem.class);
		transformComponent = eng.getComponent(TransformComponent.class);
		System.out.println(transformComponent);
	}

	protected void render(Sprite sp,int ColSprites, int currSprite, 
			float x, float y, float z) {
    //ColSprites is no of sprites present in column of spritesheet
    //currSprite refers to the current sprite being rendered
		sp.bindTexture();
		glPushMatrix();
		//TODO: Replace with x and y coords
		//glTranslatef(100f, 200, 0);
	    glTranslatef(x, y, z);

		glBegin(GL_QUADS);

		int width = sp.getWidth();
		int height = sp.getHeight();

    float sprite_tex_width = 1/(float)ColSprites;

    float pt0 = sp.getTexcoord(0, 0);
    float pt1 = sp.getTexcoord(0, 1);
    float pt2 = sp.getTexcoord(1, 0);
    float pt3 = sp.getTexcoord(1, 1);

		glTexCoord2f((sprite_tex_width*currSprite), 1-pt1);
		glVertex2f(0, 0);

		glTexCoord2f((sprite_tex_width*currSprite), 1-pt3);
		glVertex2f(0, height);

		glTexCoord2f((sprite_tex_width*(currSprite+1)), 1-pt3);
		glVertex2f(width/ColSprites, height);

		glTexCoord2f((sprite_tex_width*(currSprite+1)), 1-pt1);
		glVertex2f(width/ColSprites, 0);

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
			Transform t = transformComponent.get(name);
			try {
				render(sprite,as.getSprite(name),as.getSpriteCount(name),t.getX(),t.getY(), t.getZ());
			} catch (Exception e) {
				System.out.println("ERROR: Could not render sprite, missing transform component: " + name);
			}
		}
		glPopMatrix();
	}
}
