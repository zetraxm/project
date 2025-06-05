/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.de.hanoi;

import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Mouad
 */
import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private JPanel p1;
    private JComboBox<String> cb1;
    private JLabel lblMoviments;
    private Model m;

    public Vista() {
        setLayout(new BorderLayout());

        m = new Model();
        add(m, BorderLayout.CENTER);

        p1 = new JPanel(new FlowLayout());

        cb1 = new JComboBox<>();
        cb1.addItem("5");
        cb1.addItem("6");
        cb1.addItem("7");
        cb1.addItem("8");
        p1.add(new JLabel("Discs:"));
        p1.add(cb1);

        lblMoviments = new JLabel("Moviments: 0");
        p1.add(lblMoviments);

        add(p1, BorderLayout.NORTH);
    }

    public Model getModelPanel() {
        return m;
    }

    public JComboBox<String> box1() {
        return cb1;
    }

    public JLabel getLabelMovimientos() {
        return lblMoviments;
    }

    public void iniciarVista() {
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

