package pl.ftang.example.feeder.mq

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import org.apache.log4j.Logger

/**
 * User: mcl
 * Date: 3/10/11 9:27 AM
 */
abstract class AbstractBaseMqObject {

    Logger log = Logger.getLogger(AbstractBaseMqObject.class);

    def queue
    def host

    def durable = true

    Channel channel
    Connection connection

    def AbstractBaseMqObject(host, queue) {
        this.host = host
        this.queue = queue
        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost(host)
        connection = factory.newConnection()
        channel = connection.createChannel()

        channel.queueDeclare(queue, durable, false, false, null)
        log.debug(" [*] Waiting for messages. To exit press CTRL+C")
        this
    }

    def disconnect() {
        channel.close();
        connection.close();
    }
}
