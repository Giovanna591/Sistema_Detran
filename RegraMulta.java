// Classe abstrata que serve como base para diferentes tipos de regras de multa
public abstract class RegraMulta {
    // Valores padrão para os diferentes níveis de multas
    private double valorMultaLeve = 50.0; // Valor para multa leve
    private double valorMultaMedia = 250.0; // Valor para multa média
    private double valorMultaGrave = 800.0; // Valor para multa grave

    // Método abstrato para verificar o nível da multa com base em uma ocorrência
    public abstract int verificaNivelMulta(Ocorrencia ocorrencia);

    // Método abstrato para obter a descrição da multa
    public abstract String obterDescricaoMulta();

    // Método para calcular a multa com base no nível retornado pelo método abstrato
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        // Obtém o nível da multa da ocorrência
        int nivelMulta = verificaNivelMulta(ocorrencia);
        double valor; // Valor da multa a ser calculado

        // Define o valor da multa com base no nível
        switch (nivelMulta) {
            case 1: // Nível 1 (leve)
                valor = valorMultaLeve;
                break;
            case 2: // Nível 2 (média)
                valor = valorMultaMedia;
                break;
            case 3: // Nível 3 (grave)
                valor = valorMultaGrave;
                break;
            default: // Nenhum nível (sem multa)
                valor = 0.0;
                break;
        }

        // Retorna uma nova instância de Multa com os dados calculados
        return new Multa(valor, obterDescricaoMulta(), new java.util.Date(), ocorrencia.getPlaca());
    }

    // Getters e Setters para os valores das multas

    // Retorna o valor da multa leve
    public double getValorMultaLeve() {
        return valorMultaLeve;
    }

    // Define um novo valor para a multa leve
    public void setValorMultaLeve(double valorMultaLeve) {
        this.valorMultaLeve = valorMultaLeve;
    }

    // Retorna o valor da multa média
    public double getValorMultaMedia() {
        return valorMultaMedia;
    }

    // Define um novo valor para a multa média
    public void setValorMultaMedia(double valorMultaMedia) {
        this.valorMultaMedia = valorMultaMedia;
    }

    // Retorna o valor da multa grave
    public double getValorMultaGrave() {
        return valorMultaGrave;
    }

    // Define um novo valor para a multa grave
    public void setValorMultaGrave(double valorMultaGrave) {
        this.valorMultaGrave = valorMultaGrave;
    }
}
