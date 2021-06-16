package ic.ufal.ifdefcatcher
package utils

import java.io.File

class Io {
  def createFolder(folderPath: String):Boolean = {
    val directory = new File(folderPath)
    (directory.exists && directory.isDirectory) || directory.mkdirs
  }
}
