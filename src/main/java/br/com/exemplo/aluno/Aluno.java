package br.com.exemplo.aluno;

import java.time.LocalDate;

public class Aluno {
    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Aluno() {}

    public Aluno(Integer id, String nome, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(String nome, String email, LocalDate dataNascimento) {
        this(null, nome, email, dataNascimento);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
