package mariangelamarasciuolo.ProgettoSettimanales7d5.controllers;

import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Eventi;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.EventiDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public Page<Eventi> getEvento(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return eventoService.getEventi(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Eventi saveEvento(@RequestBody EventiDTO body) {
        return eventoService.save(body);
    }

    @GetMapping("/{id}")
    public Eventi findById(@PathVariable int id) {
        return eventoService.findById(id);
    }

    @PutMapping("/{id}")
    public Eventi findAndUpdateById(@PathVariable int id, @RequestBody Eventi body) {
        return eventoService.findAndUpdateById(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteById(@PathVariable int id) {
        eventoService.findAndDeleteById(id);
    }

}
