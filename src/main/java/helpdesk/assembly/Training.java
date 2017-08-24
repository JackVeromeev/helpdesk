package helpdesk.assembly;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import helpdesk.data.Sample;
import helpdesk.data.TfIdf;
import helpdesk.data.TokenizedSample;
import helpdesk.data.TrainedData;
import helpdesk.services.SamplesLoader;
import helpdesk.services.TfIdfComputer;
import helpdesk.services.Tokenizer;
import helpdesk.services.impl.TfIdfComputerImpl;

public class Training {
    private final SamplesLoader samplesLoader;
    private final Tokenizer tokenizer;
    private final TfIdfComputer tfIdfComputer;

    public Training(SamplesLoader samplesLoader, Tokenizer tokenizer) {
        this.samplesLoader = samplesLoader;
        this.tokenizer = tokenizer;
        this.tfIdfComputer = new TfIdfComputerImpl();
    }

    public TrainedData doTraining() throws IOException {
        final List<TokenizedSample> tokenizedSamplesList =
                samplesLoader.load()
                             .stream()
                             .map( sample -> new TokenizedSample(sample, tokenizer.tokenize(sample.getQuestion())) )
                             .collect(Collectors.toList());

        final String[] superVector = makeSuperVector(tokenizedSamplesList);

	    final List<TfIdf<Sample>> tfIdfList = tokenizedSamplesList
	    										.stream()
	        				    				.map( sample -> makeTfIdf(sample, tokenizedSamplesList, superVector) )
	        				    				.collect(Collectors.toList());
        
        return new TrainedData(tfIdfList, tokenizedSamplesList, superVector);        
    }

    protected TfIdf<Sample> makeTfIdf( TokenizedSample sample, List<TokenizedSample> tokenizedSamplesList, String[] superVector ) {
		return new TfIdf<Sample>(tfIdfComputer.computeTfIdf(sample, tokenizedSamplesList,  superVector), sample.getSample());
	}

	public String[] makeSuperVector(List<TokenizedSample> tokenizedSamplesList) {
        final Set<String> tokenSet = new HashSet<>();

        for (TokenizedSample tokenizedSample: tokenizedSamplesList) {
            tokenSet.addAll(Arrays.asList(tokenizedSample.getTokens()));
        }

        return tokenSet.toArray(new String[0]);
    }


}
