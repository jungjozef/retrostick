package retrostick.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrostick.gpio.GPIOService;

/**
 *
 * @author jungj
 */
public class EventBus {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final GPIOService gpioService;
    
    
    public EventBus() {
        gpioService = GPIOService.Factory.createAccordingToPlatform();
    }
    
    public void post(JoystickEvent joystickEvent) {
        executorService.submit(() -> {
            gpioService.execute(joystickEvent);
        });
    }
    
    public void shutdown() {
        gpioService.shutdown();
    }
    
}
