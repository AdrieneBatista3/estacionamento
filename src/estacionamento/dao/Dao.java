package estacionamento.dao;


import estacionamento.factory.Conexao;
import java.sql.Connection;

public class Dao {

    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
}
