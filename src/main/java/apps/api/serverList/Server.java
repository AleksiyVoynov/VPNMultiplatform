package apps.api.serverList;

public class Server {
    private String country;
    private String country_name;
    private String alias_name;
    private String host;
    private String password;
    private int load;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryName() {
        return country_name;
    }

    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    public String getAliasName() {
        return alias_name;
    }

    public void setAliasName(String alias_name) {
        this.alias_name = alias_name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}

