package helpdesk.services;

import helpdesk.data.Sample;

import java.io.IOException;
import java.util.List;

public interface SamplesLoader {
    List<Sample> load() throws IOException, IllegalArgumentException;
}
