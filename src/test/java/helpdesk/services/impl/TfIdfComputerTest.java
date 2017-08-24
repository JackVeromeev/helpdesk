package helpdesk.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import helpdesk.assembly.Training;
import helpdesk.data.Sample;
import helpdesk.data.TokenizedSample;


public class TfIdfComputerTest {
	private final TfIdfComputerImpl tfIdfComputer;
	private final EnglishTokenizerImpl tokenizer;
	private final Training training;
	
	public TfIdfComputerTest() throws Exception {
		tfIdfComputer = new TfIdfComputerImpl();
		tokenizer = new EnglishTokenizerImpl();
		training = new Training(new ResourceSamplesLoaderImpl(), tokenizer);
	}
	
	@Test
	public void tf() {
		final TokenizedSample sample = new TokenizedSample( new Sample("hello", "world")
													      , new String[]{"1", "2", "2", "3", "3", "3"}
													      );
		
		Assert.assertEquals( "{1=0.16666666666666666, 2=0.3333333333333333, 3=0.5}"
						   , tfIdfComputer.tf(sample).toString());
	}
	
	@Test
	public void idf() {
		final List<TokenizedSample> samples = samples();
		final String[] superVector = training.makeSuperVector(samples);
		
		Assert.assertEquals( "{hi=1.0986122886681098, hello=0.4054651081081644}"
						   , tfIdfComputer.idf(samples.get(0), samples, superVector).toString());
	}
	
	@Test
	public void tfidf() {
		final List<TokenizedSample> samples = samples();
		final String[] superVector = training.makeSuperVector(samples);
		
		Assert.assertEquals( "[0.3034130755422791, 0.0, 0.0, 1.6442023082509543]"
						   , Arrays.toString(tfIdfComputer.computeTfIdf(samples.get(0), samples, superVector)));
	}
	
	private List<TokenizedSample> samples() {
        final List<TokenizedSample> tokenizedSampleList = new ArrayList<>();
        
        final String str1 = "hello hi hello";
        tokenizedSampleList.add( new TokenizedSample( new Sample(str1, "ans1"), tokenizer.tokenize(str1))  );
        
        final String str2 = "hello howdy";
        tokenizedSampleList.add( new TokenizedSample( new Sample(str2, "ans2"), tokenizer.tokenize(str2))  );
        
        final String str3 = "howdy yep";
        tokenizedSampleList.add( new TokenizedSample( new Sample(str3, "ans3"), tokenizer.tokenize(str3))  );
        
        return tokenizedSampleList;
	}
}
