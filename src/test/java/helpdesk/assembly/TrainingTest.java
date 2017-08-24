package helpdesk.assembly;

import helpdesk.data.Sample;
import helpdesk.data.TokenizedSample;
import helpdesk.services.impl.EnglishTokenizerImpl;
import helpdesk.services.impl.ResourceSamplesLoaderImpl;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class TrainingTest {
	private final EnglishTokenizerImpl tokenizer;

	public TrainingTest() throws Exception {
		tokenizer = new EnglishTokenizerImpl();
	}
	
    @Test
    public void makeSuperVector() throws IOException {
        final Training training = new Training( new ResourceSamplesLoaderImpl()
                                              , new EnglishTokenizerImpl()
                                              );

        final List<TokenizedSample> tokenizedSampleList = samples();

        final String[] superVector = training.makeSuperVector(tokenizedSampleList);
        final Set<String> result = new HashSet<>(Arrays.asList(superVector));

        Assert.assertEquals(4, result.size());
        assert(result.contains("howdi"));
        assert(result.contains("hello"));
        assert(result.contains("yep"));
        assert(result.contains("hi"));
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
