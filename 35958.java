import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;

public class _ParameterList implements EncodableList {

    public void encode(__Encoder encoder) {
        encoder.encodeint(getListSize());
        Enumeration e = getElements();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            if (obj instanceof Encodable) {
                ((Encodable) obj).encode(encoder);
            }
        }
    }

    public void decode(__Decoder decoder) {
        int nbr = decoder.decodeint();
        for (int i = 0; i < nbr; i++) {
            _Parameter parameter = new _Parameter();
            parameter.decode(decoder);
            put(parameter.getName(), parameter);
        }
    }

    private LinkedHashMap list = new LinkedHashMap((int) 16, (float) 0.75, true);

    public void put(String name, _Parameter parameter) {
        list.put(name, parameter);
    }

    public _Parameter getByIndex(int index) {
        int i = 0;
        for (Enumeration e = getElements(); e.hasMoreElements(); ) {
            Object o = e.nextElement();
            if (i == index) return (_Parameter) o;
            i++;
        }
        return null;
    }

    public void remove(String name) {
        list.remove(name);
    }

    public int getListSize() {
        return list.size();
    }

    public Enumeration getElements() {
        Vector tmp = new Vector(list.values());
        return tmp.elements();
    }
}
