import ETL.Leitor;
import Objetos.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsightApplication {

    public static void main(String[] args) {
        System.out.println("Iniciando execução do programa");

        ConnectionProvider cnp = new ConnectionProvider();
        JdbcTemplate connection = cnp.getConnection();

        System.out.println("Conectado ao banco de dados");

        String nomeArquivo = "chegadas_2023.xlsx";
        Path caminho = Path.of(nomeArquivo);

        String selectEstados = "SELECT * FROM Estado";
        List<Estado> estados = connection.query(selectEstados, new BeanPropertyRowMapper<>(Estado.class));
        System.out.println("Recuperando lista de estados");
        for (Estado estado : estados) {
            System.out.println(estado.toString());
        }

        try (InputStream arquivo = Files.newInputStream(caminho)) {
            System.out.println("Lendo arquivo " + nomeArquivo);

            List<VooExterior> voosExtraidos = Leitor.ExtrairVoo(nomeArquivo, arquivo);

            int numeroDeVoosParaExibir = Math.min(10, voosExtraidos.size());
            System.out.println("Exibindo os " + numeroDeVoosParaExibir + " primeiros voos:");

            for (VooExterior vooExtraido : voosExtraidos) {
                System.out.println(vooExtraido.toString());
                System.out.println("------------------------------");

                String dataViagem = String.format("%04d-%02d-%s", vooExtraido.getAno(), vooExtraido.getMes(), "01");

                String sqlInsertDatas = "INSERT INTO Passagem (NomePassagem, Natureza, Origem, Destino, dtViagem, fkAgencia) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?);\n";

                System.out.println("Executando query: " + sqlInsertDatas);
                try {
                    connection.update(sqlInsertDatas, vooExtraido.createName(), "Internacional", vooExtraido.converterToStringPaisOrigem(),
                            vooExtraido.converterToStringEstado(), dataViagem, null);
                } catch (Exception e) {
                    System.out.println("Erro ao executar query: " + sqlInsertDatas);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Leitura finalizada!");

        String sqlStart = "SHOW TABLES"; // Query de validação das tabelas
        List<String> tables = connection.queryForList(sqlStart, String.class);

        System.out.println("Todas as tabelas do banco de dados: " + tables.size() + " tabelas");

        for (String tableName : tables) {
            System.out.println(tableName);
        }

        System.out.println("Execução finalizada!");
    }
}
