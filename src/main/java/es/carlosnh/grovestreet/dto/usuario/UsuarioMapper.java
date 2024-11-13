package es.carlosnh.grovestreet.dto.usuario;

import es.carlosnh.grovestreet.entidades.Usuario;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface UsuarioMapper {
    //@Mapping(target = "empleados", ignore=true)
    UsuarioSignupDto toDto(Usuario entity);

    Usuario toEntity(UsuarioSignupDto dto);
}
