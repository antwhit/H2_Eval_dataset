import java.io.*;
import java.net.*;
import java.util.*;

public abstract class Behaviour implements Serializable {

    protected String className = "Behaviour";

    protected Agent myAgent = null;

    protected byte[] code = null;

    public Behaviour() {
    }

    public Behaviour(Agent agent) {
        myAgent = agent;
    }

    public void setAgent(Agent agent) {
        myAgent = agent;
    }

    public Agent getAgent() {
        return myAgent;
    }

    public abstract void action();

    public abstract int getType();

    public byte[] getClassDataFromDisk() throws ClassNotFoundException {
        byte[] code = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(getClassFileName());
            int size = in.available();
            if (size == 0) throw new ClassNotFoundException(className);
            code = new byte[size];
            in.read(code);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException ex) {
            }
        }
        return code;
    }

    public void putClassToNetwork(Socket connect) {
        try {
            if (code == null) code = getClassDataFromDisk();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        DataOutputStream dout = null;
        try {
            dout = new DataOutputStream(connect.getOutputStream());
            dout.writeUTF(className);
            dout.writeInt(code.length);
            dout.write(code);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public byte[] getCode() {
        return code;
    }

    private String getClassFileName() {
        return className + ".class";
    }
}
