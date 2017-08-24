package helpdesk.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helpdesk.data.abstr.TokenContainer;
import helpdesk.services.TfIdfComputer;

public class TfIdfComputerImpl implements TfIdfComputer {

	@Override
	public double[] computeTfIdf(TokenContainer sample, List<? extends TokenContainer> containersList, String[] superVector) {
		final Map<String, Double> tfsMap = tf(sample);
		final Map<String, Double> idfsMap = idf(sample, containersList, superVector);
		
		return makeTfIdfVector(tfsMap, idfsMap, superVector);
	}
	
	protected double[] makeTfIdfVector(Map<String, Double> tfsMap, Map<String, Double> idfsMap, String[] superVector) {
		final double[] tfidf = new double[superVector.length];

        int i = 0;
        for (String token: superVector) {
            final Double tf = tfsMap.get(token);
            final Double idf = idfsMap.get(token);

            if (tf != null && idf != null && idf != 0) {
                tfidf[i] = tf / idf;
            } else {
                tfidf[i] = 0;
            }

            i++;
        }

        return tfidf;
	}

	protected Map<String, Double> idf(TokenContainer sample, List<? extends TokenContainer> containersList, String[] superVector) {
		final Map<String, Double> result = new HashMap<>();

        for (String token: sample.getTokens()) {
            final long sameTokensAll = containersList
                                                .stream()
                                                .filter( container -> container.containsToken(token) )
                                                .count();

            result.put(token, sameTokensAll == 0
            					? 0
            					: Math.log((double) containersList.size() / sameTokensAll ) 
            		   );
        }
        
        return result;
	}
	
	protected Map<String, Double> tf(TokenContainer sample) {
		final Map<String, Double> result = new HashMap<>();

        for (String token: sample.getTokens()) {
            result.put( token
                      , (double) sample.tokenCount(token) / sample.getTokens().length
                      );
        }
        
        return result;
	}

}
