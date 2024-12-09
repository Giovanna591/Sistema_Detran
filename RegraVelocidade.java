// Classe que representa uma regra de velocidade em um logradouro
public class RegraVelocidade extends RegraMulta {
    // Atributos privados para encapsulamento
    private int velocidadeMaxima; // Velocidade máxima permitida no logradouro
    private String nomeLogradouro; // Nome do logradouro onde a regra se aplica

    // Construtor para inicializar os atributos da classe
    public RegraVelocidade(int velocidadeMaxima, String nomeLogradouro) {
        // Verifica se a velocidade máxima é válida (maior que zero)
        if (velocidadeMaxima <= 0) {
            throw new IllegalArgumentException("Velocidade máxima deve ser maior que zero.");
        }
        // Verifica se o nome do logradouro é válido (não nulo ou vazio)
        if (nomeLogradouro == null || nomeLogradouro.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do logradouro não pode ser vazio.");
        }
        this.velocidadeMaxima = velocidadeMaxima; // Define a velocidade máxima
        this.nomeLogradouro = nomeLogradouro; // Define o nome do logradouro
    }

    // Método que verifica o nível da multa com base na ocorrência
    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Verifica se o logradouro da ocorrência corresponde ao especificado na regra
        if (!ocorrencia.getNomeLogradouro().equals(nomeLogradouro)) return 0;

        // Calcula o excesso de velocidade
        int excesso = ocorrencia.getVelocidadeMedida() - velocidadeMaxima;

        // Define o nível da multa com base no excesso de velocidade
        if (excesso <= 0) return 0; // Sem infração
        if (excesso <= velocidadeMaxima * 0.1) return 1; // Excesso leve (até 10% da velocidade máxima)
        if (excesso <= velocidadeMaxima * 0.4) return 2; // Excesso moderado (até 40% da velocidade máxima)
        return 3; // Excesso grave (mais de 40% da velocidade máxima)
    }

    // Método que retorna a descrição da multa
    @Override
    public String obterDescricaoMulta() {
        return "Excesso de velocidade na " + nomeLogradouro; // Descrição com o logradouro
    }

    // Getters e Setters para os atributos

    // Retorna a velocidade máxima permitida
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    // Define uma nova velocidade máxima, verificando se é válida
    public void setVelocidadeMaxima(int velocidadeMaxima) {
        if (velocidadeMaxima <= 0) {
            throw new IllegalArgumentException("Velocidade máxima deve ser maior que zero.");
        }
        this.velocidadeMaxima = velocidadeMaxima;
    }

    // Retorna o nome do logradouro
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    // Define um novo nome para o logradouro, verificando se é válido
    public void setNomeLogradouro(String nomeLogradouro) {
        if (nomeLogradouro == null || nomeLogradouro.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do logradouro não pode ser vazio.");
        }
        this.nomeLogradouro = nomeLogradouro;
    }
}
