import java.util.ArrayList;
import java.util.Collections;
public class Wave {
    ArrayList<Float> myFloats = null;
    float totalDuration;

    Wave() {
        this.myFloats = new ArrayList<Float>();
        this.totalDuration = 0.0f;
    }

    public void addNote(Note myNote) {
        this.myFloats.addAll(MyUtil.generatePulse(myNote.mySemitone.getHz(), myNote.myBeat.getDuration()));
        this.totalDuration += myNote.myBeat.getDuration();
    }

    public void play(String Semitone, int Beat) {
        this.addNote(new Note(Semitone, Beat));
    }

    public void rest() {
        this.addNote(new Note(0.0f, 0.20f));
    }

    public void merge(Wave w2) {
        for (int rep = 0; rep < w2.myFloats.size(); ++rep)
            if (rep < this.myFloats.size())
                this.myFloats.set(rep, (this.myFloats.get(rep) + w2.myFloats.get(rep)) / 2);
            else
                this.myFloats.add(w2.myFloats.get(rep));

        this.totalDuration = Math.max(this.totalDuration, w2.totalDuration);
    }

    public void concat(Wave w2) {
        for (int rep = 0; rep < w2.myFloats.size(); ++rep)
            this.myFloats.add(w2.myFloats.get(rep));
        this.totalDuration += w2.totalDuration;
    }

    public void reverse() {
        Collections.reverse(this.myFloats);
    }

    public ArrayList<Float> getFloats() {
        return this.myFloats;
    }

    public float getTotalDuration() {
        return this.totalDuration;
    }
}
