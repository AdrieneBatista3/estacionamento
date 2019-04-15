package estacionamento.uteis;

import javax.swing.JTextField;

public class Validacao {

    public static boolean campoVazio(JTextField tf) {
        return tf.getText().trim().isEmpty();
        
    }

}
