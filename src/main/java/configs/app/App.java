package configs.app;

public abstract class App {
    public String bundleId;
    public String appPackage;
    public String appActivity;
    public String name;
    public String webView;

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}
