package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.input.IAuthServicePort;
import com.pragma.user_service.application.ports.output.IUserAuthPersistencePort;
import com.pragma.user_service.application.ports.output.ITokenServicePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.exception.UserNotFoundException;
import com.pragma.user_service.domain.exception.InvalidCredentialsException;
import com.pragma.user_service.domain.exception.ClientNotAuthorizedException;
import com.pragma.user_service.domain.exception.RoleNotFoundException;
import static com.pragma.user_service.domain.util.ExceptionMessages.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase implements IAuthServicePort {
    private final IUserAuthPersistencePort userAuthPersistencePort;
    private final ITokenServicePort tokenServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        User user = userAuthPersistencePort.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!user.isEnabled()) {
            throw new ClientNotAuthorizedException(USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new RoleNotFoundException();
        }
        return tokenServicePort.generateToken(user);
    }
}
