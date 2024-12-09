import java.util.Date; // Importa a classe Date para manipular datas e horas

// Classe que representa uma ocorrência registrada no sistema
public class Ocorrencia {
    // Atributos privados para garantir encapsulamento
    private String placa; // Placa do veículo relacionado à ocorrência
    private Date dataHora; // Data e hora em que a ocorrência aconteceu
    private String nomeLogradouro; // Nome do logradouro onde ocorreu a infração
    private int velocidadeMedida; // Velocidade medida no momento da ocorrência
    private int tipoVeiculo; // Tipo do veículo (1 para carro, 2 para moto, etc.)

    // Construtor da classe para inicializar os atributos com validação
    public Ocorrencia(String placa, Date dataHora, String nomeLogradouro, int velocidadeMedida, int tipoVeiculo) {
        setPlaca(placa); // Define a placa após validação
        setDataHora(dataHora); // Define a data e hora após validação
        setNomeLogradouro(nomeLogradouro); // Define o nome do logradouro após validação
        setVelocidadeMedida(velocidadeMedida); // Define a velocidade medida após validação
        setTipoVeiculo(tipoVeiculo); // Define o tipo de veículo após validação
    }

    // Getter para o atributo 'placa'
    public String getPlaca() {
        return placa; // Retorna a placa do veículo
    }

    // Setter para o atributo 'placa' com validação de formato
    public void setPlaca(String placa) {
        if (placa == null || !placa.matches("[A-Z]{3}-\\d{4}")) { // Verifica se a placa segue o formato ABC-1234
            throw new IllegalArgumentException("Placa inválida! O formato deve ser ABC-1234.");
        }
        this.placa = placa; // Atualiza o valor da placa
    }

    // Getter para o atributo 'dataHora'
    public Date getDataHora() {
        return dataHora; // Retorna a data e hora da ocorrência
    }

    // Setter para o atributo 'dataHora' com validação
    public void setDataHora(Date dataHora) {
        if (dataHora == null || dataHora.after(new Date())) { // Verifica se a data não é nula ou no futuro
            throw new IllegalArgumentException("Data e hora inválidas! Não pode ser uma data futura.");
        }
        this.dataHora = dataHora; // Atualiza a data e hora da ocorrência
    }

    // Getter para o atributo 'nomeLogradouro'
    public String getNomeLogradouro() {
        return nomeLogradouro; // Retorna o nome do logradouro
    }

    // Setter para o atributo 'nomeLogradouro' com validação
    public void setNomeLogradouro(String nomeLogradouro) {
        if (nomeLogradouro == null || nomeLogradouro.trim().isEmpty()) { // Verifica se o logradouro não está vazio
            throw new IllegalArgumentException("Nome do logradouro não pode ser vazio.");
        }
        this.nomeLogradouro = nomeLogradouro; // Atualiza o nome do logradouro
    }

    // Getter para o atributo 'velocidadeMedida'
    public int getVelocidadeMedida() {
        return velocidadeMedida; // Retorna a velocidade medida
    }

    // Setter para o atributo 'velocidadeMedida' com validação
    public void setVelocidadeMedida(int velocidadeMedida) {
        if (velocidadeMedida < 0) { // Verifica se a velocidade é negativa
            throw new IllegalArgumentException("Velocidade medida não pode ser negativa.");
        }
        this.velocidadeMedida = velocidadeMedida; // Atualiza a velocidade medida
    }

    // Getter para o atributo 'tipoVeiculo'
    public int getTipoVeiculo() {
        return tipoVeiculo; // Retorna o tipo do veículo
    }

    // Setter para o atributo 'tipoVeiculo' com validação
    public void setTipoVeiculo(int tipoVeiculo) {
        if (tipoVeiculo < 1 || tipoVeiculo > 10) { // Verifica se o tipo está no intervalo permitido
            throw new IllegalArgumentException("Tipo de veículo inválido.");
        }
        this.tipoVeiculo = tipoVeiculo; // Atualiza o tipo do veículo
    }
}
