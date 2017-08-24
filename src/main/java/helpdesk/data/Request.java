package helpdesk.data;

import java.util.Arrays;

import helpdesk.data.abstr.AbstractTokensContainer;
import helpdesk.data.abstr.HasQuestion;

public class Request extends AbstractTokensContainer implements HasQuestion {
    private final String question;

    public Request(String question, String[] tokens) {
    	super(tokens);
        this.question = question;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Request{" +
                "question='" + question + '\'' +
                ", tokens=" + Arrays.toString(getTokens()) +
                '}';
    }
}
