    public ColumnInsertDialog(KeyListener listener) {
        boolean b = false;
        Container cPanel = this.getContentPane();
        text = new JTextField(40);
        text.addKeyListener(listener);
        text.setText(" ");
        text.selectAll();
        cPanel.add(text);
        this.pack();
        b = true;
        this.setVisible(b);
    }
