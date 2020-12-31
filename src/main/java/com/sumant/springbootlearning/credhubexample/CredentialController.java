package com.sumant.springbootlearning.credhubexample;

import org.springframework.credhub.support.SimpleCredentialName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService){
        this.credentialService = credentialService;
    }

    @GetMapping ("/passwd")
    public String getCredentialsFromCredHub() {
        SimpleCredentialName simpleCredentialName = new SimpleCredentialName("SumantCredentials");
        return credentialService.getCredentialByName(simpleCredentialName);
    }

    @PostMapping("/passwd")
    public String setCredentialsInCredHub(){
        SimpleCredentialName simpleCredentialName = new SimpleCredentialName("test", "password");
        return credentialService.generatePassword(simpleCredentialName);
    }

    @GetMapping("/")
    public String getVcapEnvironment(){
        String vcapEnvironment = System.getenv("VCAP_SERVICES");
        return vcapEnvironment;
    }
}
