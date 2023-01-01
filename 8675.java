public interface UIFrameworkObserver {

    public void applicationLoadingStarted(String applicationName, boolean fromServer);

    public void applicationLoadingFinished(String applicationName);
}
