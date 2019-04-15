package estacionamento.interfaces;

import java.util.List;

public interface DaoI<E> {

    public List<E> listar();

    public int cadastrar(E obj);

    public boolean alterar(E obj);

    public boolean deletar(int id);

    public E lerPorId(int id);

    public List<E> pesquisar(String termo);

}
