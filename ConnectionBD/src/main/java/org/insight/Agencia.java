package org.insight;

public class Agencia {
    private int idAgencia;
    private String nome;
    private String cnpj;
    private String endereco;
    private String cep;
    private Funcionario administrador;

    public Agencia(){}

    public Agencia(int idAgencia, String nome, String cnpj, String endereco, String cep, Funcionario administrador) {
        this.idAgencia = idAgencia;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cep = cep;
        this.administrador = administrador;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public Funcionario getAdministrador() {
        return administrador;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setAdministrador(Funcionario administrador) {
        this.administrador = administrador;
    }
}
