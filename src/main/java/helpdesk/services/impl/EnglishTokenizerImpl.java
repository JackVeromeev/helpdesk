package helpdesk.services.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import helpdesk.services.Tokenizer;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class EnglishTokenizerImpl implements Tokenizer {
    private final TokenizerME tokenizerME;
    private final opennlp.tools.stemmer.Stemmer stemmer;

    public EnglishTokenizerImpl() throws IOException {
        this.tokenizerME = new TokenizerME(new TokenizerModel(this.getClass().getResourceAsStream("/models/en-token.bin")));
        this.stemmer = new PorterStemmer();
    }

    @Override
    public String[] tokenize(String str) {
    	return Arrays.stream(tokenizerME.tokenize(str))
			         .map( token -> stemmer.stem(token).toString() )
			         .filter( this::removeMarks )
			         .map(String::toLowerCase)
			         .collect( Collectors.toList() )
			         .toArray( new String[0] );	    	
    }
    
    protected boolean removeMarks(String s) {
        return !(  s.contains(".")
                || s.contains(",")
                || s.contains(";")
                || s.contains("!")
                || s.contains("?")
                || s.contains("/")
                || s.contains("\\")
                || s.contains("(")
                || s.contains(")")
                );
    }

}
