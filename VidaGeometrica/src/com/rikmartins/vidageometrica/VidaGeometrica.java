package com.rikmartins.vidageometrica;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VidaGeometrica implements ApplicationListener {
	private Texture texturas;
	private SpriteBatch lote;
	private Animation animadoCiano;
	private Animation animadoAmarelo;
	private Animation animadoBranco;
	private Sprite inanimadoCastanho;
	private Sprite inanimadoVerde;
	
	private OrthographicCamera camara;
	
	private float stateTime;
	
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camara = new OrthographicCamera(1, h/w);
		
		texturas = new Texture(Gdx.files.internal("graficos/texturas.png").toString());
		TextureRegion[][] regioesTexturas = TextureRegion.split(texturas, texturas.getWidth() / 4, texturas.getHeight() / 4);
		animadoCiano = new Animation(0.025f, regioesTexturas[0]);
		animadoAmarelo = new Animation(0.050f, regioesTexturas[1]);
		animadoBranco = new Animation(0.075f, regioesTexturas[2]);
		inanimadoCastanho = new Sprite(regioesTexturas[3][0]);
		inanimadoVerde = new Sprite(regioesTexturas[3][1]);
		//inanimadoVerde.
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
		inanimadoVerde.setPosition(-inanimadoVerde.getWidth()/2, -inanimadoVerde.getHeight()/2);
		
		
		TextureRegion currentFrame = animadoBranco.getKeyFrame(stateTime, true);
		
		lote.setProjectionMatrix(camara.combined);
		lote.begin();
		//inanimadoVerde.draw(lote);
		lote.draw(currentFrame, -0.5f, -0.5f, 1f, 1f);
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
