package de.qrg.view;

import java.time.Duration;

public interface View {

	public abstract void showIntro();

	public abstract void newGame();

	public abstract void showResult(Duration duration);

}