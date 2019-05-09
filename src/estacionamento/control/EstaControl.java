package estacionamento.control;

import estacionamento.dao.EstaDao;
import estacionamento.model.Estacio;
import estacionamento.uteis.Mensagens;
import estacionamento.uteis.Painel;
import estacionamento.uteis.ThreadRelogio;
import estacionamento.uteis.Validacao;
import estacionamento.view.GerenciarEsta;
import static estacionamento.view.GerenciarEsta.tfEntrada;
import static estacionamento.view.GerenciarEsta.tfSaida;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EstaControl {

    List<Estacio> listEsta;
    EstaDao estaDao;
    Estacio Estacio;
    ThreadRelogio threadRelogio;

    public EstaControl() {
        estaDao = new EstaDao();
    }

    public void ListarAction() {
        listEsta = estaDao.listar();
        atualizarJTable();
    }

    public void atualizarJTable() {
        DefaultTableModel model = (DefaultTableModel) GerenciarEsta.tblEstacio.getModel();
        model.setNumRows(0);
        double total = 0;
        for (Estacio e : listEsta) {
            model.addRow(new Object[]{
                e.getId(),
                e.getNomeCondutor(),
                e.getTipoCliente(),
                e.getPlaca(),
                e.getCor(),
                e.getMarca(),
                e.getModelo()
            });
            total += e.getValor();
        }
        atualizarLabelTotal(total);
    }

    public void atualizarLabelTotal(double total) {
        GerenciarEsta.lblTotal.setText("R$ " + total);
    }

    public void CadastroEstacionamento() {
        Estacio = new Estacio();
        Estacio.setNomeCondutor(GerenciarEsta.tfNome.getText());
        Estacio.setTipoCliente(GerenciarEsta.tfTipoCliente.getText());
        Estacio.setPlaca(GerenciarEsta.tfPlaca.getText());
        Estacio.setCor(GerenciarEsta.tfCor.getText());
        Estacio.setMarca(GerenciarEsta.tfMarca.getText());
        Estacio.setModelo(GerenciarEsta.tfModelo.getText());

        int res = estaDao.cadastrar(Estacio);
        if (res > 0) {
            Painel.msg(Mensagens.SUCESSO_CADASTRO);
            ListarAction();
        } else {
            Painel.msg(Mensagens.ERRO_CADASTRAR);
        }

        GerenciarEsta.tfNome.setText("");
        GerenciarEsta.tfTipoCliente.setText("");
        GerenciarEsta.tfPlaca.setText("");
        GerenciarEsta.tfCor.setText("");
        GerenciarEsta.tfMarca.setText("");
        GerenciarEsta.tfModelo.setText("");
        Estacio = null;

    }

    public void CadastroForm() {
        GerenciarEsta.tfNome.setText(Estacio.getNomeCondutor());
        GerenciarEsta.tfTipoCliente.setText(Estacio.getTipoCliente());
        GerenciarEsta.tfPlaca.setText(Estacio.getPlaca());
        GerenciarEsta.tfCor.setText(Estacio.getCor());
        GerenciarEsta.tfMarca.setText(Estacio.getMarca());
        GerenciarEsta.tfModelo.setText(Estacio.getModelo());
        GerenciarEsta.tfNome.requestFocus();

    }

    public void CadastrarAction() {
        if (Validacao.campoVazio(GerenciarEsta.tfNome)) {
            Painel.msg(Mensagens.ERRO_NOME_INVALIDO);
            GerenciarEsta.tfNome.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfTipoCliente)) {
            Painel.msg(Mensagens.ERRO_TIPOCLIENTE_INVALIDO);
            GerenciarEsta.tfTipoCliente.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfPlaca)) {
            Painel.msg(Mensagens.ERRO_PLACA_INVALIDA);
            GerenciarEsta.tfPlaca.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfCor)) {
            Painel.msg(Mensagens.ERRO_COR_INVALIDA);
            GerenciarEsta.tfCor.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfMarca)) {
            Painel.msg(Mensagens.ERRO_MARCA_INVALIDA);
            GerenciarEsta.tfMarca.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfModelo)) {
            Painel.msg(Mensagens.ERRO_MODELO_INVALIDA);
            GerenciarEsta.tfModelo.requestFocus();
            return;
        }

        CadastroEstacionamento();

    }

    private Estacio getClienteSelecionadoTable() {
        int i = GerenciarEsta.tblEstacio.getSelectedRow();
        if (i >= 0) {
            return listEsta.get(i);
        }
        return null;
    }

    public void excluirAction() {
        Estacio e = getClienteSelecionadoTable();
        if (e == null) {
            Painel.msg("Selecione um Cliente");
            return;

        }

        if (Painel.questao("Deseja Excluir o Cliente" + e.getNomeCondutor() + "?") == JOptionPane.YES_OPTION) {
            if (estaDao.deletar(e.getId())) {
                Painel.msg(Mensagens.SUCESSO_EXCLUIR);
                ListarAction();
            } else {
                Painel.msg(Mensagens.ERRO_EXCLUIR);
            }

        }

    }

    public void popularFormAction() {
        Estacio = getClienteSelecionadoTable();
        if (Estacio == null) {
            Painel.msg("Selecione um Cliente");
            return;
        }
        CadastroForm();

    }

    public void editarAction() {
        if (Validacao.campoVazio(GerenciarEsta.tfNome)) {
            Painel.msg(Mensagens.ERRO_NOME_INVALIDO);
            GerenciarEsta.tfNome.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfTipoCliente)) {
            Painel.msg(Mensagens.ERRO_TIPOCLIENTE_INVALIDO);
            GerenciarEsta.tfTipoCliente.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfPlaca)) {
            Painel.msg(Mensagens.ERRO_PLACA_INVALIDA);
            GerenciarEsta.tfPlaca.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfCor)) {
            Painel.msg(Mensagens.ERRO_COR_INVALIDA);
            GerenciarEsta.tfCor.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfMarca)) {
            Painel.msg(Mensagens.ERRO_MARCA_INVALIDA);
            GerenciarEsta.tfMarca.requestFocus();
            return;
        }
        if (Validacao.campoVazio(GerenciarEsta.tfModelo)) {
            Painel.msg(Mensagens.ERRO_MODELO_INVALIDA);
            GerenciarEsta.tfModelo.requestFocus();
            return;
        }
        Estacio estacio = new Estacio();
        Estacio.setNomeCondutor(GerenciarEsta.tfNome.getText());
        Estacio.setTipoCliente(GerenciarEsta.tfTipoCliente.getText());
        Estacio.setPlaca(GerenciarEsta.tfPlaca.getText());
        Estacio.setCor(GerenciarEsta.tfCor.getText());
        Estacio.setMarca(GerenciarEsta.tfMarca.getText());
        Estacio.setModelo(GerenciarEsta.tfModelo.getText());

        if (estaDao.alterar(Estacio)) {
            Painel.msg(Mensagens.SUCESSO_EDITAR);
            ListarAction();
        } else {
            Painel.msg("Erro ao Editar");
        }

        GerenciarEsta.tfNome.setText("");
        GerenciarEsta.tfTipoCliente.setText("");
        GerenciarEsta.tfPlaca.setText("");
        GerenciarEsta.tfCor.setText("");
        GerenciarEsta.tfMarca.setText("");
        GerenciarEsta.tfModelo.setText("");
        Estacio = null;

    }

    public void salvarAction() {
        if (Estacio == null) {
            CadastrarAction();
        } else {
            editarAction();
        }
    }

    public void pesquisarAction() {
        listEsta = estaDao.pesquisar(GerenciarEsta.tfPesquisar.getText());
        atualizarJTable();
    }

    public void calculoTempo() {
        if (Estacio instanceof Estacio) {
            double Servidor = 2.00;
            double Publico = 4.00;
        }
    }
}
