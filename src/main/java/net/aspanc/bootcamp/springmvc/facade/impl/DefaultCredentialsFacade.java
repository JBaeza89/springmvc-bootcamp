package net.aspanc.bootcamp.springmvc.facade.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import net.aspanc.bootcamp.springmvc.facade.CredentialsFacade;
import net.aspanc.bootcamp.springmvc.services.CredentialsService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter(AccessLevel.PROTECTED)
@Component("defaultCredentialsFacade")
public class DefaultCredentialsFacade implements CredentialsFacade {

    private CredentialsService credentialsService;

    private Converter<Credentials, CredentialsData> converterCredentialsEntityIntoData;

    private Converter<CredentialsData, Credentials> converterCredentialsDataIntoEntity;

    public DefaultCredentialsFacade(CredentialsService credentialsService,
                                    Converter<Credentials, CredentialsData> converterCredentialsEntityIntoData,
                                    Converter<CredentialsData, Credentials> converterCredentialsDataIntoEntity) {

        this.credentialsService = credentialsService;
        this.converterCredentialsEntityIntoData = converterCredentialsEntityIntoData;
        this.converterCredentialsDataIntoEntity = converterCredentialsDataIntoEntity;
    }

    @Override
    public List<CredentialsData> findAll() {

        return getCredentialsService().findAll()
                .stream()
                .map(credentials -> getConverterCredentialsEntityIntoData().convert(credentials))
                .collect(Collectors.toList());
    }

    @Override
    public CredentialsData findOne(@NonNull final Long id) {

        return getConverterCredentialsEntityIntoData().convert(getCredentialsService().findOne(id)
                .orElseThrow());
    }

    @Override
    public List<CredentialsData> findByQuery(@NonNull final String filter) {

        return getCredentialsService().findByQuery(filter)
                .stream()
                .map(credentials -> getConverterCredentialsEntityIntoData().convert(credentials))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(@NonNull final Long id) {
        getCredentialsService().remove(id);
    }

    @Override
    public CredentialsData save(@NonNull final CredentialsData inputCredentials) {
        final Credentials entityCredentials = getConverterCredentialsDataIntoEntity().convert(inputCredentials);
        return getConverterCredentialsEntityIntoData().
                convert(getCredentialsService().save(entityCredentials));
    }
}
