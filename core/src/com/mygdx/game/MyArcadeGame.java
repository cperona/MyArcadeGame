package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyArcadeGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion[] frames;
	Animation<TextureRegion> animation;
	float duration;
	private Sound soundEffect;
	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setupAnimation();
		soundEffect = Gdx.audio.newSound(Gdx.files.internal("bat_sound_effect.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("a-folk-story-background-music.mp3"));
		music.setLooping(true);

		music.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(125, 125, 125, 1);

		duration += Gdx.graphics.getDeltaTime();
		TextureRegion anim = animation.getKeyFrame(duration, true);

		soundEffect.play();

		batch.begin();


		batch.draw(anim, 100, 100);


		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		soundEffect.dispose();
		music.dispose();
	}

	public void setupAnimation() {
		TextureRegion textureRegion = new TextureRegion(new Texture("32x32-bat-sprite.png"), 128, 128);
		TextureRegion[][] textureRegionTemp = textureRegion.split(textureRegion.getRegionWidth()/4, textureRegion.getRegionHeight()/4);
		frames = new TextureRegion[textureRegionTemp.length * textureRegionTemp[0].length];
		int index = 0;
		for (int i = 0; i < textureRegionTemp.length; i++) {
			for (int j = 0; j < textureRegionTemp[0].length; j++) {
				frames[index++] = textureRegionTemp[i][j];
			}
		}
		animation = new Animation<TextureRegion>(0.25f, frames);
		duration = 0f;
	}

	public void provaSprites() {
		int x = 150;
		int y = 150;
		for (int i = 0; i < frames.length; i++) {
			batch.draw(frames[i], x, y);
			x+=20;
			y+=20;
		}
	}
}
