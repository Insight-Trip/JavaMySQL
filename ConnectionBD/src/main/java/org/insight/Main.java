package org.insight;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        //Fazendo cadastro de um novo usário
        String sqlInsert = "INSERT INTO USUARIO (NOME, EMAIL, TELEFONE, CPF, SENHA) VALUES (?, ?, ?, ?, ?)";
        connection.update(sqlInsert, "João", "pBqY6@example.com", "12345678911", "12345678911", "123456");

        //Listando todos os usuarios
        String sqlSelect = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = connection.query(sqlSelect, new BeanPropertyRowMapper<>(Usuario.class));

        System.out.println("Todas as tabelas do banco de dados: " + usuarios.size() + " usários");
        for(Usuario u : usuarios){
            System.out.println(u);
        }

        //Adicionando por instancia
        Usuario user = new Usuario("Joaquim", "pBqY6@example.com", "123456", "12345678911", "123456");
        connection.update(sqlInsert, user.getNome(), user.getEmail(), user.getTelefone(), user.getCpf(), user.getSenha());



    }
}