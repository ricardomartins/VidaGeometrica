package com.rikmartins.vidageometrica;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rikmartins.libgdxext.ExtendedAnimation;
import com.rikmartins.libgdxext.Frame;

public class VidaGeometrica implements ApplicationListener {
	private Texture texturas;
	private SpriteBatch lote;
	private ExtendedAnimation animadoCiano;
	private ExtendedAnimation animadoAmarelo;
	private ExtendedAnimation animadoBranco;
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
		Frame[][] frames = new Frame[regioesTexturas.length][regioesTexturas[0].length];
		for(int tri = 0; tri < regioesTexturas.length ; tri++){ //TextureRegion[] trArray : regioesTexturas){
			TextureRegion[] trArray = regioesTexturas[tri];
			for(int trj = 0; trj < trArray.length; trj++){
				frames[tri][trj] = new Frame(trArray[trj]);
				frames[tri][trj].setSize(0.5f, 0.5f);
				frames[tri][trj].setPosition(-0.25f, -0.25f);
				frames[tri][trj].setOrigin(0.25f, 0.25f);
				
			}
		}
		animadoCiano = new ExtendedAnimation(0.1f, frames[0]);
		animadoCiano.setPlayMode(ExtendedAnimation.LOOP);
		animadoAmarelo = new ExtendedAnimation(1f, frames[1]);
		animadoAmarelo.setPlayMode(ExtendedAnimation.LOOP);
		animadoBranco = new ExtendedAnimation(1f, frames[2]);
		animadoBranco.setPlayMode(ExtendedAnimation.LOOP);
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
		
		Sprite ciano = animadoCiano.getKeyFrame(stateTime, true);
		//ciano.setPosition(-ciano.getWidth() / 2,
		//		-ciano.getHeight() / 2);
		// ciano.setRotation(stateTime * 10);
		
		
		//inanimadoVerde.setSize(0.5f, 0.5f);
		//inanimadoVerde.setPosition(-inanimadoVerde.getWidth() / 2,
		//		-inanimadoVerde.getHeight() / 2);

		lote.setProjectionMatrix(camara.combined);
		lote.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		lote.begin();
		ciano.draw(lote);
		// inanimadoCastanho.draw(lote);
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
