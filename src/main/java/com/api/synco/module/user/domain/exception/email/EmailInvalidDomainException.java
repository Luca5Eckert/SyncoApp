package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class EmailInvalidDomainException extends UserDomainException {

  public EmailInvalidDomainException(String message) {
    super(message);
  }

  public EmailInvalidDomainException() {
    super("The format of email is invalid. Please insert a correct");
  }


}
