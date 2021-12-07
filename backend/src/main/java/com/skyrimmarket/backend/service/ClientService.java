package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.repository.ClientRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClientService implements AuthorizationService {
    private final OrderService orderService;
    private final ClientRepository clientRepository;

    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(format("Client user not found by username: %s.", username)));
    }

    @Override
    public void checkThatOrderLinkedWithCurrentUser(String username, Long orderId) {
        orderService.findById(orderId).orElseThrow(() -> notFoundException(orderId));
    }

    @Override
    public void checkThatOrderLinkedWithCurrentUser(String username, Order order) {
        Client client = findByUsername(username);
        if (isNotCurrentClient(client, order.getClient())) {
            throw new BadRequestException("You cannot give access to this order");
        }
    }

    private boolean isNotCurrentClient(Client clientFromDb, Client clientLinkedWithOrder) {
        return !clientFromDb.equals(clientLinkedWithOrder);
    }
}
