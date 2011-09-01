package pl.ftang.example.feeder.mq

/**
 * User: mcl
 * Date: 3/9/11 10:16 AM
 */
class TestSender {

    static void main(args) {
        new SenderImpl("localhost", "hello").send("Abc test").disconnect()
    }
}
