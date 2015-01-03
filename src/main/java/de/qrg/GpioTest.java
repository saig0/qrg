package de.qrg;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioTest {

	public static void main(String[] args) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_00, "MyLED", PinState.HIGH);
		// provision gpio pin #02 as an input pin with its internal pull down
		// resistor enabled
		final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(
				RaspiPin.GPIO_02, PinPullResistance.PULL_UP);

		// create and register gpio pin listener
		myButton.addListener(new GpioPinListenerDigital() {

			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: "
						+ event.getPin() + " = " + event.getState());
				if (myButton.isLow()) {
					pin.toggle();
				}
			}
		});

		System.out.println("press the button to swith led");
		while (true) {
			Thread.sleep(500);
		}

		// System.out.println("--> GPIO state should be: ON");
		//
		// Thread.sleep(5000);
		//
		// // turn off gpio pin #01
		// pin.low();
		// System.out.println("--> GPIO state should be: OFF");
		//
		// Thread.sleep(5000);
		//
		// // turn on gpio pin #01 for 1 second and then off
		// System.out.println("--> GPIO state should be: ON for only 1 second");
		// pin.pulse(1000, true); // set second argument to 'true' use a
		// blocking
		// // call
		//
		// Thread.sleep(5000);
		//
		// System.out.println("--> GPIO state should blink: for only 1 second");
		// pin.blink(1000);
		//
		// Thread.sleep(5000);
		// pin.low();
		//
		// // stop all GPIO activity/threads by shutting down the GPIO
		// controller
		// // (this method will forcefully shutdown all GPIO monitoring threads
		// and
		// // scheduled tasks)
		// gpio.shutdown();
	}
}
