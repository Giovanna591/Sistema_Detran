import java.time.LocalTime; // Importa a classe LocalTime para manipular horas

// Classe que representa uma regra para trânsito em corredor de ônibus
public class RegraCorredorOnibus extends RegraMulta {
    // Atributos privados para encapsulamento
    private int horaInicial; // Hora inicial do período permitido
    private int horaFinal; // Hora final do período permitido
    private String nomeLogradouro; // Nome do logradouro onde a regra se aplica

    // Construtor da classe para inicializar os atributos com validação
    public RegraCorredorOnibus(int inicio, int fim, String logra) {
        // Validação dos horários
        if (inicio < 0 || fim > 23 || inicio > fim) {
            throw new IllegalArgumentException("Horários inválidos.");
        }
        this.horaInicial = inicio; // Define o horário inicial
        this.horaFinal = fim; // Define o horário final
        this.nomeLogradouro = logra; // Define o logradouro
    }

    // Método para verificar o nível da multa com base na ocorrência
    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Obtém a hora atual
        LocalTime agora = LocalTime.now();
        int horaAtual = agora.getHour(); // Extrai apenas a hora

        // Verifica se a hora atual está fora do período permitido e se a ocorrência ocorreu no logradouro
        if (horaAtual >= horaInicial && horaAtual <= horaFinal
                && ocorrencia.getNomeLogradouro().equals(nomeLogradouro)) {
            return 3; // Retorna nível 3 (Multa grave)
        }
        return 0; // Retorna 0 se não houver infração
    }

    // Método para obter a descrição da multa
    @Override
    public String obterDescricaoMulta() {
        return "Trânsito em corredor de ônibus fora do horário permitido no logradouro: " + nomeLogradouro;
    }

    // Getter para o atributo 'horaInicial'
    public int getHoraInicial() {
        return horaInicial; // Retorna o horário inicial
    }

    // Setter para o atributo 'horaInicial' com validação
    public void setHoraInicial(int horaInicial) {
        if (horaInicial < 0 || horaInicial > 23) { // Verifica se o horário inicial está no intervalo válido
            throw new IllegalArgumentException("Horário inicial inválido.");
        }
        this.horaInicial = horaInicial; // Atualiza o horário inicial
    }

    // Getter para o atributo 'horaFinal'
    public int getHoraFinal() {
        return horaFinal; // Retorna o horário final
    }

    // Setter para o atributo 'horaFinal' com validação
    public void setHoraFinal(int horaFinal) {
        if (horaFinal < 0 || horaFinal > 23 || horaFinal < horaInicial) { // Verifica se o horário final é válido
            throw new IllegalArgumentException("Horário final inválido.");
        }
        this.horaFinal = horaFinal; // Atualiza o horário final
    }

    // Getter para o atributo 'nomeLogradouro'
    public String getNomeLogradouro() {
        return nomeLogradouro; // Retorna o nome do logradouro
    }

    // Setter para o atributo 'nomeLogradouro' com validação
    public void setNomeLogradouro(String nomeLogradouro) {
        if (nomeLogradouro == null || nomeLogradouro.isEmpty()) { // Verifica se o logradouro não é vazio ou nulo
            throw new IllegalArgumentException("Nome do logradouro inválido.");
        }
        this.nomeLogradouro = nomeLogradouro; // Atualiza o nome do logradouro
    }
}
