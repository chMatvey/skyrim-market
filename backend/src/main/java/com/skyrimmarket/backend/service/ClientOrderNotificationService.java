package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.notification.NotificationService;
import com.skyrimmarket.backend.service.notification.NotificationTokenManager;
import com.skyrimmarket.backend.service.notification.SkyrimNotificationMessage;
import com.skyrimmarket.backend.service.notification.model.ClientOrderNotificationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.fromString;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClientOrderNotificationService {
    private final NotificationService notificationService;
    private final NotificationTokenManager tokenManager;

    public void sendOrderStatusUpdatedNotificationToClient(Order order) {
        ClientOrderNotificationData notificationData = new ClientOrderNotificationData(order.getId(), order.getStatus());
        SkyrimNotificationMessage<ClientOrderNotificationData> orderUpdatedNotification = new SkyrimNotificationMessage<>(
                "Order status updated",
                orderStatusUpdatedBodyByStatusName(order.getStatus()),
                notificationData
        );
        String personalToken = tokenManager.getToken(order.getClient().getUsername());

        notificationService.sendPersonal(orderUpdatedNotification, personalToken);
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
