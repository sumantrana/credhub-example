package com.sumant.springbootlearning.credhubexample;


import org.springframework.credhub.core.CredHubOperations;
import org.springframework.credhub.support.CredentialDetails;
import org.springframework.credhub.support.SimpleCredentialName;
import org.springframework.credhub.support.StringCredential;
import org.springframework.credhub.support.password.PasswordCredential;
import org.springframework.credhub.support.password.PasswordParameters;
import org.springframework.credhub.support.password.PasswordParametersRequest;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final CredHubOperations credHubOperations;

    public CredentialService(CredHubOperations credHubOperations) {
        this.credHubOperations = credHubOperations;
    }

    public String generatePassword(SimpleCredentialName simpleCredentialName) {
        PasswordParameters parameters = PasswordParameters.builder().length(12).excludeLower(false).excludeUpper(false)
                .excludeNumber(false).includeSpecial(true).build();

        CredentialDetails<PasswordCredential> password = this.credHubOperations.credentials()
                .generate(PasswordParametersRequest.builder().name(simpleCredentialName).parameters(parameters).build());

        return password.getValue().getPassword();
    }

    public String getPassword(SimpleCredentialName simpleCredentialName) {
        CredentialDetails<PasswordCredential> password = this.credHubOperations.credentials()
                .getByName(simpleCredentialName, PasswordCredential.class);

        return password.getValue().getPassword();
    }

    public String getCredentialByName(SimpleCredentialName simpleCredentialName) {
        CredentialDetails<StringCredential> stringCredential = this.credHubOperations.credentials().getByName(simpleCredentialName, StringCredential.class);
        return stringCredential.getValue().toString();
    }

}
