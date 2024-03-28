package practicamatricesdispersas;

import java.awt.*;
import javax.swing.JOptionPane;

public class DispersaF1 {

    private int Mat[][];
    private int N, M;

    public DispersaF1(int n, int m) {
        M = 3;
        N = 2;
        Mat = new int[N][M];
        Mat[0][0] = n;
        Mat[0][1] = m;
        Mat[0][2] = 0;
        Mat[1][0] = n + 1;
        Mat[1][1] = m + 1;
    }

    public int obtenerDato(int f, int c) {
        return Mat[f][c];
    }

    public void pintar(Graphics g, int f, int c) {
        Color colorFondo = new Color(233, 243, 243);
        int saltoC = 40;
        int saltoF = 30;
        g.setColor(Color.blue);
        g.drawString("MAT", c - 20, f);
        f = f + 20;

        for (int i = 0; i < Mat.length; i++) // PARA RECORRER LAS FILAS DE LA MATRIZ
        {
            c = 50;
            g.setColor(Color.red);
            g.drawString("" + (i + 1), c - 20, f + 20);
            for (int j = 0; j < Mat[i].length; j++) // PARA RECORRER LAS COLUMNAS DE LA MATRIZ
            {
                if (i == 0 || i == Mat[0][2] + 1) {
                    g.setColor(Color.yellow);

                } else if (i > Mat[0][2] + 1) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.white);

                }
                g.fillRect(c, f, saltoC, saltoF);
                g.setColor(Color.blue);
                g.drawRect(c, f, saltoC, saltoF);
                g.setColor(Color.black);
                g.drawString("" + Mat[i][j], c + 10, f + 20);
                c = c + saltoC;
            }
            f = f + saltoF;

        }
    }

    public void redimensionar(int x) {
        int aux[][];
        if (x > 0) {
            aux = new int[N + x][M];
            for (int i1 = 0; i1 < N; i1++) {
                for (int j = 0; j < M; j++) {
                    aux[i1][j] = Mat[i1][j];
                }
            }
            N = N + x;
            Mat = aux;
        } else {
            if (N + x >= 2) {
                aux = new int[N + x][M];
                for (int i1 = 0; i1 < N + x; i1++) {
                    for (int j = 0; j < M; j++) {
                        aux[i1][j] = Mat[i1][j];
                    }
                }
                N = N + x;
                Mat = aux;
            }
        }

    }

    public void insetarDato(int f, int c, int d) {

        int i = 1, j;
        while (i < Mat[0][2] + 1 && Mat[i][0] < f) {
            i = i + 1;
        }
        while (i < Mat[0][2] + 1 && Mat[i][0] == f && Mat[i][1] < c) {

            i = i + 1;
        }
        if (i < Mat[0][2] + 1 && Mat[i][0] == f && Mat[i][1] == c) {
            Mat[i][2] = Mat[i][2] + d;
            if (Mat[i][2] == 0) {
                for (int k = i + 1; k < Mat[0][2] + 2; k++) {
                    Mat[k - 1][0] = Mat[k][0];
                    Mat[k - 1][1] = Mat[k][1];
                    Mat[k - 1][2] = Mat[k][2];
                }
                Mat[0][2] = Mat[0][2] - 1;

                redimensionar(- 1);
            }

        } else {
            if (Mat.length == Mat[0][2] + 2) {
                redimensionar(1);
            }
            for (int k = Mat[0][2] + 1; k >= i; k = k - 1) {
                Mat[k + 1][0] = Mat[k][0];
                Mat[k + 1][1] = Mat[k][1];
                Mat[k + 1][2] = Mat[k][2];
            }
            Mat[i][0] = f;
            Mat[i][1] = c;
            Mat[i][2] = d;
            Mat[0][2] = Mat[0][2] + 1;
        }

    }

    public void promedioPares(int f, int c, int d) {
        int i = 1, cont = 0, suma = 0;

        while (i < Mat[0][2] + 1) {
            Mat[i][2] = d;
            if (d % 2 == 0);
            {
                d = d + d;
                cont = cont + cont;

            }

        }
        suma = d / cont;
        System.out.println(suma);
    }

    public void mostrarMaxDigitosPares(Component parentComponent) {
        boolean[] filaP = new boolean[N]; 
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Datos con Mayor Cantidad de Dígitos Pares por Fila:\n");
        for (int i = 1; i <= Mat[0][2]; i++) {
            if (!filaP[Mat[i][0]]) { 
                int f = Mat[i][0];
                int c = Mat[i][1];
                int d = Mat[i][2];

                int numDigitosPares = contarDigitosPares(d);
                int columnaMax = c;

                for (int j = i + 1; j <= Mat[0][2]; j++) {
                    if (Mat[j][0] == f && contarDigitosPares(Mat[j][2]) > numDigitosPares) {
                        d = Mat[j][2];
                        numDigitosPares = contarDigitosPares(d);
                        columnaMax = Mat[j][1]; 

                    }
                }

                filaP[f] = true;
                mensaje.append("Fila: ").append(f).append(", Columna: ").append(columnaMax).append(", Dato: ").append(d).append("\n");

            }
        }
        JOptionPane.showMessageDialog(parentComponent, mensaje.toString(), "", JOptionPane.INFORMATION_MESSAGE);

    }

    private int contarDigitosPares(int num) {
        int cont = 0;
        while (num != 0) {
            int digito = num % 10;
            if (digito % 2 == 0) {
                cont++;
            }
            num /= 10;
        }
        return cont;
    }

}
