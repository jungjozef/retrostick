package retrostick.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import retrostick.event.JoystickEvent;

/**
 *
 * @author jungj
 */
public class RpiGPIOService implements GPIOService {
    
    private final GpioController gpio;
    private final GpioPinDigitalOutput up;
    private final GpioPinDigitalOutput down;
    private final GpioPinDigitalOutput left;
    private final GpioPinDigitalOutput right;
    private final GpioPinDigitalOutput btn1;
    private final GpioPinDigitalOutput btn2;

    public RpiGPIOService() {
        gpio = GpioFactory.getInstance();
        up = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "UP", PinState.LOW);
        down = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "DOWN", PinState.LOW);
        left = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LEFT", PinState.LOW);
        right = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "RIGHT", PinState.LOW);
        btn1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "BTN1", PinState.LOW);
        btn2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "BTN2", PinState.LOW);
        
        up.setShutdownOptions(Boolean.TRUE, PinState.LOW);
        down.setShutdownOptions(Boolean.TRUE, PinState.LOW);
        left.setShutdownOptions(Boolean.TRUE, PinState.LOW);
        right.setShutdownOptions(Boolean.TRUE, PinState.LOW);
        btn1.setShutdownOptions(Boolean.TRUE, PinState.LOW);
        btn2.setShutdownOptions(Boolean.TRUE, PinState.LOW);
    }
    
    @Override
    public void execute(JoystickEvent event) {
        switch (event.getKey()) {
            case UP:
                switchPin(up, event.getState());
                break;
            case DOWN:
                switchPin(down, event.getState());
                break;
            case LEFT:
                switchPin(left, event.getState());
                break;
            case RIGHT:
                switchPin(right, event.getState());
                break;
            case BUTTON1:
                switchPin(btn1, event.getState());
                break;
            case BUTTON2:
                switchPin(btn2, event.getState());
                break;
        }
    }
    
    private void switchPin(final GpioPinDigitalOutput pin, final JoystickEvent.State state) {
        switch (state) {
            case PRESSED:
                pin.high();
                break;
            case RELEASED:
                pin.low();
                break;
        }
    }
    
    @Override
    public void shutdown() {
        gpio.shutdown();
    }
}
