package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
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

    public void checkThatOrderLinkedWithCurrentClient(HttpServletRequest request, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> notFoundException(orderId));
        checkThatOrderLinkedWithCurrentClient(request, order);
    }

    public void checkThatOrderLinkedWithCurrentClient(HttpServletRequest request, Order order) {
        if (isNotCurrentClient(request, order.getClient())) {
            throw new BadRequestException("Order cannot be created for another user");
        }
    }

    public SkyrimUser getCurrentUser(HttpServletRequest request) {
        String token = getAuthorizationTokenOrThrowException(request);
        String username = usernameFromToken(token);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found by authorization token"));
    }

    private boolean isNotCurrentClient(HttpServletRequest request, SkyrimUser user) {
        SkyrimUser userFromDb = getCurrentUser(request);

        return !user.equals(userFromDb);
    }
}
