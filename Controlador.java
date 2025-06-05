/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.de.hanoi;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Mouad
 */

public class Controlador {
    private Vista v;
    private Model m;

    public Controlador(Vista v, Model m) {
        this.v = v;
        this.m = m;

        // Cambiar número de discos
        v.box1().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int num = Integer.parseInt((String) v.box1().getSelectedItem());
                v.getModelPanel().setNumDiscos(num);
                v.getLabelMovimientos().setText("Movimientos: 0");
            }
        });

        // Un solo MouseAdapter para todo
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int torre = m.getTorreDesdeX(e.getX());
                m.iniciarArrossegament(torre, e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                m.actualitzarPosicioRato(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int torreDestino = m.getTorreDesdeX(e.getX());
                int antes = m.getMoviments();
                m.deixarDisc(torreDestino);
                int despues = m.getMoviments();

                if (despues != antes) {
                    v.getLabelMovimientos().setText("Moviments: " + despues);
                }
            }

        };

        v.getModelPanel().addMouseListener(mouseHandler);
        v.getModelPanel().addMouseMotionListener(mouseHandler);
    }

    public static void main(String[] args) {
        Vista v = new Vista();
        Model m = v.getModelPanel();
        Controlador c = new Controlador(v, m);
        v.iniciarVista();
    }
}

