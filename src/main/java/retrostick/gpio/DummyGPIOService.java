/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrostick.gpio;

import retrostick.event.JoystickEvent;

/**
 *
 * @author jungj
 */
public class DummyGPIOService implements GPIOService {

    @Override
    public void execute(JoystickEvent event) {
        System.out.println("Button: " + event.getKey() + "; State: " + event.getState());
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down");
    }
    
}
