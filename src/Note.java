import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Note {
    Semitone mySemitone;
    Beat myBeat;

    Note(String semitoneName, int beatNumber) {
        this.mySemitone = new Semitone(Main.SEMITONES.get(semitoneName));
        this.myBeat = new Beat(Main.BEATS.get(beatNumber));
    }

    Note(int semitoneNumber, int beatNumber) {
        this.mySemitone = new Semitone(semitoneNumber);
        this.myBeat = new Beat(Main.BEATS.get(beatNumber));
    }

    Note(int semitoneNumber, float beat_i) {
        this.mySemitone = new Semitone(semitoneNumber);
        this.myBeat = new Beat(beat_i);
    }

    Note(float hz, float duration) {
        this.mySemitone = new Semitone(new Semitone.Frequency(hz));
        this.myBeat = new Beat(new Beat.Seconds(duration));
    }

    static class Semitone {
        static {
            try (BufferedReader myreader = new BufferedReader(new FileReader("SemitonesAndMidi.txt"))) {
                String s;
                while ((s = myreader.readLine()) != null) {
                    String[] data = s.split(",");
                    Main.SEMITONES.put(data[0], Integer.parseInt(data[1]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.MIDIS.addAll(Main.SEMITONES.values());
        }

        Frequency Hz;

        Semitone(int i) {
            this.Hz = new Frequency(MyUtil.f(i));
        }

        Semitone(Frequency myHz) {
            this.Hz = myHz;
        }

        public float getHz() {
            return this.Hz.getFrequency();
        }

        static class Frequency {
            float val;

            Frequency(float myVal) {
                this.val = myVal;
            }

            public float getFrequency() {
                return this.val;
            }
        }
    }

    static class Beat {
        static {
            Main.BEATS.add(0.25f);
            Main.BEATS.add(0.5f);
            Main.BEATS.add(0.5f);
            Main.BEATS.add(0.5f);
            Main.BEATS.add(0.5f);
            Main.BEATS.add(0.5f);
            Main.BEATS.add(1.0f);
        }

        Seconds duration;

        Beat(float myBeat_i) {
            this.duration = new Seconds(myBeat_i * 60.0f / Main.bpm);
        }

        Beat(Seconds myDuration) {
            this.duration = myDuration;
        }

        public float getDuration() {
            return this.duration.getSeconds();
        }

        static class Seconds {
            float val;

            Seconds(float dur) {
                this.val = dur;
            }

            public float getSeconds() {
                return this.val;
            }
        }

    }
}


