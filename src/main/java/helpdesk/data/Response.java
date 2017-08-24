package helpdesk.data;

public class Response {
    private final String response;
    private final double confidence;

    public Response(String response, double confidence) {
        this.response = response;
        this.confidence = confidence;
    }

    public String getResponse() {
        return response;
    }

    public double getConfidence() {
        return confidence;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response='" + response + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
