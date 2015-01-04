package de.qrg.view;

import java.time.Duration;

public class CommandlineView implements View {

	public void showIntro() {
		System.out.println("=== Quick Reaction Game ===\n");
		System.out
				.println("Press the button as fast as you can after the led has turned off.\n");
		System.out.println("---------");
		System.out.println("Press the button to start");
		System.out.println("---------\n");
	}

	public void newGame() {
		System.out.println("--- START ---");
	}

	public void showResult(Duration duration) {
		System.out.println("Reaction in " + duration.toMillis() + "ms");
		System.out.println("---------\n");
	}
}
