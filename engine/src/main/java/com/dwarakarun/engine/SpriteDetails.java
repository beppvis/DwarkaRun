package com.dwarakarun.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.nio.file.*;
import java.util.*;
import java.lang.System.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.stb.STBImage.*;

public class SpriteDetails {
  public SpriteDetails() {
    System.out.println("SpriteDetails Constructor Done");
  }
  
  //no_of_sprites/curr_sprite/sprite_time/time_since_curr_sprite_last_changed
  public static int shinobiSprite[] = {8,0,35,0};
  public static int vampSprite[] = {6,0,55,0};


  //no of sprites in spritesheet
  public int getSpriteQuant(String name) {
    if(name=="shinobiSprite")
      return shinobiSprite[0];
    else if(name=="vampSprite")
      return vampSprite[0];
    return 1;
  }

  //current sprite count and change it
  public int getCurrSprite(String name) {
    if(name=="shinobiSprite") {
      shinobiSprite[1]++;
      shinobiSprite[1] %= (shinobiSprite[0]+1);
      return shinobiSprite[1];
    } else if(name=="vampSprite") {
      vampSprite[1]++;
      vampSprite[1] %= (vampSprite[0]+1);
      return vampSprite[1];
    }
    return 1;
  }

//sprite time Interval
  public int getSpriteTimeInterval(String name) {
    if(name=="shinobiSprite")
      return shinobiSprite[2];
    else if(name=="vampSprite")
      return vampSprite[2];
    return 1;
  }

  //get last activated time of sprite and change it
  public int getLastTime(String name) {
    if(name=="shinobiSprite")
      return shinobiSprite[3];
    else if(name=="vampSprite")
      return vampSprite[3];
    return 1;
  }
}
