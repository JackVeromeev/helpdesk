package helpdesk.assembly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import helpdesk.data.Request;
import helpdesk.data.Response;
import helpdesk.data.Sample;
import helpdesk.data.TfIdf;
import helpdesk.data.TrainedData;
import helpdesk.services.Measure;
import helpdesk.services.Tokenizer;
import helpdesk.services.impl.TfIdfComputerImpl;

public class Chatting {
    private final TrainedData trainedData;
    private final Tokenizer tokenizer;
    private final TfIdfComputerImpl tfIdfComputer;
    private final Measure measure;
    
    public Chatting(TrainedData trainedData, Tokenizer tokenizer, Measure measure) {
        this.trainedData = trainedData;
        this.tokenizer = tokenizer;
        this.tfIdfComputer = new TfIdfComputerImpl();
        this.measure = measure;
    }

    public Response findResponse(String question) {
        /* 1. Tokenize and stem the question
         * 2. Having a supervector -- create question invariant
         * 3. Having trained invariants -- find similar
         * 4. Return best match
         */
    	final Request request = new Request(question, tokenizer.tokenize(question));
    	final double[] tfidf = tfIdfComputer.computeTfIdf(request, trainedData.getSamples(), trainedData.getTokensVector());
    	
    	final List<Pair<Double, Sample>> pairsList = new ArrayList<>(trainedData.getSamples().size());
    	
    	for (TfIdf<Sample> ti: trainedData.getData()) {
    		pairsList.add(Pair.of(measure.measure(tfidf, ti.getTfidf()), ti.getSample()));
    	}

    	Collections.sort(pairsList, new Comparator<Pair<Double, Sample>>() {
			@Override
			public int compare(Pair<Double, Sample> arg0, Pair<Double, Sample> arg1) {
				return (int) (1000.0*arg1.getLeft() - 1000.0 * arg0.getLeft());
			}});
    	
        return new Response(pairsList.get(0).getRight().toString(), pairsList.get(0).getLeft());
    }
}
