package folha_de_pagamento.testes;

import org.junit.jupiter.api.Test;

import folha_de_pagamento.model.user.UsuarioDoSistema;

import org.junit.jupiter.api.Assertions;

public class UsuarioDoSistemaTest {

    @Test
    public void testFazerLogin_Sucesso() {
        UsuarioDoSistema usuario = new UsuarioDoSistema("user", "123456");
        Assertions.assertTrue(usuario.fazerLogin());
    }

    @Test
    public void testFazerLogin_Falha() {
        UsuarioDoSistema usuario = new UsuarioDoSistema("", "123");
        Assertions.assertFalse(usuario.fazerLogin());
    }

    @Test
    public void testDesconectar() {
        UsuarioDoSistema usuario = new UsuarioDoSistema("user", "123456");
        Assertions.assertFalse(usuario.desconectar());
    }
}
