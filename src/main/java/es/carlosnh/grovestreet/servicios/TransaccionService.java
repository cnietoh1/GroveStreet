package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.Transaccion;
import es.carlosnh.grovestreet.repositorios.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public List<Transaccion> obtenerTodas() {
        return transaccionRepository.findAll();
    }

    public Transaccion obtenerPorId(Long id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    public Transaccion crear(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public boolean eliminar(Long id) {
        if (transaccionRepository.existsById(id)) {
            transaccionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
