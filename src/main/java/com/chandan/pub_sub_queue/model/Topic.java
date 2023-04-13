package com.chandan.pub_sub_queue.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Topic {
    private final String topicName;
    private final String topicId;
    List<Message> messages;
    List<TopicSubscriber> subscribers;

    public Topic(String topicName, String topicId) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }
}
