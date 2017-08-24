package helpdesk.data.abstr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTokensContainer implements TokenContainer {
	private final String[] tokens;
	private final Set<String> tokensSet;
	
	public AbstractTokensContainer(String[] tokens) {
		this.tokens = tokens;
		this.tokensSet = new HashSet<>(Arrays.asList(tokens));
	}

	@Override
	public String[] getTokens() {
		return tokens;
	}

    @Override
    public boolean containsToken(String token) {
        return tokensSet.contains(token);
    }

    public long tokenCount(String token) {
        return Arrays.stream( tokens )
                .filter( token::equals )
                .count();
    }

}
