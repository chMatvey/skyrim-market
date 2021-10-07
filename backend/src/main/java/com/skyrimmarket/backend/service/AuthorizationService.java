package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.skyrimmarket.backend.util.SecurityUtil.getAuthorizationTokenOrThrowException;
import static com.skyrimmarket.backend.util.SecurityUtil.usernameFromToken;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    public boolean isCurrentUser(HttpServletRequest request, SkyrimUser user) {
        String token = getAuthorizationTokenOrThrowException(request);
        String username = usernameFromToken(token);
        SkyrimUser userFromDb = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found by authorization token"));

        return user.equals(userFromDb);
    }

    public void checkThatOrderLinkedWithCurrentUser(HttpServletRequest request, Order order) {
        if (order.getClient() == null) {
            throw new BadRequestException("User not specified");
        }
        if (!isCurrentUser(request, order.getClient())) {
            throw new BadRequestException("Order cannot be created for another user");
        }
    }
}
