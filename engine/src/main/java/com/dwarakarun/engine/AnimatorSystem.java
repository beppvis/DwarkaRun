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


public class AnimatorSystem extends GameSystem{
  public AnimatorSystem(Engine eng) {
    super(eng);
    deps = new Class[] {};
    System.out.println("AS Constructor done");
  }

  SpriteDetails sp = new SpriteDetails();
  TransformComponent tc;

  @Override
  public void init() {
    System.out.println("AS init");
  }

  @Override
  public void update() {
    
  }
} 
