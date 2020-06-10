package net.aspanc.bootcamp.springmvc.controller;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.facade.CredentialsFacade;
import org.springframework.http.ResponseEntity;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(getCredentialsValidator());
    }

    @RequestMapping(value = "/admin/rest/users", method = RequestMethod.GET)
    public List<CredentialsData> getAllUsers() {
        return credentialsFacade.findAll();
    }

    @RequestMapping(value = "/admin/rest/users?q=", method = RequestMethod.GET)
    public List<CredentialsData> getAllUsersByQuery(@RequestParam(value = "q") String query) {
        return credentialsFacade.findByQuery(query);
    }

    @RequestMapping(value = "/admin/rest/user", method = RequestMethod.POST)
    public ResponseEntity createUser(@Valid @ModelAttribute("credentials") CredentialsData credentials,
                                             BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(400).body(
                        bindingResult.getFieldErrors()
                                .stream()
                                .map(fieldError -> fieldError.toString())
                                .collect(Collectors.toList()));
            }
            if (credentialsFacade.existById(credentialsFacade.save(credentials).getId())) {
                return ResponseEntity.status(201).body(null);
            }
            return ResponseEntity.status(500).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable Long userId) {
        if (getCredentialsFacade().existById(userId)) {
            return ResponseEntity.ok().body(getCredentialsFacade().findOne(userId));
        }
        return ResponseEntity.status(404).body(null);
    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable Long userId,
                                     @Valid @ModelAttribute("credentials") CredentialsData credentials,
                                     BindingResult bindingResult) {
        if (getCredentialsFacade().existById(userId)) {
            try {
                if (bindingResult.hasErrors()) {
                    return ResponseEntity.status(400).body(
                            bindingResult.getFieldErrors()
                                    .stream()
                                    .map(fieldError -> fieldError.toString())
                                    .collect(Collectors.toList()));
                }
                return ResponseEntity.status(201).body(null);
            } catch (Exception ex) {
                return ResponseEntity.status(500).body(null);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @RequestMapping(value = "/admin/rest/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        if (getCredentialsFacade().existById(userId)) {
            getCredentialsFacade().remove(userId);
            return ResponseEntity.status(200).body(getCredentialsFacade().findOne(userId));
        }
        return ResponseEntity.status(404).body(null);
    }


}
