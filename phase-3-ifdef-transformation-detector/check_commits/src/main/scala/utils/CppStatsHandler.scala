package ic.ufal.ifdefcatcher
package utils

import java.io.{File, FileWriter}
import scala.util.Random
import sys.process._

class CppStatsHandler(private val projectName:String, private val commitHash: String) {

  //<editor-fold desc="variables">

  private val CPPSTATS_ROOT_FOLDER = "CPPSTATS"

  private val executionFolder: String = CPPSTATS_ROOT_FOLDER + File.separator +
    projectName.replaceAll("(\\W|^_)*", "_").replaceAll(" ", "") +
    commitHash +
    Random.nextInt(Int.MaxValue)

  private val project1 = executionFolder + File.separator + "project1"
  private val project2 = executionFolder + File.separator + "project2"

  private val sourceFolder1 = project1 + File.separator + "source"
  private val sourceFolder2 = project2 + File.separator + "source"

  private val cppstatsFile = executionFolder + File.separator + "cppstats_input.txt"

  private val cppstatsReport1 = project1 + File.separator + "cppstats_discipline.csv"
  private val cppstatsReport2 = project2 + File.separator + "cppstats_discipline.csv"

  private val cppstatsCommand = "cppstats --kind discipline"

  //</editor-fold>

  /**
   * Prepare the environment to run cppstats. Run this first
   * @return true if success
   */
  def prepCppstatsDir() : Boolean = {
    createAllFolders && writeExecutionFile
  }

  /**
   * Run cppstats
   * @return true if success
   */
  def runCppstats(): Boolean = {
    0 == ("cd " + executionFolder + " && " + cppstatsCommand).!
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
    // TODO implement clean
  }

  //<editor-fold desc="private methods">

  private def getMetrics(path: String) : Metrics = {
    // TODO implement getMetrics
    null
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
