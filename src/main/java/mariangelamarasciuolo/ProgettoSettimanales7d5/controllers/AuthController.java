package mariangelamarasciuolo.ProgettoSettimanales7d5.controllers;

import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Utente;
import mariangelamarasciuolo.ProgettoSettimanales7d5.exceptions.BadRequestException;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.AdminDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.UtenteDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.UtenteLoginDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.UtenteLoginSuccessDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.services.AuthService;
import mariangelamarasciuolo.ProgettoSettimanales7d5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginSuccessDTO login(@RequestBody UtenteLoginDTO body) {

        return new UtenteLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUtente(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Utente saveAdmin(@RequestBody @Validated AdminDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerAdmin(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}