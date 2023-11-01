package com.dwarakarun.engine;

import java.util.*;

public class Sprite {
	private Texture texture;
	protected float texcoords[][];
	protected int width;
	protected int height;
	private String path;
	public Sprite() {
		texture = new Texture();
		texcoords = new float[][] {{0, 0}, {1, 1}};
	}
	public Sprite(String path) {
		this();
		this.path = path;
	}
	public void load() {
		texture.load(path);
		width = texture.getWidth();
		height = texture.getHeight();
	}
	public void load(String path) {
		this.path = path;
		load();
	}
	public void bindTexture() {
		texture.bind();
	}
	public float getTexcoord(int point, int axis) {
		return texcoords[point][axis];
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void scale(float factor) {
		width *= factor;
		height *= factor;
	}
}
