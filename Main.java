import java.io.BufferedReader; // Importa a classe para leitura de arquivos linha a linha
import java.io.FileReader; // Importa a classe para abrir e ler arquivos
import java.text.SimpleDateFormat; // Importa a classe para manipulação e formatação de datas
import java.util.Date; // Importa a classe que representa datas
import java.util.List; // Importa a interface para trabalhar com listas
import java.util.Scanner; // Importa a classe para entrada de dados do usuário
import java.util.stream.Collectors; // Importa a funcionalidade para filtrar dados usando Stream API

// Classe principal do programa
public class Main {
    // Método principal onde a execução do programa começa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um Scanner para capturar entradas do usuário
        BaseDeDados baseDeDados = new BaseDeDados(); // Instancia a base de dados do sistema
        baseDeDados.inicializaRegras(); // Inicializa as regras padrão no sistema

        // Exibe mensagem de boas-vindas ao usuário
        System.out.println("Bem-vindo ao Sistema de Gestão de Multas!");

        // Loop infinito para exibir o menu principal e executar as opções
        while (true) {
            // Exibe o menu com as opções disponíveis
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar Ocorrência");
            System.out.println("2. Processar Ocorrências");
            System.out.println("3. Exibir Multas Geradas");
            System.out.println("4. Importar Ocorrências de um Arquivo");
            System.out.println("5. Buscar Multas por Data e Placa");
            System.out.println("6. Cadastrar Nova Regra");
            System.out.println("7. Sair");

            // Solicita a escolha do usuário
            System.out.print("Opção: ");
            int opcao = Integer.parseInt(scanner.nextLine()); // Lê a entrada do usuário como um número inteiro

            // Estrutura switch para executar ações com base na escolha do usuário
            switch (opcao) {
                case 1:
                    cadastrarOcorrencia(baseDeDados, scanner); // Chama o método para cadastrar uma ocorrência
                    break;
                case 2:
                    processarOcorrencias(baseDeDados); // Chama o método para processar ocorrências
                    break;
                case 3:
                    exibirMultas(baseDeDados); // Chama o método para exibir multas geradas
                    break;
                case 4:
                    importarOcorrencias(baseDeDados, scanner); // Chama o método para importar ocorrências
                    break;
                case 5:
                    buscarMultas(baseDeDados, scanner); // Chama o método para buscar multas com base em critérios
                    break;
                case 6:
                    cadastrarNovaRegra(baseDeDados, scanner); // Chama o método para cadastrar novas regras
                    break;
                case 7:
                    // Finaliza o programa
                    System.out.println("Encerrando o sistema...");
                    scanner.close(); // Fecha o scanner
                    return; // Sai do loop e termina o programa
                default:
                    // Mensagem exibida quando a opção não é válida
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para cadastrar uma nova ocorrência no sistema
    public static void cadastrarOcorrencia(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            System.out.println("\n--- Cadastro de Ocorrência ---");

            // Solicita a placa do veículo
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine();

            // Solicita a data e hora da ocorrência
            System.out.print("Digite a data e hora da ocorrência (dd/MM/yyyy HH:mm): ");
            String dataHoraStr = scanner.nextLine();
            Date dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataHoraStr); // Converte o texto para um objeto Date

            // Solicita o nome do logradouro
            System.out.print("Digite o nome do logradouro: ");
            String logradouro = scanner.nextLine();

            // Solicita a velocidade medida
            System.out.print("Digite a velocidade medida: ");
            int velocidade = Integer.parseInt(scanner.nextLine());

            // Solicita o tipo de veículo
            System.out.print("Digite o tipo de veículo (1 para carro, 2 para moto, etc.): ");
            int tipoVeiculo = Integer.parseInt(scanner.nextLine());

            // Cria uma nova ocorrência com os dados informados
            Ocorrencia ocorrencia = new Ocorrencia(placa, dataHora, logradouro, velocidade, tipoVeiculo);

            // Adiciona a ocorrência na lista de ocorrências sem processar
            baseDeDados.getOcorrenciasSemProcessar().add(ocorrencia);

            // Exibe mensagem de sucesso
            System.out.println("Ocorrência cadastrada com sucesso!");
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de falha no cadastro
            System.out.println("Erro ao cadastrar ocorrência: " + e.getMessage());
        }
    }

