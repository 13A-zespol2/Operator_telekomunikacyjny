package psk.phone.operator.transport.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String email;
    private String password;


}
