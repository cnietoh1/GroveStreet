package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.Favorito;
import es.carlosnh.grovestreet.repositorios.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoritoService {

    private FavoritoRepository favoritoRepository;

    public List<Favorito> obtenerTodos() {
        return favoritoRepository.findAll();
    }

    public List<Favorito> obtenerPorUsuario(Long idUsuario) {
        return favoritoRepository.findByUsuarioId(idUsuario);
    }

    public Favorito crear(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public boolean eliminar(Long id) {
        try {
            favoritoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("No se pudo eliminar la tarea con ID: " + id);
        }
    }
}
