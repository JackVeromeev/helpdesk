package helpdesk.services.impl;

import helpdesk.data.Sample;
import helpdesk.services.SamplesLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextSamplesLoader implements SamplesLoader {

    protected abstract InputStream inputStream();

    @Override
    public List<Sample> load() throws IOException              // File is not available
                                    , IllegalArgumentException // File is empty or has odd number of rows
    {
        final List<String> lines = IOUtils.readLines( inputStream(), "UTF-8");
        checkFileFormat(lines);

        return linesToSamples(lines);
    }

    private List<Sample> linesToSamples(List<String> lines) {
        final List<Sample> sampleList = new ArrayList<>(lines.size() / 2);

        for (int i = 0; i < lines.size(); i += 2) {
            sampleList.add( new Sample( lines.get(i)
                                      , lines.get(i+1))
                          );
        }

        return sampleList;
    }

    private void checkFileFormat(List<String> lines) throws IllegalArgumentException {
        if ( lines == null ||
             lines.size() == 0 ||
             lines.size() % 2 > 0 ) {

            throw new IllegalArgumentException();

        }
    }
}
