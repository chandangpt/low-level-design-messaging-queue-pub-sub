package com.chandan.pub_sub_queue;

import com.chandan.pub_sub_queue.model.Message;
import com.chandan.pub_sub_queue.model.Topic;
import com.chandan.pub_sub_queue.public_interfaces.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        Topic topic1 = queue.createTopic("t1");
        Topic topic2 = queue.createTopic("t2");
        SleepingSubscriber sub1 = new SleepingSubscriber("sub1", 10000);
        SleepingSubscriber sub2 = new SleepingSubscriber("sub2", 10000);
        queue.subscribe(sub1, topic1);
        queue.subscribe(sub2, topic1);
        SleepingSubscriber sub3 = new SleepingSubscriber("sub3", 5000);
        queue.subscribe(sub3, topic2);
        queue.publish(topic1, new Message("m1"));
        queue.publish(topic1, new Message("m2"));
        queue.publish(topic2, new Message("m3"));
        Thread.sleep(15000);
        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));
        queue.resetOffset(topic1, sub1, 0);
    }
}
