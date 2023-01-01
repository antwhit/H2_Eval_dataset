import java.awt.Image;
import java.beans.BeanInfo;

/**
 * see above
 * 
 */
public class OfficeBeanInfo extends BeanInfoAdapter {

    /**
     * Constructor OfficeBeanInfo
     *
     */
    public OfficeBeanInfo() {
        addStandardRWPropertyDescriptor("DocumentURL", true, false);
        addStandardBooleanRWPropertyDescriptor("ReadOnly", true, false);
        addStandardBooleanRWPropertyDescriptor("ToolBarVisible", true, false);
        addStandardBooleanRWPropertyDescriptor("ObjectBarVisible", true, false);
        addStandardBooleanRWPropertyDescriptor("MenuBarVisible", true, false);
        addMethodDescriptors("load");
        addMethodDescriptors("save");
    }

    /**
	 * getBeanClass retrieve the class of the bean
	 * @return Class the Class of the Bean
	 */
    public Class getBeanClass() {
        return Office.class;
    }

    /**
	 * getDisplayName
	 * @return String the DisplayName as String
	 */
    public String getDisplayName() {
        return "Office Bean";
    }

    /**
	 * @param iconKind int the child of icon
	 * @return Image the requested Image
	 */
    public java.awt.Image getIcon(int iconKind) {
        if (iconKind == BeanInfo.ICON_COLOR_16x16) {
            Image img = loadImage("OfficeIconColor16.jpg");
            return img;
        }
        if (iconKind == BeanInfo.ICON_COLOR_32x32) {
            Image img = loadImage("OfficeIconColor32.jpg");
            return img;
        }
        if (iconKind == BeanInfo.ICON_MONO_16x16) {
            Image img = loadImage("OfficeIconMono16.jpg");
            return img;
        }
        if (iconKind == BeanInfo.ICON_MONO_32x32) {
            Image img = loadImage("OfficeIconMono32.jpg");
            return img;
        }
        return null;
    }
}
