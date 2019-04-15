package estacionamento.dao;

import estacionamento.interfaces.DaoI;
import estacionamento.model.Estacio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstaDao extends Dao implements DaoI<Estacio> {

    public EstaDao() {
        super();
    }

    @Override
    public List<Estacio> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT id ,nomeCondutor, tipoCliente ,placa,cor,marca,modelo FROM estacio "
                    + "WHERE ativo = 1 ORDER BY id DESC ", PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet res = stmt.executeQuery();
            List<Estacio> lista = new ArrayList<>();
            while (res.next()) {
                Estacio est = new Estacio();
                est.setId(res.getInt("id"));
                est.setNomeCondutor(res.getString("nomeCondutor"));
                est.setTipoCliente(res.getString("tipoCliente"));
                est.setPlaca(res.getString("placa"));
                est.setCor(res.getString("cor"));
                est.setMarca(res.getString("marca"));
                est.setModelo(res.getString("modelo"));

                lista.add(est);

            }
            return lista;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public int cadastrar(Estacio obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("INSERT INTO estacio(nomeCondutor,tipoCliente,placa,cor,marca,modelo) "
                    + "values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNomeCondutor());
            stmt.setString(2, obj.getTipoCliente());
            stmt.setString(3, obj.getPlaca());
            stmt.setString(4, obj.getCor());
            stmt.setString(5, obj.getMarca());
            stmt.setString(6, obj.getModelo());

            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;

        }
    }

    @Override
    public boolean alterar(Estacio obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("UPDATE estacio SET nomeCondutor = ? ,tipoCliente = ?, placa = ? ,cor = ?,"
                    + " marca = ? , modelo = ? WHERE id = ?");
            stmt.setString(1, obj.getNomeCondutor());
            stmt.setString(2, obj.getTipoCliente());
            stmt.setString(3, obj.getPlaca());
            stmt.setString(4, obj.getCor());
            stmt.setString(5, obj.getMarca());
            stmt.setString(6, obj.getModelo());
            stmt.setInt(7, obj.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("UPDATE estacio SET ativo = 0 WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Estacio lerPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT id ,nomeCondutor ,tipoCliente ,placa ,cor ,marca ,modelo "
                    + "FROM estacio WHERE ativo = 1 AND id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            Estacio estacionamento = null;
            if (res.next()) {
                Estacio e = new Estacio();
                e.setId(res.getInt("id"));
                e.setNomeCondutor(res.getString("nomeCondutor"));
                e.setTipoCliente(res.getString("tipoCliente"));
                e.setPlaca(res.getString("placa"));
                e.setCor(res.getString("cor"));
                e.setMarca(res.getString("marca"));
                e.setModelo(res.getString("modelo"));
                return e;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Estacio> pesquisar(String termo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT id ,nomeCondutor,tipoCliente,placa,cor,marca,modelo"
                    + " FROM estacio WHERE ativo = 1"
                    + " AND (id = ? OR placa LIKE ? OR nomeCondutor = ?)");
            stmt.setString(1, termo);
            stmt.setString(2, termo + "%");
            stmt.setString(3, termo);
            ResultSet res = stmt.executeQuery();
            List<Estacio> lista = new ArrayList<>();
            while (res.next()) {
                Estacio es = new Estacio();
                es.setId(res.getInt("id"));
                es.setNomeCondutor(res.getString("nomeCondutor"));
                es.setTipoCliente(res.getString("tipoCliente"));
                es.setPlaca(res.getString("placa"));
                es.setCor(res.getString("cor"));
                es.setMarca(res.getString("marca"));
                es.setModelo(res.getString("modelo"));
                lista.add(es);

            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
