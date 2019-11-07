package model;

public class Subscription {
    private Integer id;
    private User subscriber, subscription;

    public Integer getId() {
        return id;
    }

    public Subscription(Integer id, User subscriber, User subscription) {
        this.id = id;
        this.subscriber = subscriber;
        this.subscription = subscription;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public User getSubscription() {
        return subscription;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriber=" + subscriber.getUsername() +
                ", subscription=" + subscription.getUsername() +
                '}';
    }
}