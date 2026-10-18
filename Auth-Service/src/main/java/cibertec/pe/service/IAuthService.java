package cibertec.pe.service;

import cibertec.pe.dto.AuthResponse;
import cibertec.pe.dto.LoginRequest;
import cibertec.pe.dto.RegisterRequest;
import cibertec.pe.dto.UserResponse;

import java.util.List;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    boolean validateToken(String token);
    List<UserResponse> listarUsuarios();
    UserResponse cambiarRol(Long userId, String nuevoRol);
}
