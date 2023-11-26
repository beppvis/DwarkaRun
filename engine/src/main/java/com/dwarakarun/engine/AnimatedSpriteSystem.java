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


public class AnimatedSpriteSystem extends RendererSystem {
  public AnimatedSpriteSystem(Engine eng) {
    super(eng);
    deps = new Class[] {WindowSystem.class, RendererSystem.class};
    System.out.println("AnimatedSprite Constructor Done");
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
      super.render(sprite, 5, sprite_count++);
      sprite_count %= 6; //create class that automatically calculates the arguments needed to be given to the render function
    }
    glPopMatrix();
  }
}
