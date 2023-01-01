public class mailRemover {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                mailRemoverGUI mrGUI = new mailRemoverGUI();
            }
        });
    }
}
