package com.skyrimmarket.backend.service.notification;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.notification.model.ClientOrderNotificationData;
import com.skyrimmarket.backend.service.notification.model.SkyrimNotificationMessage;
import com.skyrimmarket.backend.web.error.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.fromString;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class ClientOrderNotificationService {
    private final NotificationService notificationService;
    private final NotificationTokenManager tokenManager;

    public static final String TITLE = "Order status updated";

    private final Map<String, List<SkyrimNotificationMessage>> waitingMessages = new ConcurrentHashMap<>();

    public void sendOrderStatusUpdatedNotificationToClient(Order order) {
        ClientOrderNotificationData notificationData =
                new ClientOrderNotificationData(order.getId().toString(), order.getStatus().getName());
        SkyrimNotificationMessage orderUpdatedNotification =
                new SkyrimNotificationMessage(TITLE, orderStatusUpdatedBodyByStatusName(order.getStatus()));

        orderUpdatedNotification.setObjectData(notificationData);

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("orderId", notificationData.getOrderId());
        dataMap.put("orderStatus", notificationData.getOrderStatus());
        orderUpdatedNotification.setMapData(dataMap);

        String clientUsername = order.getClient().getUsername();
        Optional<String> personalTokenOptional = ofNullable(tokenManager.getToken(clientUsername));

        if (personalTokenOptional.isPresent()) {
            notificationService.sendPersonal(orderUpdatedNotification, personalTokenOptional.get());
        } else {
            List<SkyrimNotificationMessage> userMessages =
                    waitingMessages.computeIfAbsent(clientUsername, (username) -> new LinkedList<>());
            userMessages.add(orderUpdatedNotification);
        }
    }

    public void sendWaitingMessagesToUser(String username) {
        String token = ofNullable(tokenManager.getToken(username))
                .orElseThrow(() -> new InternalServerErrorException(format("Cannot extract firebase token for user: %s.", username)));
        ofNullable(waitingMessages.remove(username))
                .ifPresent(messages -> messages.forEach(msg -> notificationService.sendPersonal(msg, token)));
    }

    private String orderStatusUpdatedBodyByStatusName(OrderStatus orderStatus) {
        switch (fromString(orderStatus.getName())) {
            case NEED_CHANGES:
                return "Changes required";
            case APPROVED:
                return "Order approved. Payment is expected.";
            case IN_PROGRESS:
                return "Order execution started.";
            case DECLINED:
                return "Order declined.";
            case COMPLETED:
                return "Order completed!";
        }
        throw new IllegalArgumentException(format("Unexpected order status name: %s", orderStatus.getName()));
    }
}
