// Classe que representa uma regra de rodízio para veículos
public class RegraRodizio extends RegraMulta {
    // Atributos privados para encapsulamento
    private int finalPlaca; // Final da placa restrita pelo rodízio
    private String[] logradourosAfetados; // Lista de logradouros onde a regra se aplica
    private String diaDaSemana; // Dia da semana em que o rodízio está ativo
    private int tipoVeiculo; // Tipo de veículo afetado pela regra

    // Construtor para inicializar os atributos da classe
    public RegraRodizio(int finalPlaca, String[] logradourosAfetados, String diaDaSemana, int tipoVeiculo) {
        this.finalPlaca = finalPlaca; // Define o final da placa
        this.logradourosAfetados = logradourosAfetados; // Define os logradouros afetados
        this.diaDaSemana = diaDaSemana; // Define o dia da semana do rodízio
        this.tipoVeiculo = tipoVeiculo; // Define o tipo de veículo
    }

    // Método que verifica o nível da multa com base na ocorrência
    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Verifica se o tipo de veículo da ocorrência corresponde ao especificado na regra
        if (ocorrencia.getTipoVeiculo() != tipoVeiculo) return 0;

        // Verifica se o logradouro e o final da placa correspondem à regra
        for (String logradouro : logradourosAfetados) {
            if (ocorrencia.getNomeLogradouro().equals(logradouro) // Compara o logradouro
                    && ocorrencia.getPlaca().endsWith(String.valueOf(finalPlaca))) { // Compara o final da placa
                return 2; // Retorna nível 2 (multa média)
            }
        }
        return 0; // Retorna 0 se não houver infração
    }

    // Método que retorna a descrição da multa
    @Override
    public String obterDescricaoMulta() {
        return "Infração de rodízio no dia " + diaDaSemana; // Descrição baseada no dia da semana
    }

    // Getters e Setters para os atributos

    // Retorna o final da placa restrito pelo rodízio
    public int getFinalPlaca() {
        return finalPlaca;
    }

    // Define o final da placa restrito pelo rodízio
    public void setFinalPlaca(int finalPlaca) {
        this.finalPlaca = finalPlaca;
    }

    // Retorna os logradouros afetados pela regra
    public String[] getLogradourosAfetados() {
        return logradourosAfetados;
    }

    // Define os logradouros afetados pela regra
    public void setLogradourosAfetados(String[] logradourosAfetados) {
        this.logradourosAfetados = logradourosAfetados;
    }

    // Retorna o dia da semana em que o rodízio está ativo
    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    // Define o dia da semana em que o rodízio está ativo
    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    // Retorna o tipo de veículo afetado pela regra
    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    // Define o tipo de veículo afetado pela regra
    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
}
