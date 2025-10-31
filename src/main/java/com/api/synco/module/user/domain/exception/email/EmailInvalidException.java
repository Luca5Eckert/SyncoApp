package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserException;

public class EmailInvalidException extends UserException {

  public EmailInvalidException(String message) {
    super(message);
  }

  public EmailInvalidException() {
    super("The format of email is invalid. Please insert a correct");
  }


}
