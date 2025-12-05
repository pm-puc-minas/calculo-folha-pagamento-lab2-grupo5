package folha_de_pagamento.model.user;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioDoSistema {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String login;
    
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.FUNCIONARIO;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public UsuarioDoSistema() {
    }

    public UsuarioDoSistema(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean fazerLogin() {
        boolean loginExiste = !this.login.isEmpty();
        boolean senhaValida = this.senha.length() >= 6;
        boolean loginValido = (loginExiste && !this.senha.isEmpty()) && senhaValida;

        if (!loginValido)
            return false;

        return true;
    }

    public boolean desconectar() {
        return false;
    }
}
