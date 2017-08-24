package helpdesk.data;

import java.util.Arrays;
import java.util.List;

public class TrainedData {
    private final List<TfIdf<Sample>> data;
    private final List<TokenizedSample> samples;
	private final String[] tokensVector;
	
	public List<TfIdf<Sample>> getData() {
		return data;
	}

	public List<TokenizedSample> getSamples() {
		return samples;
	}
	
	public String[] getTokensVector() {
		return tokensVector;
	}

    public TrainedData(List<TfIdf<Sample>> data, List<TokenizedSample> samples, String[] tokensVector) {
        this.data = data;
        this.tokensVector = tokensVector;
        this.samples = samples;
    }

	@Override
	public String toString() {
		return "TrainedData [data=" + data + ", samples=" + samples + ", tokensVector=" + Arrays.toString(tokensVector)
				+ "]";
	}

    
}
