package estacionamento.model;

public class Estacio {

    private int id;
    private String NomeCondutor;
    private String Placa;
    private String cor;
    private String marca;
    private String modelo;
    private double valor;
    private String TipoCliente;

    public Estacio() {
    }

    public Estacio(int id, String NomeCondutor, String TipoCliente) {
        this.id = id;
        this.NomeCondutor = NomeCondutor;
        this.TipoCliente = TipoCliente;
    }

    public Estacio(int id, String NomeCondutor, String Placa, String cor, String marca, String modelo, double valor, String TipoCliente) {
        this.id = id;
        this.NomeCondutor = NomeCondutor;
        this.Placa = Placa;
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
        this.TipoCliente = TipoCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCondutor() {
        return NomeCondutor;
    }

    public void setNomeCondutor(String NomeCondutor) {
        this.NomeCondutor = NomeCondutor;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(String TipoCliente) {
        this.TipoCliente = TipoCliente;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        try {
            setValor(Double.parseDouble(valor));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return id + " - " + NomeCondutor + " - R$" + valor;
    } 

}
