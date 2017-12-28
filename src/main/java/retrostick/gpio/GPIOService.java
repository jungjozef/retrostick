package retrostick.gpio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import retrostick.event.JoystickEvent;

/**
 *
 * @author jungj
 */
public interface GPIOService {
    void execute(JoystickEvent event);
    void shutdown();
    
    public static class Factory {
        private static boolean isPiUnix = false;
        static {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux")) {
                final File file = new File("/etc", "os-release");
                try (FileInputStream fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                    String string;
                    while ((string = br.readLine()) != null) {
                        if (string.toLowerCase().contains("raspbian")) {
                            if (string.toLowerCase().contains("name")) {
                                isPiUnix = true;
                                break;
                            }
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public static GPIOService createAccordingToPlatform() {
            return isPiUnix ? new RpiGPIOService() : new DummyGPIOService();
        }
    }
}
