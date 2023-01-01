import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * edit soolly
 * vision：0.5
 * eclipse 插件端代码
 * 网络上的代码，完善了线程锁，现在接收数据流打印的时候顺序不会乱了
 * 一段时间以后计划做成eclipse插件
 * 代码发布在http://club.topsage.com/thread-2450808-1-1.html
 *
 * 该文件来源：《java编程思想(第2版)》,作者Bruce Eckel
 * 被修改了输出的几句话。，还有编码等 现成的php 的 eclipse 的控制台，
 *
 * 使php 在pdt环境下 本机调试更加方便 发表评论，请http://xieye.javaeye.com/ xieye 20081122
 */
class Console4PHPOperate extends Thread {

    private Socket socket;

    private BufferedReader in;

    private Console4PHPServe obj;

    public Console4PHPOperate(Socket s, Object o) throws IOException {
        socket = s;
        obj = (Console4PHPServe) o;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
            System.out.print(sdf.format(new Date()) + " ");
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    System.out.println("java:InputStream is null.");
                    str = Console4PHPServe.S_EOF;
                }
                if (Console4PHPServe.S_EOF.equals(str)) break;
                if (("\\" + Console4PHPServe.S_EOF).equals(str)) {
                    str = Console4PHPServe.S_EOF;
                }
                System.out.println(str);
            }
        } catch (IOException e) {
            System.err.println("== 客户端强行关闭 ==");
        } finally {
            try {
                socket.close();
                obj.waitForAll(true, null);
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}

public class Console4PHPServe {

    static final int PORT = 8281;

    static final String S_EOF = "END";

    public static void main(String[] args) throws IOException {
        Console4PHPServe c = new Console4PHPServe();
        c.waitForAll(false, c);
    }

    public synchronized void waitForAll(boolean notify, Object o) throws IOException {
        if (notify) {
            notifyAll();
        } else {
            ServerSocket s = null;
            try {
                s = new ServerSocket(PORT);
                System.out.println("PHP控制台启动。");
            } catch (Exception e) {
                System.out.println("端口" + PORT + "被占用！");
            }
            try {
                while (true) {
                    Socket socket = s.accept();
                    try {
                        new Console4PHPOperate(socket, o).start();
                        try {
                            wait();
                        } catch (Exception e) {
                        }
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            } finally {
                System.out.println("PHP控制台关闭。");
                s.close();
            }
        }
    }

    protected void finalize() {
        System.out.println("PHP控制台关闭。");
    }
}
