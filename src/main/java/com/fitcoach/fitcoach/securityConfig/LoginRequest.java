package com.fitcoach.fitcoach.securityConfig;


import com.fitcoach.fitcoach.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    String logintype;
    String email;
    String password;
    String refreshToken;

}
