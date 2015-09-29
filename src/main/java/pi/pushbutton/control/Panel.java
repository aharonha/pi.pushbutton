package pi.pushbutton.control;

import pi.pushbutton.PushButtonWithLED;

import com.pi4j.component.ObserveableComponentBase;
import com.pi4j.component.light.LightListener;
import com.pi4j.component.switches.SwitchListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;

public class Panel extends ObserveableComponentBase {
	protected PushButtonWithLED red, green, blue, yellow, white;
	// final Pin redPin, greenPin, bluePin, yellowPin, whitePin;
	protected final PushButtonWithLED[] allPushButtonWithLEDs;

	public Panel(GpioController gpio, GpioProvider providerForButtons,
			GpioProvider providerForLEDs, Pin redButtonPin, Pin greenButtonPin,
			Pin blueButtonPin, Pin yellowButtonPin, Pin whiteButtonPin,
			Pin redLEDPin, Pin greenLEDPin, Pin blueLEDPin, Pin yellowLEDPin,
			Pin whiteLEDPin) {
		red = new PushButtonWithLED(gpio, providerForButtons, redButtonPin,
				providerForLEDs, redLEDPin, "RED");
		green = new PushButtonWithLED(gpio, providerForButtons, greenButtonPin,
				providerForLEDs, greenLEDPin, "GREEN");
		blue = new PushButtonWithLED(gpio, providerForButtons, blueButtonPin,
				providerForLEDs, blueLEDPin, "BLUE");
		yellow = new PushButtonWithLED(gpio, providerForButtons,
				yellowButtonPin, providerForLEDs, yellowLEDPin, "YELLOW");
		white = new PushButtonWithLED(gpio, providerForButtons, whiteButtonPin,
				providerForLEDs, whiteLEDPin, "WHITE");
		allPushButtonWithLEDs = new PushButtonWithLED[] { red, green, blue,
				yellow, white };
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
