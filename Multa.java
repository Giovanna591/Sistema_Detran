import java.util.Date; // Importa a classe Date para trabalhar com datas

// Classe que representa uma Multa
public class Multa {
    // Atributos privados da multa
    private double valor; // Valor da multa
    private String motivo; // Motivo ou descrição da multa
    private Date data; // Data em que a multa foi aplicada
    private String placa; // Placa do veículo que recebeu a multa

    // Construtor da classe Multa para inicializar os atributos
    public Multa(double valor, String motivo, Date data, String placa) {
        this.valor = valor; // Inicializa o valor da multa
        this.motivo = motivo; // Inicializa o motivo da multa
        this.data = data; // Inicializa a data da multa
        this.placa = placa; // Inicializa a placa do veículo
    }

    // Getter para o atributo 'valor'
    public double getValor() {
        return valor; // Retorna o valor da multa
    }

    // Setter para o atributo 'valor'
    public void setValor(double valor) {
        this.valor = valor; // Atualiza o valor da multa
    }

    // Getter para o atributo 'motivo'
    public String getMotivo() {
        return motivo; // Retorna o motivo da multa
    }

    // Setter para o atributo 'motivo'
    public void setMotivo(String motivo) {
        this.motivo = motivo; // Atualiza o motivo da multa
    }

    // Getter para o atributo 'data'
    public Date getData() {
        return data; // Retorna a data da multa
    }

    // Setter para o atributo 'data'
    public void setData(Date data) {
        this.data = data; // Atualiza a data da multa
    }

    // Getter para o atributo 'placa'
    public String getPlaca() {
        return placa; // Retorna a placa do veículo que recebeu a multa
    }

    // Setter para o atributo 'placa'
    public void setPlaca(String placa) {
        this.placa = placa; // Atualiza a placa do veículo
    }
}
