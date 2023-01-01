import net.llando.LazyObjectLoader;
import net.llando.LlandoClass;
import net.llando.LlandoMethod;
import net.llando.LlandoObject;
import net.llando.LlandoObjectInfo;

@LlandoClass(tag = "net.llando.test.LazyObjectService")
public class LazyObjectService implements LlandoObject, LazyObjectLoader {

    @LlandoMethod(name = "get_test_object")
    public LazyTestObject getTestObject() {
        return new LazyTestObject();
    }

    LlandoObjectInfo info;

    public LlandoObjectInfo getLlandoObjectInfo() {
        return info;
    }

    public void setLlandoObjectInfo(LlandoObjectInfo info) {
        this.info = info;
    }

    public LlandoObject loadObject(LlandoObjectInfo info) {
        LazyTestObject result = new LazyTestObject();
        result.setLlandoObjectInfo(info);
        return result;
    }
}
