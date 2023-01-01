import com.jcraft.jsch.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class sshCommand {

    public static void main(String[] arg) {
        try {
            if (arg.length < 4) {
                System.out.println("usage: java sshCommand user pass host command arg1 arg2 arg...");
                System.exit(-1);
            }
            JSch jsch = new JSch();
            String user = arg[0];
            String pass = arg[1];
            String host = arg[2];
            StringBuffer c = new StringBuffer(arg[3]);
            for (int a = 4; a < arg.length; a++) {
                c.append(" ");
                c.append(arg[a]);
            }
            String command = c.toString();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
