package helpdesk.services;

import java.util.List;

import helpdesk.data.abstr.TokenContainer;

public interface TfIdfComputer {
	double[] computeTfIdf(TokenContainer sample, List<? extends TokenContainer> containersList, String[] superVector);
}
