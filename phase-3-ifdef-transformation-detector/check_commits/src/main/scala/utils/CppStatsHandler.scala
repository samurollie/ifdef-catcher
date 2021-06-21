package ic.ufal.ifdefcatcher
package utils

import org.apache.commons.io.FileUtils

import java.io.{File, FileWriter}
import scala.collection.mutable
import scala.io.Source
import scala.sys.process._
import scala.util.{Random, Try, Using}

class CppStatsHandler(private val projectName:String, private val commitHash: String) {

  //<editor-fold desc="variables">

  private val CPPSTATS_ROOT_FOLDER = "CPPSTATS"

  private val executionFolder: String = CPPSTATS_ROOT_FOLDER + File.separator +
    projectName.replaceAll("[^A-Za-z0-9]", "_").replaceAll(" ", "") +
    commitHash +
    Random.nextInt(Int.MaxValue)

  private val project1 = executionFolder + File.separator + "project1"
  private val project2 = executionFolder + File.separator + "project2"

  private val sourceFolder1 = project1 + File.separator + "source"
  private val sourceFolder2 = project2 + File.separator + "source"

  private val cppstatsFile = executionFolder + File.separator + "cppstats_input.txt"

  private val cppstatsReport1 = project1 + File.separator + "cppstats_discipline.csv"
  private val cppstatsReport2 = project2 + File.separator + "cppstats_discipline.csv"

  private val cppstatsCommand = "cppstats --list " + cppstatsFile + " --kind discipline"

  //</editor-fold>

  /**
   * Prepare the environment to run cppstats. Run this first
   * @return true if success
   */
  def prepCppstatsDir() : Boolean = {
    createAllFolders && writeExecutionFile
  }

  /**
   * This replace the current file in the source folder
   * @param file file to add in the source folder
   * @return true if success
   */
  def addFileProject1(file: File): Boolean = {
    copyFileToDestiny(file, new File(sourceFolder1 + File.separator + file.getName))
  }

  /**
   * This replace the current file in the source folder
   * @param file file to add in the source folder
   * @return true if success
   */
  def addFileProject2(file: File): Boolean = {
    copyFileToDestiny(file, new File(sourceFolder2 + File.separator + file.getName))
  }

  /**
   * Run cppstats
   * @return true if success
   */
  def runCppstats(): Boolean = {
    0 == (cppstatsCommand).!
  }

  /**
   * Get metrics of project 1. Run this after [[runCppstats]] method)
   * @return metrics of the project 1
   */
  def getMetricsProject1: Metrics = {
    getMetrics(cppstatsReport1)
  }

  /**
   * Get metrics of project 2. Run this after [[runCppstats]] method)
   * @return metrics of the project 2
   */
  def getMetricsProject2: Metrics = {
    getMetrics(cppstatsReport2)
  }

  /**
   * Delete all execution folder for this project, but no cppstats base folder.
   */
  def clean(): Unit = {
    val execFolder = new File(executionFolder)
    if (execFolder.exists() && execFolder.isDirectory)
      FileUtils.deleteDirectory(new File(executionFolder))
  }

  //<editor-fold desc="private methods">

  private def copyFileToDestiny(file : File, destinyFile: File): Boolean = {
    if (destinyFile.exists() && !destinyFile.delete) return false

    try {
      FileUtils.copyFile(file, destinyFile)
    } catch {
      case _: Exception => return false
    }

    true
  }

  private def getMetrics(path: String) : Metrics = {
    val report = new File(path)
    if (!report.exists() || report.isDirectory) {
      println("Error to get metrics")
      return null
    }

    var titles = ""
    var results = ""
    val unit: Try[Unit] = Using(Source.fromFile(report)) {
      source => {
        val it = source.getLines()
        var index = 0
        while (it.hasNext) {
          if (index == 0) titles = it.next else if (index == 1) results = it.next
          index += 1
        }
      }
    }
    if (unit.isFailure) {
      println("Error to get metrics")
      return null
    }

    val titleColumns = titles.split(";")
    val resultColumns = results.split(";")
    val map = new mutable.HashMap[String, Long]
    val validColumns = Array("loc", "compilationunit", "functiontype", "siblings", "overallblocks")
    for ((value, index) <- titleColumns.zipWithIndex) {
      if (validColumns.contains(value)) map.put(value, resultColumns.apply(index).toLong)
    }

    val disc = map.apply("compilationunit") + map.apply("functiontype") + map.apply("siblings")
    new Metrics(disc, map.apply("overallblocks") - disc, map.apply("loc"), map.apply("overallblocks"))
  }

  private def createAllFolders(): Boolean = {
    val io = new Io
    synchronized {
      io.createFolder(executionFolder) && io.createFolder(sourceFolder1) && io.createFolder(sourceFolder2)
    }
  }

  private def writeExecutionFile(): Boolean = {
    try {
      val fileWriter = new FileWriter(cppstatsFile)
      fileWriter.append(project1 + System.lineSeparator)
      fileWriter.append(project2 + System.lineSeparator)
      fileWriter.close()
    } catch {
      case _: Exception => return false
    }
    true
  }

  //</editor-fold>
}
