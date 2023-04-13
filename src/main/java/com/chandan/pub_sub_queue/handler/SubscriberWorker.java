package com.chandan.pub_sub_queue.handler;

import com.chandan.pub_sub_queue.model.Message;
import com.chandan.pub_sub_queue.model.Topic;
import com.chandan.pub_sub_queue.model.TopicSubscriber;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                int currOffset = topicSubscriber.getOffset().get();
                while(currOffset >= topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicSubscriber.getSubscriber().consume(message);
                topicSubscriber.getOffset().compareAndSet(currOffset, currOffset + 1);
            } while(true);

        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
