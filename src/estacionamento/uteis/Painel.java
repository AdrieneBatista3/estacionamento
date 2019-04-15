package estacionamento.uteis;

import javax.swing.JOptionPane;

public class Painel {

    public static void msg(String texto) {
        JOptionPane.showMessageDialog(null, texto);

    }

    public static int questao(String pergunta) {
        return JOptionPane.showConfirmDialog(null, pergunta);

    }

}
