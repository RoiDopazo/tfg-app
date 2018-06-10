package es.udc.rdopazo.tfg.app.service.core.usuario.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.util.security.MyEncryptorService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioPersistDto;

@Component
public class UsuarioPersistEntityDtoConverter<U extends Usuario, DT extends UsuarioPersistDto>
        extends DefaultEntityDtoConverterSupport<DT, U> {
    @Autowired
    private MyEncryptorService encryptor;

    @Override
    protected Class<?> getEntityClass() {
        return JpaUser.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return UsuarioPersistDto.class;
    }

    @Override
    public DT toDto(U entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setPassword(this.encryptor.decrypt(entity.getPassword()));
        return dto;
    }

}
