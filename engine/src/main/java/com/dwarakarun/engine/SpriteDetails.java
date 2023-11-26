package com.dwarakarun.engine;

import java.util.*;
import java.io.*;

public class SpriteDetails {
  String name;
  public SpriteDetails(String name)
  {
    this.name = name;
  }
 
  public void getSpriteTime() {
    File f = new File("../../../../../../../DwarakaRun/assets/sprite_data.json");

    
    System.out.println(f);
  }
}
