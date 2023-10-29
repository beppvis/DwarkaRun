package com.dwarakarun.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowSystem extends GameSystem {
	private long handle;
	private int vbo;
	private int vao;
	private int shader;

	public WindowSystem(Engine eng) {
		super(eng);
		deps = new String[] {};
	}

	@Override
	public void init() {
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to init GLFW");
		}
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		handle = glfwCreateWindow(800, 600, "Dwaraka Run", NULL, NULL);
		if (handle == NULL) {
			System.out.println("NULL window");
		}

		glfwMakeContextCurrent(handle);
		glfwSwapInterval(1);
		glfwShowWindow(handle);

		GL.createCapabilities();

		glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
		System.out.println("Init windowsystem");
		glLoadIdentity();
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
		glPushMatrix();
		glTranslatef(-0.25f, -0.25f, 0);
		glColor3f(0.5f, 0.5f, 0.4f);
		glBegin(GL_QUADS);
		glVertex2f(0 ,0);
		glVertex2f(0, 0.5f);
		glVertex2f(0.5f, 0.5f);
		glVertex2f(0.5f, 0);
		glEnd();
		glPopMatrix();
	}
}
