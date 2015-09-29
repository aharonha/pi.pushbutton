package pi.pushbutton;

import java.util.concurrent.Future;

import com.pi4j.component.ObserveableComponentBase;
import com.pi4j.component.light.LED;
import com.pi4j.component.light.LightListener;
import com.pi4j.component.light.impl.GpioLEDComponent;
import com.pi4j.component.switches.MomentarySwitch;
import com.pi4j.component.switches.SwitchListener;
import com.pi4j.component.switches.SwitchState;
import com.pi4j.component.switches.impl.GpioMomentarySwitchComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;

public class PushButtonWithLED extends ObserveableComponentBase implements LED,
		MomentarySwitch {
	GpioLEDComponent ledComponent;

	GpioMomentarySwitchComponent switchComponent;

	public PushButtonWithLED(GpioController gpio,
			GpioProvider providerForButton, Pin button,
			GpioProvider providerForLED, Pin led) {
		switchComponent = new GpioMomentarySwitchComponent(
				gpio.provisionDigitalInputPin(providerForButton, button));
		ledComponent = new GpioLEDComponent(gpio.provisionDigitalOutputPin(
				providerForLED, led));
	}

	@Override
	public void on() {
		ledComponent.on();
	}

	@Override
	public void off() {
		ledComponent.off();
	}

	@Override
	public boolean isOn() {
		return ledComponent.isOn();
	}

	@Override
	public boolean isOff() {
		return ledComponent.isOff();
	}

	@Override
	public void addListener(LightListener... listener) {
		ledComponent.addListener(listener);

	}

	@Override
	public void removeListener(LightListener... listener) {
		ledComponent.removeListener(listener);
	}

	@Override
	public SwitchState getState() {
		return switchComponent.getState();
	}

	@Override
	public boolean isState(SwitchState state) {
		return switchComponent.isState(state);
	}

	@Override
	public void addListener(SwitchListener... listener) {
		switchComponent.addListener(listener);
	}

	@Override
	public void removeListener(SwitchListener... listener) {
		switchComponent.removeListener(listener);
	}

	@Override
	public void toggle() {
		ledComponent.toggle();
	}

	@Override
	public Future<?> blink(long delay) {
		return ledComponent.blink(delay);
	}

	@Override
	public Future<?> blink(long delay, long duration) {
		return ledComponent.blink(delay, duration);
	}

	@Override
	public Future<?> pulse(long duration) {
		return ledComponent.pulse(duration);
	}

	@Override
	public Future<?> pulse(long duration, boolean blocking) {
		return ledComponent.pulse(duration, blocking);
	}
}
