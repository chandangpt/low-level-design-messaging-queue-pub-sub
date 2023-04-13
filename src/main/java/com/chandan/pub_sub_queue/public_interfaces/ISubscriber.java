package com.chandan.pub_sub_queue.public_interfaces;

import com.chandan.pub_sub_queue.model.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
