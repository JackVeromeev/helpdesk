package helpdesk.data;

import helpdesk.data.abstr.HasQuestion;

import java.util.Arrays;

public class TfIdf<S extends HasQuestion> {
    private final double[] tfidf;
    private final S sample;

    public TfIdf(double[] tfidf, S sample) {
        this.tfidf = tfidf;
        this.sample = sample;
    }

    public double[] getTfidf() {
        return tfidf;
    }

    public S getSample() {
        return sample;
    }

    @Override
    public String toString() {
        return "TfIdf{" +
                "tfidf=" + Arrays.toString(tfidf) +
                ", sample=" + sample +
                '}';
    }
}
