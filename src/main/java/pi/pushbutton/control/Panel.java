package pi.pushbutton.control;

import pi.pushbutton.PushButtonWithLED;

import com.pi4j.component.ObserveableComponentBase;
import com.pi4j.component.light.LightListener;
import com.pi4j.component.switches.SwitchListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;

public class Panel extends ObserveableComponentBase {
	PushButtonWithLED red, green, blue, yellow, white;
	Pin redPin, greenPin, bluePin, yellowPin, whitePin;

	public Panel(GpioController gpio, GpioProvider providerForButtons,
			GpioProvider providerForLEDs) {
		red = new PushButtonWithLED(gpio, providerForButtons, redPin,
				providerForLEDs, redPin);

		green = new PushButtonWithLED(gpio, providerForButtons, greenPin,
				providerForLEDs, greenPin);
		blue = new PushButtonWithLED(gpio, providerForButtons, bluePin,
				providerForLEDs, bluePin);
		yellow = new PushButtonWithLED(gpio, providerForButtons, yellowPin,
				providerForLEDs, yellowPin);
		white = new PushButtonWithLED(gpio, providerForButtons, whitePin,
				providerForLEDs, whitePin);
		PushButtonWithLED[] allPushButtonWithLEDs = { red, green, blue, yellow,
				white };
		if (this instanceof SwitchListener) {
			for (PushButtonWithLED pushButtonWithLED : allPushButtonWithLEDs) {
				pushButtonWithLED.addListener((SwitchListener) this);
			}
		}
		if (this instanceof LightListener) {
			for (PushButtonWithLED pushButtonWithLED : allPushButtonWithLEDs) {
				pushButtonWithLED.addListener((LightListener) this);
			}
		}
	}

	public PushButtonWithLED getRed() {
		return red;
	}

	public PushButtonWithLED getGreen() {
		return green;
	}

	public PushButtonWithLED getBlue() {
		return blue;
	}

	public PushButtonWithLED getYellow() {
		return yellow;
	}

	public PushButtonWithLED getWhite() {
		return white;
	}
}
