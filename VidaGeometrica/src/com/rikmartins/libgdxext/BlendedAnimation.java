package com.rikmartins.libgdxext;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlendedAnimation extends Animation {
	public class TransformedTextureRegion {

	}

	public BlendedAnimation(float frameDuration, List keyFrames) {
		super(frameDuration, keyFrames);
	}
	
	public BlendedAnimation(float frameDuration, List keyFrames, int playType){
		super(frameDuration, keyFrames, playType);
	}

	public BlendedAnimation(float frameDuration, TextureRegion... keyFrames){
		super(frameDuration, keyFrames);
	}
	
	public List<TextureRegion> getBlendedKeyFrames (float stateTime, boolean looping) {
		return null;
	}

	public List<TextureRegion> getBlendedKeyFrame (float stateTime) {
		return null;
	}
}
