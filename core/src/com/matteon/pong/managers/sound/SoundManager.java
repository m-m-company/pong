package com.matteon.pong.managers.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private Sound hit;
	private Sound goal;
	public SoundManager() {
		hit = Gdx.audio.newSound(Gdx.files.internal("paddle.wav"));
		goal = Gdx.audio.newSound(Gdx.files.internal("goal.wav"));
	}
	public void playHit() {
		this.hit.play();
	}
	public void playGoal() {
		this.goal.play();
	}
}
