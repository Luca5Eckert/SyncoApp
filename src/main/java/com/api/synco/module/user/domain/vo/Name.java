package com.api.synco.module.user.domain.vo;

import com.api.synco.module.user.domain.exception.name.NameBlankException;
import com.api.synco.module.user.domain.exception.name.NameLengthException;

public record Name(String value) {

    public Name {
        if(value.isBlank()) throw new NameBlankException();

        if(value.length() > 30) throw new NameLengthException(30);

    }
}
