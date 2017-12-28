package retrostick.event;

import java.io.Serializable;

/**
 *
 * @author jungj
 */
public class JoystickEvent implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Key key;
    private State state;
    
    public JoystickEvent() {}
    
    public JoystickEvent(Key key, State state) {
        this.key = key;
        this.state = state;
    }

    public Key getKey() {
        return key;
    }

    public State getState() {
        return state;
    }
    
    public static enum Key {
        UP, DOWN, LEFT, RIGHT, BUTTON1, BUTTON2
    }
    
    public static enum State {
        PRESSED, RELEASED
    }
}
