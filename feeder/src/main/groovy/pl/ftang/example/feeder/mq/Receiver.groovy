package pl.ftang.example.feeder.mq

/**
 * User: mcl
 * Date: 3/10/11 9:25 AM
 */
interface Receiver {

    def addListeners(MessageListener obj);

    def receive();
}
