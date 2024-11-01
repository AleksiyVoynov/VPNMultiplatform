package apps.pages.multiplatform.serverList;

public class Server {

    public int clusterIndex;
    public String cluster;
    public String name;

    public Server() {
    }

    public Server(int clusterIndex, String cluster, String name) {
        this.cluster = cluster;
        this.name = name;
        this.clusterIndex = clusterIndex;
    }

    @Override
    public String toString() {
        return "Server{" +
                "cluster='" + cluster + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
