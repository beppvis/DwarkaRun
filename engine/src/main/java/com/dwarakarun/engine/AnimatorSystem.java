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
  
  static int sprite_count=0;
  //no of sprites in spritesheet
  public int getSprite() {
    return 8;
  }
  //current sprite count
  public int getSpriteCount() {
    sprite_count++;
    sprite_count %= 9;
    return sprite_count;
  }

  static int x=300;
  public void changeX(int x) {
    this.x = x;
  }
  public int getX() {
    return x;
  }

  static int y=100;
  public void changeY(int y) {
    this.y = y;
  }
  public int getY() {
    return y;
  }

  static int spriteTime = 35;
  public int changeSpriteTime(int spriteTime) {
    this.spriteTime = spriteTime;
    return spriteTime;
  }
  public int getSpriteTime() {
    return spriteTime;
  }
} 
