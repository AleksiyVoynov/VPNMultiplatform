package apps.multiplatform.pages.serverList;

public class Server {

    public String cluster;
    public String name;

    public Server(String cluster, String name) {
        this.cluster = cluster;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Server{" +
                "cluster='" + cluster + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
