package net.aspanc.bootcamp.springmvc.validators;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.facade.CredentialsFacade;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter(AccessLevel.PROTECTED)
@Component
public class CredentialsValidator implements Validator {

    private final CredentialsFacade credentialsFacade;

    public CredentialsValidator(CredentialsFacade credentialsFacade) {
        this.credentialsFacade = credentialsFacade;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CredentialsData.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CredentialsData credentials = (CredentialsData) target;
        if (getCredentialsFacade().existByUsername(credentials.getUsername())) {
            errors.rejectValue("username", "ALREADY_EXIST", "mal");
        }
    }
}