    // Método para processar ocorrências e gerar multas
    public static void processarOcorrencias(BaseDeDados baseDeDados) {
        // Verifica se há ocorrências para processar
        if (baseDeDados.getOcorrenciasSemProcessar().isEmpty()) {
            System.out.println("Nenhuma ocorrência para processar.");
        } else {
            // Processa todas as ocorrências pendentes
            System.out.println("\n--- Processando Ocorrências ---");
            baseDeDados.processar(); // Chama o método processar da base de dados
            System.out.println("Ocorrências processadas com sucesso!");
        }
    }

    // Método para exibir as multas geradas
    public static void exibirMultas(BaseDeDados baseDeDados) {
        // Verifica se há multas geradas
        if (baseDeDados.getMultas().isEmpty()) {
            System.out.println("Nenhuma multa gerada.");
        } else {
            // Exibe as multas uma a uma
            System.out.println("\n--- Multas Geradas ---");
            for (Multa multa : baseDeDados.getMultas()) {
                System.out.println("Placa: " + multa.getPlaca());
                System.out.println("Motivo: " + multa.getMotivo());
                System.out.println("Valor: R$" + multa.getValor());
                System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(multa.getData()));
                System.out.println("--------------------------------");
            }
        }
    }

    // Método para importar ocorrências de um arquivo de texto
    public static void importarOcorrencias(BaseDeDados baseDeDados, Scanner scanner) {
        System.out.println("\n--- Importar Ocorrências ---");

        try {
            // Solicita o caminho do arquivo
            System.out.print("Digite o caminho do arquivo de texto: ");
            String caminhoArquivo = scanner.nextLine();

            // Abre o arquivo para leitura
            BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;
            int contador = 0; // Contador de ocorrências importadas

            // Lê o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(","); // Divide a linha em partes separadas por vírgulas

                // Valida o formato da linha
                if (dados.length != 5) {
                    System.out.println("Linha ignorada (formato inválido): " + linha);
                    continue;
                }

                // Converte os dados lidos para os tipos corretos
                String placa = dados[0].trim();
                Date dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dados[1].trim());
                String logradouro = dados[2].trim();
                int velocidade = Integer.parseInt(dados[3].trim());
                int tipoVeiculo = Integer.parseInt(dados[4].trim());

                // Cria uma nova ocorrência
                Ocorrencia ocorrencia = new Ocorrencia(placa, dataHora, logradouro, velocidade, tipoVeiculo);

                // Adiciona a ocorrência na lista de ocorrências sem processar
                baseDeDados.getOcorrenciasSemProcessar().add(ocorrencia);
                contador++; // Incrementa o contador de ocorrências importadas
            }

            // Fecha o arquivo após a leitura
            reader.close();

            // Exibe a quantidade de ocorrências importadas
            System.out.println(contador + " ocorrências importadas com sucesso!");
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de falha na importação
            System.out.println("Erro ao importar ocorrências: " + e.getMessage());
        }
    }
    // Método para buscar multas por placa e data
    public static void buscarMultas(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            System.out.println("\n--- Buscar Multas ---");

            // Solicita a placa ao usuário (opcional)
            System.out.print("Digite a placa do veículo (ou pressione ENTER para ignorar): ");
            String placa = scanner.nextLine();

            // Solicita a data ao usuário (opcional)
            System.out.print("Digite a data (dd/MM/yyyy) ou pressione ENTER para ignorar: ");
            String dataStr = scanner.nextLine();
            // Converte a entrada de data para um objeto Date, se fornecida
            Date data = dataStr.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

            // Filtra as multas na base de dados usando os critérios fornecidos
            List<Multa> multasFiltradas = baseDeDados.getMultas().stream()
                    .filter(multa -> (placa.isEmpty() || multa.getPlaca().equalsIgnoreCase(placa)) &&
                            (data == null || isSameDay(multa.getData(), data)))
                    .collect(Collectors.toList()); // Coleta os resultados filtrados em uma lista

            // Verifica se há resultados para exibir
            if (multasFiltradas.isEmpty()) {
                System.out.println("Nenhuma multa encontrada com os critérios fornecidos.");
            } else {
                // Exibe as multas encontradas
                System.out.println("\n--- Resultados da Busca ---");
                for (Multa multa : multasFiltradas) {
                    System.out.println("Placa: " + multa.getPlaca());
                    System.out.println("Motivo: " + multa.getMotivo());
                    System.out.println("Valor: R$" + multa.getValor());
                    System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(multa.getData()));
                    System.out.println("--------------------------------");
                }
            }
        } catch (Exception e) {
            // Exibe mensagem de erro caso algo dê errado
            System.out.println("Erro ao buscar multas: " + e.getMessage());
        }
    }

    // Método auxiliar para verificar se duas datas são do mesmo dia
    private static boolean isSameDay(Date date1, Date date2) {
        // Usa o formato "yyyyMMdd" para comparar apenas o dia, mês e ano
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date1).equals(sdf.format(date2)); // Retorna true se forem do mesmo dia
    }

    // Método para cadastrar uma nova regra no sistema
    public static void cadastrarNovaRegra(BaseDeDados baseDeDados, Scanner scanner) {
        System.out.println("\n--- Cadastro de Nova Regra ---");

        // Exibe os tipos de regras disponíveis para cadastro
        System.out.println("Selecione o tipo de regra:");
        System.out.println("1. Regra de Velocidade");
        System.out.println("2. Regra de Rodízio");
        System.out.println("3. Regra de Corredor de Ônibus");

        // Solicita a escolha do usuário
        System.out.print("Opção: ");
        int tipoRegra = Integer.parseInt(scanner.nextLine());

        // Chama o método correspondente com base na escolha do usuário
        switch (tipoRegra) {
            case 1:
                cadastrarRegraVelocidade(baseDeDados, scanner); // Cadastra regra de velocidade
                break;
            case 2:
                cadastrarRegraRodizio(baseDeDados, scanner); // Cadastra regra de rodízio
                break;
            case 3:
                cadastrarRegraCorredorOnibus(baseDeDados, scanner); // Cadastra regra de corredor de ônibus
                break;
            default:
                // Exibe mensagem de erro se o tipo de regra for inválido
                System.out.println("Tipo de regra inválido.");
        }
    }

    // Método para cadastrar uma nova regra de velocidade
    private static void cadastrarRegraVelocidade(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            // Solicita o nome do logradouro
            System.out.print("Digite o logradouro: ");
            String logradouro = scanner.nextLine();

            // Solicita o limite de velocidade para o logradouro
            System.out.print("Digite o limite de velocidade (km/h): ");
            int limiteVelocidade = Integer.parseInt(scanner.nextLine());

            // Cria uma nova regra de velocidade com os dados fornecidos
            RegraVelocidade regra = new RegraVelocidade(limiteVelocidade, logradouro);

            // Adiciona a regra à base de dados
            baseDeDados.getRegras().add(regra);

            // Exibe mensagem de sucesso
            System.out.println("Regra de velocidade cadastrada com sucesso!");
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de falha no cadastro
            System.out.println("Erro ao cadastrar regra de velocidade: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova regra de rodízio
    private static void cadastrarRegraRodizio(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            // Solicita o logradouro onde a regra será aplicada
            System.out.print("Digite o logradouro: ");
            String logradouro = scanner.nextLine();

            // Solicita o dia da semana em que a regra será aplicada
            System.out.print("Digite o dia da semana (ex: Segunda-feira): ");
            String diaSemana = scanner.nextLine();

            // Solicita o final da placa que estará restrito
            System.out.print("Digite o final da placa restrito (ex: 2): ");
            int finalPlaca = Integer.parseInt(scanner.nextLine());

            // Cria uma nova regra de rodízio com os dados fornecidos
            RegraRodizio regra = new RegraRodizio(finalPlaca, new String[]{logradouro}, diaSemana, 1);

            // Adiciona a regra à base de dados
            baseDeDados.getRegras().add(regra);

            // Exibe mensagem de sucesso
            System.out.println("Regra de rodízio cadastrada com sucesso!");
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de falha no cadastro
            System.out.println("Erro ao cadastrar regra de rodízio: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova regra de corredor de ônibus
    private static void cadastrarRegraCorredorOnibus(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            // Solicita o logradouro onde a regra será aplicada
            System.out.print("Digite o logradouro: ");
            String logradouro = scanner.nextLine();

            // Solicita a hora de início da regra
            System.out.print("Digite a hora de início (ex: 5 para 5h): ");
            int horaInicio = Integer.parseInt(scanner.nextLine());

            // Solicita a hora de término da regra
            System.out.print("Digite a hora de término (ex: 20 para 20h): ");
            int horaFim = Integer.parseInt(scanner.nextLine());

            // Cria uma nova regra de corredor de ônibus com os dados fornecidos
            RegraCorredorOnibus regra = new RegraCorredorOnibus(horaInicio, horaFim, logradouro);

            // Adiciona a regra à base de dados
            baseDeDados.getRegras().add(regra);

            // Exibe mensagem de sucesso
            System.out.println("Regra de corredor de ônibus cadastrada com sucesso!");
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de falha no cadastro
            System.out.println("Erro ao cadastrar regra de corredor de ônibus: " + e.getMessage());
        }
    }
}
