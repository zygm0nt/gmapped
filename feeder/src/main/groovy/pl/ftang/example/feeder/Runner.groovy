package pl.ftang.example.feeder

import org.apache.log4j.Logger
import pl.ftang.example.feeder.util.Config

class Runner {
    Logger log = Logger.getLogger(Runner.class);

    def config
    def counter

    Runner(args) {
        config = new Config(args)
        // initial values
        counter = config.counter.toInteger()
    }

    public static void main(String[] args) {
        Runner runner = new Runner(args);

        runner.run()
    }

    def run() {
        while (true) {
            List events = config.notesDAO.getEvents(counter)
            log.info(events)
            config.sender.send(events)
            Thread.sleep(60000)
        }
    }
}
