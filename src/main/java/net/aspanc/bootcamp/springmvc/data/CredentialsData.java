package net.aspanc.bootcamp.springmvc.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CredentialsData {

    private Long id;
    private String username;
    private String password;
}
