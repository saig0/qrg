package de.qrg;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GpioTest {

	public static void main(String[] args) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_00, "MyLED", PinState.HIGH);
		System.out.println("--> GPIO state should be: ON");

		Thread.sleep(5000);

		// turn off gpio pin #01
		pin.low();
		System.out.println("--> GPIO state should be: OFF");

		Thread.sleep(5000);

		// turn on gpio pin #01 for 1 second and then off
		System.out.println("--> GPIO state should be: ON for only 1 second");
		pin.pulse(1000, true); // set second argument to 'true' use a blocking
								// call

		Thread.sleep(5000);

		System.out.println("--> GPIO state should blink: for only 1 second");
		pin.blink(1000);

		Thread.sleep(5000);
		pin.low();

		// stop all GPIO activity/threads by shutting down the GPIO controller
		// (this method will forcefully shutdown all GPIO monitoring threads and
		// scheduled tasks)
		gpio.shutdown();
	}
}
