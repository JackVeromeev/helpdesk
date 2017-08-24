package helpdesk.services.impl;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import helpdesk.services.Tokenizer;

@RunWith(JUnit4.class)
public class EnglishTokenizerTest {

	@Test
	public void tokenize() throws IOException {
		final Tokenizer tokenizer = new EnglishTokenizerImpl();
		
		final String[] result = tokenizer.tokenize("hello, world! what are you reading?");
		assert(Arrays.toString(result).equals("[hello, world, what, ar, you, read]"));
	}
	
}
