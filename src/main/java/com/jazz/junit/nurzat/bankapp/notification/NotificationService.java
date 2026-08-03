package com.jazz.junit.nurzat.bankapp.notification;

public interface NotificationService {

    void notify(String owner,String massage);

    default void notifyAll(Iterable<String> owners,String massage){
        for (String owner:owners){
            notify(owner,massage);
        }
    }
}
