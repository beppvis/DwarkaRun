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


public class WindowSystem extends GameSystem {
	private long handle;
	private int width;
	private int height;
	Texture texture;

	private void handleErrors() {
		int err = glGetError();
		while (err != GL_NO_ERROR) {
			System.out.format("ERROR: %d\n", err);
			err = glGetError();
		}
	}

	public WindowSystem(Engine eng) {
		super(eng);
		deps = new Class[] {};
		System.out.println("WS Constructor done");
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	@Override
	public void init() {
		System.out.println("WS init");
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to init GLFW");
		}
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		width = 800;
		height = 600;

		handle = glfwCreateWindow(width, height, "Dwaraka Run", NULL, NULL);
		if (handle == NULL) {
			System.out.println("NULL window");
		}

    KeySystem ks = new KeySystem();
    glfwSetKeyCallback(handle, (handle, key, scancode, action, mods) -> {
      if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
        glfwSetWindowShouldClose(handle, true);
      } else {
        ks.moveSprite(key);
      }
    });

		glfwMakeContextCurrent(handle);
		glfwSwapInterval(1);
		glfwShowWindow(handle);

		GL.createCapabilities();

}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void swapBuffers() {
		glfwSwapBuffers(handle);
	}

	public void pollEvents() {
		glfwPollEvents();
	}

	@Override
	public void end() {
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		glfwTerminate();
	}

	@Override
	public void update() {
		if (glfwWindowShouldClose(handle)) {
			eng.end();
		}
	}
}
