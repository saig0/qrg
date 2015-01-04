package de.qrg.controller;

import java.time.Duration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import de.qrg.view.View;

public class Controller {

	private GpioPinDigitalOutput led;
	private GpioPinDigitalInput button;

	private final View view;
	private GpioController gpio;

	private boolean inGame = false;

	private Controller(View view) {
		this.view = view;
	}

	public static Controller create(View view) {
		Controller controller = new Controller(view);
		Game game = new Game(controller);

		controller.start(game);

		view.showIntro();

		return controller;
	}

	private void start(final Game game) {
		gpio = GpioFactory.getInstance();

		led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "LED",
				PinState.LOW);
		button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
				PinPullResistance.PULL_UP);

		button.addListener(new GpioPinListenerDigital() {

			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isLow()) {
					if (!inGame) {
						view.newGame();
						game.start();
					} else {
						Duration duration = game.end();
						view.showResult(duration);
					}
					inGame = !inGame;
				}
			}
		});
	}

	public void exit() {
		gpio.shutdown();
	}

	public void pulseLed(Duration pulse) {
		led.pulse(pulse.toMillis(), true);
	}
}
