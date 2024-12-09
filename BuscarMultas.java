import java.util.Scanner; // Para entrada de dados do usuário
import java.util.Date; // Para trabalhar com datas
import java.util.List; // Para usar a interface List
import java.text.SimpleDateFormat; // Para formatar e comparar datas
import java.util.stream.Collectors; // Para usar a Stream API

public class BuscarMultas {
    // Método para buscar multas na base de dados com base em critérios fornecidos pelo usuário
    public static void buscarMultas(BaseDeDados baseDeDados, Scanner scanner) {
        try {
            System.out.println("\n--- Buscar Multas ---");

            // Solicita a placa ao usuário. Se for deixada vazia, será ignorada no filtro.
            System.out.print("Digite a placa do veículo (ou pressione ENTER para ignorar): ");
            final String placa = scanner.nextLine(); // Define a placa como final para ser usada na lambda

            // Solicita a data ao usuário. Se for deixada vazia, será ignorada no filtro.
            System.out.print("Digite a data (dd/MM/yyyy) ou pressione ENTER para ignorar: ");
            String dataStr = scanner.nextLine(); // Lê a data como string
            final Date data = dataStr.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(dataStr); // Converte a string para Date ou null

            // Filtra as multas na base de dados com base nos critérios fornecidos
            List<Multa> multasFiltradas = baseDeDados.getMultas().stream() // Obtém a lista de multas como Stream
                    .filter(multa -> (placa.isEmpty() || multa.getPlaca().equalsIgnoreCase(placa)) && // Verifica a placa
                            (data == null || isSameDay(multa.getData(), data))) // Verifica a data
                    .collect(Collectors.toList()); // Coleta os resultados filtrados em uma lista

            // Verifica se nenhuma multa foi encontrada com os critérios fornecidos
            if (multasFiltradas.isEmpty()) {
                System.out.println("Nenhuma multa encontrada com os critérios fornecidos.");
            } else {
                // Exibe as multas encontradas
                System.out.println("\n--- Resultados da Busca ---");
                for (Multa multa : multasFiltradas) { // Itera pelas multas filtradas
                    System.out.println("Placa: " + multa.getPlaca()); // Exibe a placa
                    System.out.println("Motivo: " + multa.getMotivo()); // Exibe o motivo
                    System.out.println("Valor: R$" + multa.getValor()); // Exibe o valor da multa
                    System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(multa.getData())); // Formata e exibe a data
                    System.out.println("------------------------------------------------");
                }
            }
        } catch (Exception e) {
            // Captura e exibe qualquer erro durante o processo
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }

    // Método auxiliar para verificar se duas datas são do mesmo dia
    private static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Formata as datas apenas com ano, mês e dia
        return sdf.format(date1).equals(sdf.format(date2)); // Compara as datas formatadas
    }
}
