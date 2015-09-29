package pi.pushbutton.control;

import java.util.EnumSet;

import pi.pushbutton.PushButtonWithLED;

import com.pi4j.component.ObserveableComponentBase;
import com.pi4j.component.light.LightListener;
import com.pi4j.component.switches.SwitchListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.impl.PinImpl;

public class Panel extends ObserveableComponentBase {
	protected PushButtonWithLED red, green, blue, yellow, white;
	final Pin redPin, greenPin, bluePin, yellowPin, whitePin;
	protected final PushButtonWithLED[] allPushButtonWithLEDs;
	protected final static EnumSet<PinMode> DIGITAL_IO_MODES = EnumSet.of(
			PinMode.DIGITAL_INPUT, PinMode.DIGITAL_OUTPUT);

	public Panel(GpioController gpio, GpioProvider providerForButtons,
			GpioProvider providerForLEDs) {
		String providerName = providerForButtons.getName();
		redPin = new PinImpl(providerName, 0, "red", DIGITAL_IO_MODES);
		greenPin = new PinImpl(providerName, 1, "green", DIGITAL_IO_MODES);
		bluePin = new PinImpl(providerName, 2, "blue", DIGITAL_IO_MODES);
		yellowPin = new PinImpl(providerName, 3, "yellow", DIGITAL_IO_MODES);
		whitePin = new PinImpl(providerName, 4, "white", DIGITAL_IO_MODES);

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
