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
  Transform ts;
  
  public KeySystem(Transform ts) {
    System.out.println("Keysystem Constructor Done");
    this.ts = ts;
  }

  static int x,y,time;

  void moveSprite(int keycode) {
    //65 = a
    //87 = w
    //83 = s
    //68 = d
    if(keycode == 65) {
      ts.setX(ts.getX()-8f);
    } else if(keycode == 87) {
      ts.setY(ts.getY()-50f);
    } else if(keycode == 83) {
      ts.setY(ts.getY()+50f);
    } else if(keycode == 68) {
      ts.setX(ts.getX()+8f);
    }
  }
}
