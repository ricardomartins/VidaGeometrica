package com.rikmartins.libgdxext;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class BlendedAnimation extends Animation {
	public final float invertedFrameDuration;

	public class TransformedTextureRegion {
		private final TextureRegion theTextureRegion;
		private final float alpha;

		public TransformedTextureRegion(TextureRegion theTextureRegion,
				float alpha) {
			this.theTextureRegion = theTextureRegion;
			this.alpha = alpha;
		}

		public TextureRegion getTheTextureRegion() {
			return this.theTextureRegion;
		}

		public float getAlpha() {
			return this.alpha;
		}
	}

	public BlendedAnimation(float frameDuration, List keyFrames) {
		super(frameDuration, keyFrames);
		invertedFrameDuration = 1 / frameDuration;
	}

	public BlendedAnimation(float frameDuration, List keyFrames, int playType) {
		super(frameDuration, keyFrames, playType);
		invertedFrameDuration = 1 / frameDuration;
	}

	public BlendedAnimation(float frameDuration, TextureRegion... keyFrames) {
		super(frameDuration, keyFrames);
		invertedFrameDuration = 1 / frameDuration;
	}

	public List<TransformedTextureRegion> getBlendedKeyFrames(float stateTime,
			boolean looping) {
		TextureRegion currentTR = getKeyFrame(stateTime, looping);
		TextureRegion nextTR = getKeyFrame(stateTime + frameDuration, looping);
		float nextAlpha = (stateTime - (com.badlogic.gdx.math.MathUtils
				.floor(stateTime * invertedFrameDuration) * frameDuration))
				* invertedFrameDuration;
		float currentAlpha = 1f - nextAlpha;

		//nextAlpha = (float) Math.pow(nextAlpha, 0.3333f);
		//currentAlpha = (float) Math.pow(currentAlpha, 0.3333f);
		
		List<TransformedTextureRegion> result = new ArrayList<TransformedTextureRegion>();
		if (currentTR == nextTR) {
			result.add(new TransformedTextureRegion(currentTR, 1f));
		} else {
			result.add(new TransformedTextureRegion(currentTR, currentAlpha));
			result.add(new TransformedTextureRegion(nextTR, nextAlpha));
		}

		return result;
	}

	public List<TransformedTextureRegion> getBlendedKeyFrame(float stateTime) {
		return null;
	}
}
