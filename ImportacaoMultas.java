import java.io.BufferedReader; // Para leitura de arquivos linha a linha
import java.io.FileReader; // Para abrir e ler arquivos de texto
import java.util.Date; // Para trabalhar com datas
import java.text.SimpleDateFormat; // Para formatação e conversão de datas

public class ImportacaoMultas {
    // Método para importar multas de um arquivo de texto e adicioná-las à base de dados
    public static void importarMultas(String caminhoArquivo, BaseDeDados baseDeDados) {
        // Bloco try-with-resources para garantir que o BufferedReader seja fechado automaticamente
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha; // Variável para armazenar cada linha lida do arquivo

            // Loop para ler todas as linhas do arquivo
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(","); // Divide a linha em partes separadas por vírgulas

                // Verifica se a linha possui exatamente 4 partes
                if (partes.length != 4) {
                    System.out.println("Linha inválida: " + linha); // Exibe mensagem de erro para linhas inválidas
                    continue; // Ignora a linha atual e continua para a próxima
                }

                // Extrai os valores das partes e realiza conversões necessárias
                double valor = Double.parseDouble(partes[0].trim()); // Converte o valor para double
                String motivo = partes[1].trim(); // Motivo da multa
                Date data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(partes[2].trim()); // Converte a data para o tipo Date
                String placa = partes[3].trim(); // Placa do veículo

                // Cria uma nova instância de Multa com os dados lidos
                Multa multa = new Multa(valor, motivo, data, placa);

                // Adiciona a multa à lista de multas na base de dados
                baseDeDados.getMultas().add(multa);
            }

            // Exibe mensagem de sucesso ao término da importação
            System.out.println("Importação concluída com sucesso!");
        } catch (Exception e) {
            // Captura e exibe mensagens de erro caso ocorram problemas durante a importação
            System.out.println("Erro durante a importação: " + e.getMessage());
        }
    }
}
