package helpdesk.services.impl;

import java.io.InputStream;

public class ResourceSamplesLoaderImpl extends AbstractTextSamplesLoader {
    private final String resourceName;

    public ResourceSamplesLoaderImpl(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceSamplesLoaderImpl() {
        resourceName = "/data.txt";
    }

    @Override
    protected InputStream inputStream() {
        return this.getClass().getResourceAsStream( resourceName );
    }
}
