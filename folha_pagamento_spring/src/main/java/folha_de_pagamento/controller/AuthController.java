package folha_de_pagamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import folha_de_pagamento.model.user.UsuarioDoSistema;
import folha_de_pagamento.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String login = body.get("username");
        String senha = body.get("password");
        return usuarioRepository.findByLogin(login)
                .filter(u -> u.getSenha() != null && u.getSenha().equals(senha))
                .map(u -> {
                    Map<String, Object> resp = new java.util.HashMap<>();
                    resp.put("username", u.getLogin());
                    resp.put("role", u.getRole() != null ? u.getRole().name() : "FUNCIONARIO");
                    if (u.getFuncionario() != null) {
                        resp.put("funcionarioId", u.getFuncionario().getId());
                    }
                    resp.put("token", u.getLogin()); // token simples temporário
                    return ResponseEntity.ok(resp);
                })
                .orElseGet(() -> ResponseEntity.status(401).body(java.util.Map.of("error", "Credenciais inválidas")));
    }
}
