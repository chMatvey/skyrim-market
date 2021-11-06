package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;
import static com.skyrimmarket.backend.util.SecurityUtil.getAuthorizationTokenOrThrowException;
import static com.skyrimmarket.backend.util.SecurityUtil.usernameFromToken;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public void setCurrentUserToOrder(HttpServletRequest request, Order order) {
        SkyrimUser skyrimUser = getCurrentUser(request);
        order.setClient((Client) skyrimUser);
    }

    public void checkThatOrderLinkedWithCurrentUser(HttpServletRequest request, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> notFoundException(orderId));
        checkThatOrderLinkedWithCurrentUser(request, order);
    }

    public void checkThatOrderLinkedWithCurrentUser(HttpServletRequest request, Order order) {
        if (isNotCurrentUser(request, order.getClient())) {
            throw new BadRequestException("Order cannot be created for another user");
        }
    }

    private SkyrimUser getCurrentUser(HttpServletRequest request) {
        String token = getAuthorizationTokenOrThrowException(request);
        String username = usernameFromToken(token);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found by authorization token"));
    }

    private boolean isNotCurrentUser(HttpServletRequest request, SkyrimUser user) {
        SkyrimUser userFromDb = getCurrentUser(request);

        return !user.equals(userFromDb);
    }
}
