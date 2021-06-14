package ic.ufal.ifdefcatcher

import org.repodriller.RepoDriller

object Main {
  def main(args: Array[String]): Unit = {
    new RepoDriller().start(new MyStudy())
  }
}
