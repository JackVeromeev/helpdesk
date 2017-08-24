package helpdesk.data;

import java.util.Arrays;

import helpdesk.data.abstr.AbstractTokensContainer;

public class TokenizedSample extends AbstractTokensContainer {
    private final Sample sample;   

    public TokenizedSample(Sample sample, String[] tokens) {
    	super(tokens);
        this.sample = sample;
    }

    public Sample getSample() {
        return sample;
    }

    @Override
    public String toString() {
        return "TokenizedSample{" +
                "sample=" + sample +
                ", tokens=" + Arrays.toString(getTokens()) +
                '}';
    }
}
