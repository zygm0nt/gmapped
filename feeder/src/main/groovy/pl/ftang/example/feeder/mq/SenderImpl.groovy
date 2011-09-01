package pl.ftang.example.feeder.mq

import org.apache.log4j.Logger
import com.rabbitmq.client.MessageProperties

/**
 * User: mcl
 * Date: 3/7/11 8:59 AM
 */
class SenderImpl extends AbstractBaseMqObject implements Sender {

    Logger log = Logger.getLogger(SenderImpl.class);

    def SenderImpl(host, queue) {
        super(host, queue)
    }

    def send(msg) {
        channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes())
        log.debug(" [x] Sent '" + msg + "'")
        this
    }

    def send(List msgs) {
        msgs.each { send(it)}
    }
}
