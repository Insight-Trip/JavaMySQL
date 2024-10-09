package org.insight;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Criando uma instância do ConnectionProvider(Classe de conexão)
        ConnectionProvider cnp = new ConnectionProvider();
        JdbcTemplate connection = cnp.getConnection();

        String sqlStart = "SHOW TABLES"; // Query de validação das tabelas
        List<String> tables = connection.queryForList(sqlStart, String.class);

        System.out.println("Todas as tabelas do banco de dados: " + tables.size() + " tabelas");

        for(String tableName : tables){
            System.out.println(tableName);
        }
        //Listando todos os usuarios
        String sqlSelect = "SELECT * FROM Funcionario";
        List<Funcionario> funcionarios = connection.query(sqlSelect, new BeanPropertyRowMapper<>(Funcionario.class));

        System.out.println("Todas as tabelas do banco de dados: " + funcionarios.size() + " usários");
        for(Funcionario u : funcionarios){
            System.out.println(u);
        }

        String InsertArea = "INSERT INTO AREA (NOME) VALUES (?)";
        connection.update(InsertArea, "Administrador");

        String sqlSelectArea = "SELECT * FROM AREA";
        List<Area> areas = connection.query(sqlSelectArea, new BeanPropertyRowMapper<>(Area.class));

        System.out.println("Todas as tabelas do banco de dados: " + areas.size() + " areas");
        for(Area a : areas){
            System.out.println(a);

            System.out.println(a.getId() + " - " + a.getNome());
        }

    }
}