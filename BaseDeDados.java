import java.util.ArrayList; // Importa a classe ArrayList para criar listas dinâmicas
import java.util.List; // Importa a interface List para representar listas

public class BaseDeDados {
    // Atributos privados que armazenam os dados do sistema
    private List<Ocorrencia> ocorrenciasSemProcessar; // Lista de ocorrências ainda não processadas
    private List<Ocorrencia> ocorrenciasProcessadas; // Lista de ocorrências já processadas
    private List<Multa> multas; // Lista de multas geradas
    private List<RegraMulta> regras; // Lista de regras aplicáveis às ocorrências

    // Construtor para inicializar as listas
    public BaseDeDados() {
        this.ocorrenciasSemProcessar = new ArrayList<>(); // Inicializa a lista de ocorrências sem processar
        this.ocorrenciasProcessadas = new ArrayList<>(); // Inicializa a lista de ocorrências processadas
        this.multas = new ArrayList<>(); // Inicializa a lista de multas
        this.regras = new ArrayList<>(); // Inicializa a lista de regras
    }

    // Método para processar todas as ocorrências pendentes
    public void processar() {
        for (Ocorrencia ocorrencia : ocorrenciasSemProcessar) { // Para cada ocorrência não processada
            for (RegraMulta regra : regras) { // Verifica todas as regras
                if (regra.verificaNivelMulta(ocorrencia) > 0) { // Se a regra identificar uma infração
                    Multa multa = regra.calcularMulta(ocorrencia); // Calcula a multa com base na regra
                    multas.add(multa); // Adiciona a multa à lista de multas
                }
            }
            ocorrenciasProcessadas.add(ocorrencia); // Move a ocorrência para a lista de processadas
        }
        ocorrenciasSemProcessar.clear(); // Remove todas as ocorrências da lista de não processadas
    }

    // Métodos getter e setter para manipulação das ocorrências sem processar
    public List<Ocorrencia> getOcorrenciasSemProcessar() {
        return ocorrenciasSemProcessar; // Retorna a lista de ocorrências sem processar
    }

    public void setOcorrenciasSemProcessar(List<Ocorrencia> ocorrenciasSemProcessar) {
        this.ocorrenciasSemProcessar = ocorrenciasSemProcessar; // Define uma nova lista de ocorrências sem processar
    }

    // Métodos getter e setter para ocorrências processadas
    public List<Ocorrencia> getOcorrenciasProcessadas() {
        return ocorrenciasProcessadas; // Retorna a lista de ocorrências processadas
    }

    public void setOcorrenciasProcessadas(List<Ocorrencia> ocorrenciasProcessadas) {
        this.ocorrenciasProcessadas = ocorrenciasProcessadas; // Define uma nova lista de ocorrências processadas
    }

    // Métodos getter e setter para multas
    public List<Multa> getMultas() {
        return multas; // Retorna a lista de multas
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas; // Define uma nova lista de multas
    }

    // Métodos getter e setter para regras
    public List<RegraMulta> getRegras() {
        return regras; // Retorna a lista de regras
    }

    public void setRegras(List<RegraMulta> regras) {
        this.regras = regras; // Define uma nova lista de regras
    }

    // Método para inicializar as regras de multas no sistema
    public void inicializaRegras() {
        // Adiciona regras de velocidade com limites e logradouros específicos
        regras.add(new RegraVelocidade(50, "Avenida Santo Amaro"));
        regras.add(new RegraVelocidade(70, "Marginal Pinheiros"));
        regras.add(new RegraVelocidade(50, "Avenida Faria Lima"));
        regras.add(new RegraVelocidade(60, "Avenida 23 de Maio"));
        regras.add(new RegraVelocidade(40, "Avenida Ricardo Jafet"));
        regras.add(new RegraVelocidade(90, "Avenida Marginal Tietê"));
        regras.add(new RegraVelocidade(80, "Avenida Brasil"));
        regras.add(new RegraVelocidade(60, "Avenida dos Bandeirantes"));
        regras.add(new RegraVelocidade(50, "Avenida Paulista"));
        regras.add(new RegraVelocidade(110, "Rodovia Anhanguera"));

        // Adiciona regras de rodízio com dias, logradouros e finais de placa específicos
        regras.add(new RegraRodizio(2, new String[] { "Avenida Paulista" }, "Segunda-feira", 1));
        regras.add(new RegraRodizio(2, new String[] { "Avenida 23 de Maio" }, "Segunda-feira", 2));
        regras.add(new RegraRodizio(6, new String[] { "Avenida Ibirapuera" }, "Quarta-feira", 1));
        regras.add(new RegraRodizio(2, new String[] { "Avenida dos Bandeirantes" }, "Quarta-feira", 2));
        regras.add(new RegraRodizio(3, new String[] { "Avenida Paulista" }, "Terça-feira", 1));
        regras.add(new RegraRodizio(4, new String[] { "Avenida Faria Lima" }, "Quinta-feira", 1));
        regras.add(new RegraRodizio(5, new String[] { "Avenida Ibirapuera" }, "Sexta-feira", 2));
        regras.add(new RegraRodizio(7, new String[] { "Avenida 23 de Maio" }, "Segunda-feira", 2));
        regras.add(new RegraRodizio(8, new String[] { "Avenida dos Bandeirantes" }, "Quarta-feira", 2));
        regras.add(new RegraRodizio(9, new String[] { "Avenida Paulista" }, "Sexta-feira", 1));

        // Adiciona regras para corredores de ônibus com horários e logradouros específicos
        regras.add(new RegraCorredorOnibus(5, 20, "Corredor Avenida 23 de Maio"));
        regras.add(new RegraCorredorOnibus(5, 20, "Corredor da Avenida dos Bandeirantes"));
        regras.add(new RegraCorredorOnibus(6, 21, "Corredor da Avenida Paulista"));
        regras.add(new RegraCorredorOnibus(5, 10, "Corredor da Avenida Brigadeiro Luis Antonio"));
        regras.add(new RegraCorredorOnibus(7, 18, "Corredor da Avenida Ibirapuera"));
        regras.add(new RegraCorredorOnibus(6, 19, "Corredor da Avenida 23 de Maio"));
        regras.add(new RegraCorredorOnibus(5, 22, "Corredor da Avenida dos Bandeirantes"));
        regras.add(new RegraCorredorOnibus(6, 20, "Corredor da Avenida Paulista"));
        regras.add(new RegraCorredorOnibus(5, 18, "Corredor da Avenida Faria Lima"));
        regras.add(new RegraCorredorOnibus(6, 22, "Corredor da Avenida Ricardo Jafet"));
    }
}
