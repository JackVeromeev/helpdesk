package helpdesk.services;

import helpdesk.data.Sample;
import helpdesk.services.impl.ResourceSamplesLoaderImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

@RunWith(JUnit4.class)
public class SamplesLoaderTest {

    @Test
    public void load() throws IOException {
        final List<Sample> sampleList = new ResourceSamplesLoaderImpl().load();
        Assert.assertNotNull(sampleList);
        Assert.assertEquals(18, sampleList.size());
    }
}
