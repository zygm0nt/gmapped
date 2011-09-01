package pl.ftang.example.feeder.util

import org.springframework.jdbc.datasource.SingleConnectionDataSource
import pl.ftang.example.feeder.dao.JdbcNotesDao
import pl.ftang.example.feeder.mq.SenderImpl

/**
 * User: mcl
 * Date: 3/1/11 12:55 PM
 */
class Config {

  def cfgFile = "/config.properties"
  def sender
  def config
  def notesDAO

  Config(args) {
    config = new java.util.Properties();
    config.load(getClass().getResourceAsStream(cfgFile));

    Class.forName("oracle.jdbc.driver.OracleDriver")
    def ds = new SingleConnectionDataSource("jdbc:oracle:thin:@${config.dbUrl}", config.dbUser, config.dbPass, false)
    notesDAO = new JdbcNotesDao(dataSource : ds)
    sender = new SenderImpl(config.mqServer, config.mqQueue)
  }

  def propertyMissing(String name) {
      config.getProperty name
  }

  def get(String name) {
      config.getProperty name
  }

  def persist() {
      def configSlurper = new ConfigSlurper().parse(config)

      new File("/tmp/"+cfgFile).withWriter { writer ->
           configSlurper.writeTo(writer)
      }
  }

  def saveParams(counter) {
      config.counter = counter
  }
}
