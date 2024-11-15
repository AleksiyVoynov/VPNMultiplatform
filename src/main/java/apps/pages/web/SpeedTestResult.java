package apps.pages.web;

public class SpeedTestResult {

    public Double mbpsDownload;
    public Double mbpsUpload;
    public String latency;
    public String server;

    public SpeedTestResult(String mbpsDownload, String mbpsUpload, String latency, String server) {
        this.mbpsDownload = Double.valueOf(mbpsDownload);
        this.mbpsUpload = Double.valueOf(mbpsUpload);
        this.latency = latency;
        this.server = server;
    }

    @Override
    public String toString() {
        return "SpeedTestResult{" +
                "mbpsDownload='" + mbpsDownload + '\'' +
                ", mbpsUpload='" + mbpsUpload + '\'' +
                ", latency='" + latency + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
