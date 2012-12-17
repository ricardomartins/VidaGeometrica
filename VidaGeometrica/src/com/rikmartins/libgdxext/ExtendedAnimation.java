package com.rikmartins.libgdxext;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.MathUtils;

/**
 * <p>
 * An Animation stores a list of {@link Frame}s representing an animated
 * sequence, e.g. for running or jumping. Each region of an Animation is called
 * a key frame, multiple key frames make up the animation.
 * </p>
 * 
 * @author rikhard.martins
 */

public class ExtendedAnimation {
	public class ExtendedAnimationException extends Exception {
		public ExtendedAnimationException() {
		}

		public ExtendedAnimationException(String msg) {
			super(msg);
		}

		private static final long serialVersionUID = -3106985043933415871L;
	}


	public static final int NORMAL = 0;
	public static final int REVERSED = 1;
	public static final int LOOP = 2;
	public static final int LOOP_REVERSED = 3;
	public static final int LOOP_PINGPONG = 4;
	public static final int LOOP_RANDOM = 5;

	final Frame[] keyFrames;
	public final float defaultFrameDuration;
	public final float animationDuration;

	private int playMode = NORMAL;

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param defaultFrameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link Frame}s representing the frames.
	 */
	public ExtendedAnimation(float defaultFrameDuration, List<Frame> keyFrames) {
		this.defaultFrameDuration = defaultFrameDuration;
		this.animationDuration = keyFrames.size() * defaultFrameDuration;
		this.keyFrames = new Frame[keyFrames.size()];
		for (int i = 0, n = keyFrames.size(); i < n; i++) {
			this.keyFrames[i] = (Frame) keyFrames.get(i);
		}

		this.playMode = NORMAL;
	}

	/**
	 * Constructor, storing the frame duration, key frames and play type.
	 * 
	 * @param defaultFrameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link Frame}s representing the frames.
	 * @param playType
	 *            the type of animation play (NORMAL, REVERSED, LOOP,
	 *            LOOP_REVERSED, LOOP_PINGPONG, LOOP_RANDOM)
	 */
	public ExtendedAnimation(float defaultFrameDuration, List<Frame> keyFrames,
			int playType) {

		this.defaultFrameDuration = defaultFrameDuration;
		this.animationDuration = keyFrames.size() * defaultFrameDuration;
		this.keyFrames = new Frame[keyFrames.size()];
		for (int i = 0, n = keyFrames.size(); i < n; i++) {
			this.keyFrames[i] = (Frame) keyFrames.get(i);
		}

		this.playMode = playType;
	}

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param defaultFrameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link Frame}s representing the frames.
	 */
	public ExtendedAnimation(float defaultFrameDuration, Frame... keyFrames) {
		this.defaultFrameDuration = defaultFrameDuration;
		this.animationDuration = keyFrames.length * defaultFrameDuration;
		this.keyFrames = keyFrames;
		this.playMode = NORMAL;
	}

	/**
	 * Returns a {@link Sprite} based on the so called state time. This is the
	 * amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on. The mode specifies
	 * whether the animation is looping or not.
	 * 
	 * @param stateTime
	 *            the time spent in the state represented by this animation.
	 * @param looping
	 *            whether the animation is looping or not.
	 * @return the Sprite representing the frame of animation for the given
	 *         state time.
	 */
	public Sprite getKeyFrame(float stateTime, boolean looping) {
		// we set the play mode by overriding the previous mode based on looping
		// parameter value
		if (looping && (playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == NORMAL)
				playMode = LOOP;
			else
				playMode = LOOP_REVERSED;
		} else if (!looping && !(playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == LOOP_REVERSED)
				playMode = REVERSED;
			else
				playMode = LOOP;
		}

		return getKeyFrame(stateTime);
	}

	/**
	 * Returns a {@link Sprite} based on the so called state time. This is the
	 * amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on using the mode
	 * specified by {@link setPlayType} method.
	 * 
	 * @param stateTime
	 * @return the TextureRegion representing the frame of animation for the
	 *         given state time.
	 */
	public Sprite getKeyFrame(float stateTime) {
		int frameNumber = (int) (stateTime / defaultFrameDuration);

		switch (playMode) {
		case NORMAL:
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		case LOOP:
			frameNumber = frameNumber % keyFrames.length;
			break;
		case LOOP_PINGPONG:
			frameNumber = frameNumber % (keyFrames.length * 2);
			if (frameNumber >= keyFrames.length)
				frameNumber = keyFrames.length - 1
						- (frameNumber - keyFrames.length);
			break;
		case LOOP_RANDOM:
			frameNumber = MathUtils.random(keyFrames.length - 1);
			break;
		case REVERSED:
			frameNumber = Math.max(keyFrames.length - frameNumber - 1, 0);
			break;
		case LOOP_REVERSED:
			frameNumber = frameNumber % keyFrames.length;
			frameNumber = keyFrames.length - frameNumber - 1;
			break;

		default:
			// play normal otherwise
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		}

		return keyFrames[frameNumber];
	}

	/**
	 * Sets the animation play mode.
	 * 
	 * @param playMode
	 *            can be one of the following: Animation.NORMAL,
	 *            Animation.REVERSED, Animation.LOOP, Animation.LOOP_REVERSED,
	 *            Animation.LOOP_PINGPONG, Animation.LOOP_RANDOM
	 */
	public void setPlayMode(int playMode) {
		this.playMode = playMode;
	}

	/**
	 * Whether the animation would be finished if played without looping
	 * (PlayMode Animation#NORMAL), given the state time.
	 * 
	 * @param stateTime
	 * @return whether the animation is finished.
	 */
	public boolean isAnimationFinished(float stateTime) {
		int frameNumber = (int) (stateTime / defaultFrameDuration);
		return keyFrames.length - 1 < frameNumber;
	}
}
