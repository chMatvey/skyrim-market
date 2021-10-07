package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.service.error.UsernameAlreadyExist;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static com.skyrimmarket.backend.util.SecurityUtil.*;
import static com.skyrimmarket.backend.util.UserUtil.toUserDetails;
import static com.skyrimmarket.backend.util.UserUtil.toView;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/client")
    public ResponseEntity<SkyrimUser> createClient(@RequestBody Client client) {
        try {
            URI uri = URI.create(fromCurrentContextPath().path("/api/user/client").toUriString());
            Client user = (Client) userService.create(client);
            return created(uri).body(toView(user));
        } catch (UsernameAlreadyExist e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping("/employee")
    public ResponseEntity<SkyrimUser> createEmployee(@RequestBody Employee employee) {
        try {
            URI uri = URI.create(fromCurrentContextPath().path("/api/user/employee").toUriString());
            Employee user = (Employee) userService.create(employee);
            return created(uri).body(toView(user));
        } catch (UsernameAlreadyExist e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = getAuthorizationTokenOrThrowException(request);
        try {
            String username = usernameFromToken(refreshToken);
            SkyrimUser user = userService.getByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found", username)));
            String accessToken = createAccessToken(toUserDetails(user), request.getRequestURL().toString());
            Map<String, String> tokens = createTokensMap(accessToken, refreshToken);
            putTokensToResponseAsJson(response, tokens);
        } catch (Exception e) {
            addAuthorizationErrorToResponse(response, e);
        }
    }
}
