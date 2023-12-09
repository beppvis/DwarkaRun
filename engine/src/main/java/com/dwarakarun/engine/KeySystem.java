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

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class KeySystem {
  public KeySystem() {
    System.out.println("Keysystem Constructor Done");
  }

  AnimatorSystem as = new AnimatorSystem();
  static int x,y,time;

  void moveSprite(int keycode) {
    //65 = a
    //87 = w
    //83 = s
    //68 = d
    if(keycode == 65) {
      x = as.getX();
      x -= 8;
      System.out.println("A "+x);
      as.changeX(x);
    } else if(keycode == 87) {
      y = as.getY();
      y -= 50;
      System.out.println("W "+y);
      as.changeY(y);
    } else if(keycode == 83) {
      y = as.getY();
      y += 50;
      System.out.println("S "+y);
      as.changeY(y);
    } else if(keycode == 68) {
      x = as.getX();
      x += 8;
      System.out.println("D "+x);
      as.changeX(x);
    }
  }
}
