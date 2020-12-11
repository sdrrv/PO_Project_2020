package woo.core.notifications;

import java.io.Serializable;

public interface NotificationsObserver{
    void receiveNotification(Notification notification);
}
