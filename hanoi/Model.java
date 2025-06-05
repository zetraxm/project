/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.de.hanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Mouad
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model extends JPanel {
    private List<Integer>[] torres;
    private int numDiscos = 5;
    private Integer discArrossegant = null;
    private Point posicioRato = null;
    private int torreOrigen = -1;
    private int moviments = 0;

    public Model() {
        inicialitzarTorres();
    }

    public void inicialitzarTorres() {
        torres = new List[3];
        for (int i = 0; i < 3; i++) {
            torres[i] = new ArrayList<>();
        }
        torres[0].clear();
        for (int i = numDiscos; i >= 1; i--) {
            torres[0].add(i);
        }
        repaint();
    }

    public void setNumDiscos(int n) {
        this.numDiscos = n;
        reiniciarMoviments();
        inicialitzarTorres();
    }

    public int getTorreDesdeX(int x) {
        int ample = getWidth() / 3;
        return x / ample;
    }

    public boolean potMoure(int torre) {
        return !torres[torre].isEmpty();
    }

    public int obtenirDiscSuperior(int torre) {
        return torres[torre].get(torres[torre].size() - 1);
    }

    public boolean potDeixar(int torreDesti, int disc) {
        if (torres[torreDesti].isEmpty()){
            return true;
        }
        return torres[torreDesti].get(torres[torreDesti].size() - 1) > disc;
    }

    public void deixarDisc(int torreDesti) {
        if (discArrossegant != null && torreOrigen != -1 && potDeixar(torreDesti, discArrossegant)) {
            torres[torreOrigen].remove(torres[torreOrigen].size() - 1);
            torres[torreDesti].add(discArrossegant);
            moviments++;

            if (torres[2].size() == numDiscos) {
                JOptionPane.showMessageDialog(this,
                    "Felicitats! Has completat el joc en " + moviments + " moviments.",
                    "Joc acabat",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            if (torres[1].size() == numDiscos) {
                JOptionPane.showMessageDialog(this,
                    "Felicitats! Has completat el joc en " + moviments + " moviments.",
                    "Joc acabat",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }

        discArrossegant = null;
        posicioRato = null;
        torreOrigen = -1;
        repaint();
    }

    public void iniciarArrossegament(int torre, Point puntRatoli) {
        if (potMoure(torre)) {
            torreOrigen = torre;
            discArrossegant = obtenirDiscSuperior(torre);
            posicioRato = puntRatoli;
            repaint();
        }
    }

    public void actualitzarPosicioRato(Point p) {
        posicioRato = p;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ample = getWidth() / 3;
        int baseY = getHeight() - 50;
        int alturaTorre = 200;
        int ampleBase = 10;
        int altDisc = 20;

        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < 3; i++) {
            int xCentre = ample * i + ample / 2;
            g2.setColor(new Color(139, 69, 19));
            g2.fillRect(xCentre - ampleBase / 2, baseY - alturaTorre, ampleBase, alturaTorre);

            List<Integer> torre = torres[i];
            int y = baseY - altDisc;

            for (int j = 0; j < torre.size(); j++) {
                int disc = torre.get(j);
                int ampleDisc = 30 + disc * 20;
                g2.setColor(new Color(100, 100, 255, 180));
                g2.fillRect(xCentre - ampleDisc / 2, y, ampleDisc, altDisc);
                g2.setColor(Color.BLUE);
                g2.drawRect(xCentre - ampleDisc / 2, y, ampleDisc, altDisc);
                y -= altDisc;
            }
        }

        if (discArrossegant != null && posicioRato != null) {
            int ampleDisc = 30 + discArrossegant * 20;
            g2.setColor(new Color(255, 100, 100, 200));
            g2.fillRect(posicioRato.x - ampleDisc / 2, posicioRato.y - altDisc / 2, ampleDisc, altDisc);
            g2.setColor(Color.RED);
            g2.drawRect(posicioRato.x - ampleDisc / 2, posicioRato.y - altDisc / 2, ampleDisc, altDisc);
        }
    }

    public int getNumDiscos() {
        return numDiscos;
    }

    public int getMoviments() {
        return moviments;
    }

    public void reiniciarMoviments() {
        moviments = 0;
    }
}

