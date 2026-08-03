package com.jazz.junit.nurzat.bankapp.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmsNotificationService implements NotificationService {

    private final List<String> sentMessages = new ArrayList<>();
    @Override
    public void notify(String owner, String massage) {
        String entry = "SMS to " + owner + ": " + massage;
        sentMessages.add(entry);
        System.out.println("[SMS] " + entry);

    }

    public List<String> getSentMessages() {
        return Collections.unmodifiableList(sentMessages);
    }

}
