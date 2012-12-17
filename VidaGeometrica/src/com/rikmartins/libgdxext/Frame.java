package com.rikmartins.libgdxext;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Frame extends Sprite {
	private float frameDuration;

	public void setFrameDuration(float frameDuration) {
		this.frameDuration = frameDuration;
	}

	public float getFrameDuration() {
		return this.frameDuration;
	}

	public Boolean hasDuration() {
		return this.frameDuration > 0;
	}

	public Frame() {
		setFrameDuration(-1f);
	}

	public Frame(Texture texture, float frameDuration) {
		super(texture);
		setFrameDuration(frameDuration);
	}

	public Frame(Texture texture) {
		super(texture);
		setFrameDuration(-1f);
	}

	public Frame(TextureRegion region, float frameDuration) {
		super(region);
		setFrameDuration(frameDuration);
	}

	public Frame(TextureRegion region) {
		super(region);
		setFrameDuration(-1f);
	}

	public Frame(Sprite sprite, float frameDuration) {
		super(sprite);
		setFrameDuration(frameDuration);
	}

	public Frame(Sprite sprite) {
		super(sprite);
		setFrameDuration(-1f);
	}

	public Frame(Texture texture, int srcWidth, int srcHeight,
			float frameDuration) {
		super(texture, srcWidth, srcHeight);
		setFrameDuration(frameDuration);
	}

	public Frame(Texture texture, int srcWidth, int srcHeight) {
		super(texture, srcWidth, srcHeight);
		setFrameDuration(-1f);
	}

	public Frame(Texture texture, int srcX, int srcY, int srcWidth,
			int srcHeight, float frameDuration) {
		super(texture, srcX, srcY, srcWidth, srcHeight);
		setFrameDuration(frameDuration);
	}

	public Frame(Texture texture, int srcX, int srcY, int srcWidth,
			int srcHeight) {
		super(texture, srcX, srcY, srcWidth, srcHeight);
		setFrameDuration(-1f);
	}

	public Frame(TextureRegion region, int srcX, int srcY, int srcWidth,
			int srcHeight, float frameDuration) {
		super(region, srcX, srcY, srcWidth, srcHeight);
		setFrameDuration(frameDuration);
	}

	public Frame(TextureRegion region, int srcX, int srcY, int srcWidth,
			int srcHeight) {
		super(region, srcX, srcY, srcWidth, srcHeight);
		setFrameDuration(-1f);
	}
}
