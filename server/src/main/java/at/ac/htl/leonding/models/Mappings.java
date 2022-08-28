package at.ac.htl.leonding.models;

import at.ac.htl.leonding.workloads.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Mappings {
    Mappings INSTANCE = Mappers.getMapper( Mappings.class );
    UserDOT userDTO(User user);
}
