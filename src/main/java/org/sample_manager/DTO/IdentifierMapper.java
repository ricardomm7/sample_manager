package org.sample_manager.DTO;

import org.sample_manager.Domain.Identifier;

import java.util.ArrayList;
import java.util.List;

public class IdentifierMapper {

    public static IdentifierDTO toDTO(Identifier o) {
        return new IdentifierDTO(o.getMainName(), o.getIdPrefixField());
    }

    public static Identifier toDomain(IdentifierDTO i) {
        Identifier s1 = new Identifier(i.mainName);
        s1.setIdPrefixField(i.idPrefixField);
        return s1;
    }

    public static List<IdentifierDTO> toDTOList(List<Identifier> i) {
        List<IdentifierDTO> z = new ArrayList<>();
        for (Identifier y : i) {
            z.add(new IdentifierDTO(y.getMainName(), y.getIdPrefixField()));
        }
        return z;
    }
}
