package pl.ftang.example.feeder.mq

import com.rabbitmq.client.QueueingConsumer
/**
 * User: mcl
 * Date: 3/10/11 9:26 AM
 */
class ReceiverImpl extends AbstractBaseMqObject implements Receiver {

    boolean autoAck = false;

    def listeners = []
    
    def ReceiverImpl(host, queue) {
        super(host, queue)
    }

    def addListeners(MessageListener obj) {
        listeners.add(obj)
    }

    def receive() {
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queue, autoAck, consumer);

        while (true) {
            log.debug(" [o] waiting for next message");
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            log.debug(" [x] Received '" + message + "'")
            listeners.each { it.onEvent(toEvent(message))}
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    private def toEvent(msg) {
        new Event(content:msg)
    }

}
