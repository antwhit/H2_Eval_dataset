import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;

public class _Interface implements Encodable {

    public _Interface() {
    }

    private String name = null;

    private _MethodList methods = new _MethodList();

    public void encode(__Encoder encoder) {
        encoder.encodeString(getName());
        getMethods().encode(encoder);
    }

    public void decode(__Decoder decoder) {
        setName(decoder.decodeString());
        _MethodList methods = new _MethodList();
        methods.decode(decoder);
        setMethods(methods);
    }

    public String getName() {
        return name;
    }

    public _MethodList getMethods() {
        return methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethods(_MethodList methods) {
        this.methods = methods;
    }
}
