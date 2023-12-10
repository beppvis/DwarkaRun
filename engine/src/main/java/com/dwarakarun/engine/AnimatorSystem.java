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


public class AnimatorSystem {
  public AnimatorSystem() {
    System.out.println("Animated System Constructor");
  }
  SpriteDetails sp = new SpriteDetails();
  
  static int sprite_count=0;
  //no of sprites in spritesheet
  public int getSprite(String name) {
    if(name=="shinobiSprite")
      return sp.shinobiSprite[0];
    else if(name=="vampSprite")
      return sp.vampSprite[0];
    return 1;
  }
  //current sprite count
  public int getSpriteCount(String name) {
    sprite_count++;
    if(name=="shinobiSprite") {
      sprite_count %= (sp.shinobiSprite[0]+1);
      return sprite_count;
    } else if(name=="vampSprite") {
      sprite_count %= (sp.vampSprite[0]+1);
      return sprite_count;
    }
    return 1;
  }

//x coords
  static int x=300;
  static int x1=100;
  public void changeX(int x) {
    this.x = x;
  }
  public int getX(String name) {
    if(name=="shinobiSprite")
      return x;
    else if(name=="vampSprite")
      return x1;
    return 1;
  }

//y coords
  static int y=100;
  static int y1=50;
  public void changeY(int y) {
    this.y = y;
  }
  public int getY(String name) {
    if(name=="shinobiSprite")
      return y;
    else if(name=="vampSprite")
      return y1;
    return 1;
  }

//sprite time
  public int getSpriteTime(String name) {
    if(name=="shinobiSprite")
      return sp.shinobiSprite[1];
    else if(name=="vampSprite")
      return sp.vampSprite[1];
    return 1;
  }
} 
