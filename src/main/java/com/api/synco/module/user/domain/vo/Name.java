package com.api.synco.module.user.domain.vo;

import com.api.synco.module.user.domain.exception.name.NameBlankDomainException;
import com.api.synco.module.user.domain.exception.name.NameLengthDomainException;

public record Name(String value) {

    public Name {
        if(value.isBlank()) throw new NameBlankDomainException();

        if(value.length() > 30) throw new NameLengthDomainException(30);

    }
}
