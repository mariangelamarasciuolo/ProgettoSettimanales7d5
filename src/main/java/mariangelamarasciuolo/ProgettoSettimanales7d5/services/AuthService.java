package mariangelamarasciuolo.ProgettoSettimanales7d5.services;

import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Role;
import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Utente;
import mariangelamarasciuolo.ProgettoSettimanales7d5.exceptions.BadRequestException;
import mariangelamarasciuolo.ProgettoSettimanales7d5.exceptions.UnauthorizedException;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.AdminDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.UtenteDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.UtenteLoginDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.repository.UtenteRepository;
import mariangelamarasciuolo.ProgettoSettimanales7d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteRepository utenteRepository;


    public String authenticateUser(UtenteLoginDTO body) {
        Utente utente = utenteService.findByEmail(body.email());

        if (body.password().equals(utente.getPassword())) {

            return jwtTools.createToken(utente);
        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }


    }

    public Utente registerUtente(UtenteDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già utilizzata!");
        });

        Utente newUtente = new Utente();
        newUtente.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUtente.setName(body.name());
        newUtente.setUsername(body.username());
        newUtente.setSurname(body.surname());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setEmail(body.email());
        newUtente.setRole(Role.UTENTE);
        Utente savedUtente = utenteRepository.save(newUtente);
        return savedUtente;
    }

    public Utente registerAdmin(AdminDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già utilizzata!");
        });

        Utente newUtente = new Utente();
        newUtente.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUtente.setName(body.name());
        newUtente.setUsername(body.username());
        newUtente.setSurname(body.surname());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setEmail(body.email());
        newUtente.setRole(Role.ADMIN);
        Utente savedUtente = utenteRepository.save(newUtente);
        return savedUtente;
    }

}
