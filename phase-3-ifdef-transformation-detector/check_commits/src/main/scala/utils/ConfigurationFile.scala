package ic.ufal.ifdefcatcher
package utils

import scala.io.Source
import scala.util.{Try, Using}

object ConfigurationFile {
  private val FILEPATH = "configuration.properties"
  private var FILTER_ON = false
  private var FILTER_PATH = ""

  def getProperties: Boolean = {
    val unit : Try[Unit] = Using(Source.fromFile(FILEPATH)) {
      source => {
        val it = source.getLines()
        while (it.hasNext) {
          val content = it.next
          if (content.strip.nonEmpty) {
            if (content.split("=").apply(0).strip.equals("FILTER_ON")) {
              try {
                FILTER_ON = content.split("=").apply(1).strip.toBoolean
              } catch {
                case _: Exception => FILTER_ON = false
              }
            } else if (content.split("=").apply(0).strip.equals("FILTER_PATH")) {
              FILTER_PATH = content.split("=").apply(1).strip
            }
          }
        }
      }
    }

    !unit.isFailure
  }

  def getFilterOn : Boolean = FILTER_ON
  def getFilterPath : String = FILTER_PATH
}
