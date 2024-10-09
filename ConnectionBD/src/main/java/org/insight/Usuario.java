package org.insight;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    //Construtor padrão para o JDBC (Ele utiliza por padrão um construtor vazio)
    public Usuario() {
    }


    //Construtor
    public Usuario(String nome, String email, String senha, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Senha: " + senha + "\n" +
                "Telefone: " + telefone + "\n" +
                "CPF: " + cpf + "\n";
    }

}
