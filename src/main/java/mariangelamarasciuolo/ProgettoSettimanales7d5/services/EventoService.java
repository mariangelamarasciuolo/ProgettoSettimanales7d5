package mariangelamarasciuolo.ProgettoSettimanales7d5.services;

import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Eventi;
import mariangelamarasciuolo.ProgettoSettimanales7d5.exceptions.NotFoundException;
import mariangelamarasciuolo.ProgettoSettimanales7d5.payload.EventiDTO;
import mariangelamarasciuolo.ProgettoSettimanales7d5.repository.EventoRepository;
import mariangelamarasciuolo.ProgettoSettimanales7d5.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UtenteRepository utenteRepository;


    public Eventi save(EventiDTO body) {
        Eventi newEvento = new Eventi();

        newEvento.setTitolo(body.titolo());
        newEvento.setDescrizione(body.descrizione());
        newEvento.setLuogo(body.luogo());
        newEvento.setDataEvento(body.dataEvento());
        newEvento.setPostiDisponibili(body.postiDisponibili());
        return eventoRepository.save(newEvento);
    }

    public Page<Eventi> getEventi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventoRepository.findAll(pageable);
    }

    public Eventi findById(int id) {

        return eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Eventi findAndUpdateById(int id, Eventi body) {
        Eventi foundD = this.findById(id);

        foundD.setId(id);
        foundD.setTitolo(body.getTitolo());
        foundD.setDescrizione(body.getDescrizione());
        foundD.setDataEvento(body.getDataEvento());
        foundD.setLuogo(body.getLuogo());
        foundD.setPostiDisponibili(body.getPostiDisponibili());


        return foundD;

    }

    public void findAndDeleteById(int id) {
        Eventi foundE = this.findById(id);
        eventoRepository.delete(foundE);
    }

}

