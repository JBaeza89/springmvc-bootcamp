package net.aspanc.bootcamp.springmvc.controller;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.facade.CredentialsFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Getter(AccessLevel.PROTECTED)
@RestController
public class UserController {

    private CredentialsFacade credentialsFacade;
    private Validator credentialsValidator;

    public UserController(CredentialsFacade credentialsFacade, Validator credentialsValidator) {
        this.credentialsFacade = credentialsFacade;
        this.credentialsValidator = credentialsValidator;
    }

    @RequestMapping(value = "/admin/rest/users", method = RequestMethod.GET)
    public List<CredentialsData> getUsers( @RequestParam(value = "q", required = false) final String query) {
        if (query != null) {
            return credentialsFacade.findByQuery(query);
        }
        return credentialsFacade.findAll();
    }

    private ResponseEntity<List<String>> checkValidationAndSaveUser(final CredentialsData credentials,
                                                      final BindingResult bindingResult) {
        try {
            getCredentialsValidator().validate(credentials, bindingResult);
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(400).body(
                        bindingResult.getFieldErrors()
                                .stream()
                                .map(fieldError -> fieldError.toString())
                                .collect(Collectors.toList()));
            }
            if (credentialsFacade.existById(credentialsFacade.save(credentials).getId())) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/rest/user", method = RequestMethod.POST)
    public ResponseEntity<List<String>> createUser(@Valid @RequestBody final CredentialsData credentials,
                                                   final BindingResult bindingResult) {
        return checkValidationAndSaveUser(credentials, bindingResult);

    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable final Long userId) {
        if (getCredentialsFacade().existById(userId)) {
            return ResponseEntity.ok().body(getCredentialsFacade().findOne(userId));
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<List<String>> updateUser(@PathVariable final Long userId,
                                     @Valid @RequestBody CredentialsData credentials,
                                     BindingResult bindingResult) {

        credentials.setId(userId);
        if (getCredentialsFacade().existById(userId)) {
            return checkValidationAndSaveUser(credentials, bindingResult);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable final Long userId) {
        if (getCredentialsFacade().existById(userId)) {
            getCredentialsFacade().remove(userId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
