package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.ClientDto;
import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;

import java.util.stream.Collectors;

public class ClientUtil {

    public static Client fromTo(ClientDto clientDto) {
        return new Client(clientDto.getId());
    }

    public static Client fromUserTo(User user) {
        return new Client(
                user.getUsername(),
                user.getPassword(),
                Role.CLIENT
        );
    }
    public static ClientDto asTo(Client client) {
        return new ClientDto(
                client.getId(),
                client.getUsername(),
                client.getOrders().stream().map(OrderUtil::asTo).collect(Collectors.toSet())
        );
    }

}
