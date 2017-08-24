package helpdesk.data.abstr;

public interface TokenContainer {
	boolean containsToken(String token);
	long tokenCount(String token);
	String[] getTokens();
}
