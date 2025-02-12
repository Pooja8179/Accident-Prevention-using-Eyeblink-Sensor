import java.util.concurrent.TimeUnit;

public class StepperMotorSimulation {
    private static final int STEPS_PER_REV = 200;
    private static final int BZ = 7;
    private static final int INDICATOR_LED = 6;
    private static int ebs = 0; // Simulating sensor input
    private static int i = 0;

    public static void main(String[] args) {
        setup();
        for (int j = 0; j < 20; j++) { // Simulating multiple loop iterations
            loop();
        }
    }

    private static void setup() {
        System.out.println("WELCOME");
        delay(2000);
        System.out.println("System Initialized...");
    }

    private static void loop() {
        stepperStep(50); // Simulating stepper motor movement

        int ebval = readSensor();
        System.out.println("Sensor Value: " + ebval);
        System.out.println("Counter: " + i);

        System.out.println("E: " + i);
        delay(500);

        // Simulating ebs sensor logic
        if (ebval == 0) {
            i++;
        } else {
            i = 0;
        }

        delay(100);

        // Buzzer activation logic
        if (i == 2 || i == 4) {
            buzzer(true);
            delay(300);
            buzzer(false);
        }

        // If counter exceeds 6, stop everything
        if (i > 6) {
            stepperStep(0); // Stop stepper motor
            buzzer(true);
            System.out.println("System Halted!");
            return; // Exit the loop
        }

        // Blink indicator LED every 2 seconds
        blinkLED();
    }

    private static void stepperStep(int steps) {
        if (steps > 0) {
            System.out.println("Stepper Motor Moving: " + steps + " steps");
        } else {
            System.out.println("Stepper Motor Stopped");
        }
    }

    private static int readSensor() {
        return (Math.random() < 0.5) ? 0 : 1; // Simulated sensor values (0 or 1)
    }

    private static void buzzer(boolean state) {
        if (state) {
            System.out.println("Buzzer ON");
        } else {
            System.out.println("Buzzer OFF");
        }
    }

    private static void blinkLED() {
        System.out.println("LED ON");
        delay(1000);
        System.out.println("LED OFF");
        delay(1000);
    }

    private static void delay(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
