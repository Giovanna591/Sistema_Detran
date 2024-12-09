import java.util.Date; // Para trabalhar com a classe Date
import java.util.Scanner; // Para entrada de dados do usuário
import java.text.SimpleDateFormat; // Para formatação e conversão de datas

public class CadastrarOcorrencia {

    // Método para cadastrar uma nova ocorrência
    public static void cadastrarOcorrencia(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            System.out.println("\n--- Cadastro de Ocorrência ---");

            // Solicita a placa do veículo ao usuário
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine(); // Lê a placa como string

            // Solicita a data e hora da ocorrência
            System.out.print("Digite a data e hora da ocorrência (dd/MM/yyyy HH:mm): ");
            String dataHoraStr = scanner.nextLine(); // Lê a data e hora como string
            Date dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataHoraStr); // Converte para o tipo Date

            // Solicita o nome do logradouro onde ocorreu o evento
            System.out.print("Digite o nome do logradouro: ");
            String logradouro = scanner.nextLine(); // Lê o logradouro como string

            // Solicita a velocidade medida no local da ocorrência
            System.out.print("Digite a velocidade medida: ");
            int velocidade = Integer.parseInt(scanner.nextLine()); // Converte a entrada para inteiro

            // Solicita o tipo de veículo (1 para carro, 2 para moto, etc.)
            System.out.print("Digite o tipo de veículo (1 para carro, 2 para moto, etc.): ");
            int tipoVeiculo = Integer.parseInt(scanner.nextLine()); // Converte a entrada para inteiro

            // Cria uma nova instância de Ocorrência com os dados fornecidos
            Ocorrencia ocorrencia = new Ocorrencia(placa, dataHora, logradouro, velocidade, tipoVeiculo);

            // Adiciona a nova ocorrência à lista de ocorrências sem processar na BaseDeDados
            baseDeDados.getOcorrenciasSemProcessar().add(ocorrencia);

            // Exibe mensagem de sucesso
            System.out.println("Ocorrência cadastrada com sucesso!");
        } catch (Exception e) {
            // Captura e exibe erros, caso algum problema ocorra durante o cadastro
            System.out.println("Erro ao cadastrar ocorrência: " + e.getMessage());
        }
    }
}
