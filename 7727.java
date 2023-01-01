import java.beans.*;

public class JDBCConnectBeanInfo extends SimpleBeanInfo {

    private static BeanDescriptor beanDescriptor = null;

    private static BeanDescriptor getBdescriptor() {
        beanDescriptor = new BeanDescriptor(JDBCConnect.class, JDBCConnectCustomizer.class);
        return beanDescriptor;
    }

    private static PropertyDescriptor[] properties = null;

    private static PropertyDescriptor[] getPdescriptor() {
        return properties;
    }

    private static EventSetDescriptor[] eventSets = null;

    private static EventSetDescriptor[] getEdescriptor() {
        return eventSets;
    }

    private static MethodDescriptor[] methods = null;

    private static MethodDescriptor[] getMdescriptor() {
        return methods;
    }

    private static java.awt.Image iconColor16 = null;

    private static java.awt.Image iconColor32 = null;

    private static java.awt.Image iconMono16 = null;

    private static java.awt.Image iconMono32 = null;

    private static String iconNameC16 = null;

    private static String iconNameC32 = null;

    private static String iconNameM16 = null;

    private static String iconNameM32 = null;

    private static int defaultPropertyIndex = -1;

    private static int defaultEventIndex = -1;

    /**
   * Gets the bean's <code>BeanDescriptor</code>s.
   *
   * @return BeanDescriptor describing the editable
   * properties of this bean.  May return null if the
   * information should be obtained by automatic analysis.
   */
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
   * This method returns an image object that can be used to
   * represent the bean in toolboxes, toolbars, etc.   Icon images
   * will typically be GIFs, but may in future include other formats.
   * <p>
   * Beans aren't required to provide icons and may return null from
   * this method.
   * <p>
   * There are four possible flavors of icons (16x16 color,
   * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
   * support a single icon we recommend supporting 16x16 color.
   * <p>
   * We recommend that icons have a "transparent" background
   * so they can be rendered onto an existing background.
   *
   * @param  iconKind  The kind of icon requested.  This should be
   *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32,
   *    ICON_MONO_16x16, or ICON_MONO_32x32.
   * @return  An image object representing the requested icon.  May
   *    return null if no suitable icon is available.
   */
    public java.awt.Image getIcon(int iconKind) {
        iconNameC32 = "icon32.gif";
        switch(iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) return null; else {
                    if (iconColor16 == null) iconColor16 = loadImage(iconNameC16);
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) return null; else {
                    if (iconColor32 == null) iconColor32 = loadImage(iconNameC32);
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) return null; else {
                    if (iconMono16 == null) iconMono16 = loadImage(iconNameM16);
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) return null; else {
                    if (iconMono32 == null) iconMono32 = loadImage(iconNameM32);
                    return iconMono32;
                }
            default:
                return null;
        }
    }
}
