package estacionamento.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public final static String URL = "jdbc:mysql://localhost:3306/estacio";
    public final static String USER = "root";
    public final static String PASS = "";
    private static Connection conexao;
    
    public static Connection getConexao(){
        if(conexao == null){
            try {
                conexao = DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Conectouuu...");
            
        }
        return conexao;
    }
    
    
    
    

}
