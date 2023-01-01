    public ColumnInsertDialog(KeyListener listener) {
        Container cPane = this.getContentPane();
        text = new JTextField(40);
        text.addKeyListener(listener);
        text.setText(" ");
        text.selectAll();
        cPane.add(text);
        this.pack();
        this.setVisible(true);
    }