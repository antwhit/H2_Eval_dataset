    public ColumnInsertDialog(KeyListener klisten) {
        Container cPanel = this.getContentPane();
        txt = new JTextField(40);
        txt.addKeyListener(klisten);
        txt.setText(" ");
        txt.selectAll();
        cPanel.add(txt);
        this.pack();
        this.setVisible(true);
    }
