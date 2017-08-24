package helpdesk;

import helpdesk.assembly.Chatting;
import helpdesk.assembly.Training;
import helpdesk.data.Response;
import helpdesk.data.TrainedData;
import helpdesk.services.impl.CosineMeasureImpl;
import helpdesk.services.impl.EnglishTokenizerImpl;
import helpdesk.services.impl.ResourceSamplesLoaderImpl;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {

        System.out.println("Started training...");
        final TrainedData trainedData = new Training( new ResourceSamplesLoaderImpl()
                                                    , new EnglishTokenizerImpl()
                                                    ).doTraining();
        System.out.println("Done training!");

        final Chatting chatting = new Chatting(trainedData, new EnglishTokenizerImpl(), new CosineMeasureImpl());

        final String question = "what book?";
        
        final Response response = chatting.findResponse(question);
        System.out.println(response.getResponse());
        System.out.println("[Confidence: " + response.getConfidence() + "]");
    
    }
}
