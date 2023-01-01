import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpLoginException;

public class FunFtp {

    public FunFtp() {
        new Gui();
    }

    public static void main(String[] args) {
        new FunFtp();
    }
}
