package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Favorito;
import es.carlosnh.grovestreet.servicios.FavoritoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Slf4j
@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping
    public List<Favorito> obtenerTodos() {
        return favoritoService.obtenerTodos();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Favorito> obtenerFavoritosPorUsuario(@PathVariable Long idUsuario) {
        return favoritoService.obtenerPorUsuario(idUsuario);
    }

    @PostMapping
    public Favorito agregarAFavoritos(@RequestBody Favorito favorito) {
        return favoritoService.crear(favorito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDeFavoritos(@PathVariable Long id) {
        boolean eliminado = favoritoService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
