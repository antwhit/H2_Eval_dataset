/**
 * Simple management interface for CoW to control Sventon.
 */
public interface CowSventonJMXControllerMBean {

    /**
   * Remove all known repositories from sventon.
   */
    void removeAllRepositories();

    /**
   * Reinitialize sventon.
   */
    void reInit();

    /**
   * Check whether the sventon management MBean is available.  Will either
   * return true or throw an exception, which should be caught by the caller.
   */
    boolean ping();
}
