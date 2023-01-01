public class InitialView extends AbstractView {

    /**
	 * Create the actual UI control for this view. It will be placed into the window according to the layout of
     *  the page holding this view.
	 */
    protected JComponent createControl() {
        JTextPane textPane = new JTextPane();
        JScrollPane spDescription = getComponentFactory().createScrollPane(textPane);
        try {
            textPane.setPage(getDescriptionTextPath().getURL());
        } catch (IOException e) {
            throw new RuntimeException("Unable to load description URL", e);
        }
        JLabel lblMessage = getComponentFactory().createLabel(getFirstMessage());
        lblMessage.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JPanel panel = getComponentFactory().createPanel(new BorderLayout());
        panel.add(spDescription);
        panel.add(lblMessage, BorderLayout.SOUTH);
        return panel;
    }
}
