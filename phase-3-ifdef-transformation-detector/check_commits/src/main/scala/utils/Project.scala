package ic.ufal.ifdefcatcher
package utils

import com.fasterxml.jackson.annotation.JsonProperty

import scala.collection.mutable
import scala.io.Source
import scala.util.{Try, Using}

class Project(
               @JsonProperty(required = true) val name: String,
               @JsonProperty(required = true) val url: String,
               @JsonProperty(required = true) val withThreads: Boolean,
               @JsonProperty(required = true) val startCommit: String,
               @JsonProperty(required = true) val endCommit: String
             )

object ProjectFilter {
  private val TURN_ON = false

  private val fileList: mutable.HashSet[String] = mutable.HashSet[String]()

  var totalCommits : Long = 0
  var countCommits : Long = 0

  def isEmpty: Boolean = !TURN_ON || fileList.isEmpty

  def contains(filename: String): Boolean = fileList.contains(filename)

  def buildFilter(projectName: String): Unit = {
    if (fileList.nonEmpty) { fileList.clear() }

    val unit: Try[Unit] = Using(Source.fromFile("filter.csv")) {
      source => {
        val it = source.getLines()

        while (it.hasNext) {
          val content = it.next

          if (content.split(",").head.strip.toLowerCase == projectName.toLowerCase) {
            val info = content.split(",").apply(1).strip
            fileList.add(info.split("/").last.strip)
          }
        }
      }
    }

    if (unit.isFailure) {
      println("Error to process filter.csv file")
    }
  }
}
