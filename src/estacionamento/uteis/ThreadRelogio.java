package estacionamento.uteis;

import estacionamento.view.GerenciarEsta;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadRelogio {

    public static boolean play = true;

    public static void start() {
        new Thread() {
            @Override
            public void run() {
                while (play) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
                    Date data = new Date();
                    GerenciarEsta.tfEntrada.setText(sdf.format(data));
                    GerenciarEsta.tfSaida.setText(sdf.format(data));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {

                    }

                }
                run();

            }
        }.start();
    }

}
