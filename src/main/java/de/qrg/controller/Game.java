package de.qrg.controller;

import java.time.Duration;

public class Game {

	private final Controller controller;

	private long start;

	public Game(Controller controller) {
		this.controller = controller;
	}

	public Duration end() {
		long end = System.currentTimeMillis();
		Duration duration = Duration.ofMillis(end - start);
		return duration;
	}

	public void start() {
		Duration pulse = Duration.ofSeconds(Math.round(Math.random() * 10 + 3));
		controller.pulseLed(pulse);

		start = System.currentTimeMillis();
	}
}
