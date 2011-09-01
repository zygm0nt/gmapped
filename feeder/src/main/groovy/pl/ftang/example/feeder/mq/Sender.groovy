package pl.ftang.example.feeder.mq

/**
 * User: mcl
 * Date: 3/7/11 8:58 AM
 */
interface Sender {
    def send(Object msg)

    def send(List msgs)
}
