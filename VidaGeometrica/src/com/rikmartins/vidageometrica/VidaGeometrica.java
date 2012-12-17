package com.rikmartins.vidageometrica;

import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rikmartins.libgdxext.BlendedAnimation;
import com.rikmartins.libgdxext.BlendedAnimation.TransformedTextureRegion;

public class VidaGeometrica implements ApplicationListener {
	private Texture texturas;
	private SpriteBatch lote;
	private BlendedAnimation animadoCiano;
	private BlendedAnimation animadoAmarelo;
	private BlendedAnimation animadoBranco;
	private Sprite inanimadoCastanho;
	private Sprite inanimadoVerde;

	private OrthographicCamera camara;

	private float stateTime;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camara = new OrthographicCamera(1, h / w);

		texturas = new Texture(Gdx.files.internal("graficos/texturas.png")
				.toString());
		TextureRegion[][] regioesTexturas = TextureRegion.split(texturas,
				texturas.getWidth() / 4, texturas.getHeight() / 4);
		for(TextureRegion[] trArray : regioesTexturas){
			for(TextureRegion tr : trArray){
				tr.getRegionHeight();
				tr.getRegionWidth();
				tr.getRegionX();
				tr.getRegionY();
				tr.getU();
				tr.getV();
				tr.getU2();
				tr.getV2();
			}
		}
		animadoCiano = new BlendedAnimation(0.025f, regioesTexturas[0]);
		animadoCiano.setPlayMode(BlendedAnimation.LOOP);
		animadoAmarelo = new BlendedAnimation(1f, regioesTexturas[1]);
		animadoAmarelo.setPlayMode(BlendedAnimation.LOOP);
		animadoBranco = new BlendedAnimation(1f, regioesTexturas[2]);
		animadoBranco.setPlayMode(BlendedAnimation.LOOP);
		inanimadoCastanho = new Sprite(regioesTexturas[3][0]);
		inanimadoVerde = new Sprite(regioesTexturas[3][1]);
		// inanimadoVerde.
		stateTime = 0f;
		lote = new SpriteBatch();
	}

	@Override
	public void dispose() {
		texturas.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		lote.setProjectionMatrix(camara.combined);

		stateTime += Gdx.graphics.getDeltaTime();

		inanimadoVerde.setSize(0.5f, 0.5f);
		inanimadoVerde.setPosition(-inanimadoVerde.getWidth() / 2,
				-inanimadoVerde.getHeight() / 2);

		List<TransformedTextureRegion> currentFrame = animadoBranco
				.getBlendedKeyFrames(stateTime, true);

		lote.setProjectionMatrix(camara.combined);
		lote.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		lote.begin();
		// inanimadoVerde.draw(lote);
		for (TransformedTextureRegion ttr : currentFrame) {
			lote.setColor(0f, 1f, 0f, ttr.getAlpha());
			lote.draw(ttr.getTheTextureRegion(), -0.5f, -0.5f, 1f, 1f);
		}
		lote.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
