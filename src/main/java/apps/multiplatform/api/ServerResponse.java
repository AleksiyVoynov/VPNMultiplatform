package apps.multiplatform.api;

import java.util.List;

public class ServerResponse {
    private List<Server> servers;
    private List<Server> vip_servers;

    // Getters и Setters
    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<Server> getVipServers() {
        return vip_servers;
    }

    public void setVipServers(List<Server> vip_servers) {
        this.vip_servers = vip_servers;
    }
}

