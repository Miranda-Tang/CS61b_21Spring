package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int KEYS_AMOUNT = KEYBOARD.length();

    public static void main(String[] args) {
        GuitarString[] keyObjects = new GuitarString[KEYS_AMOUNT];
        for (int i = 0; i < KEYS_AMOUNT; i++) {
            keyObjects[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index >= 0) {
                    keyObjects[index].pluck();
                }
            }

            double sample = 0;
            for (GuitarString gs : keyObjects) {
                sample += gs.sample();
            }
            StdAudio.play(sample);
            for (GuitarString gs : keyObjects) {
                gs.tic();
            }
        }
    }
}
