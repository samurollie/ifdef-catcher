package ic.ufal.ifdefcatcher

import utils.ProjectFilter

import org.repodriller.domain.Commit
import org.repodriller.persistence.PersistenceMechanism
import org.repodriller.scm.{CommitVisitor, RepositoryFile, SCMRepository}

import scala.annotation.tailrec
import scala.jdk.CollectionConverters.CollectionHasAsScala

class MyVisitor extends CommitVisitor {

  @tailrec
  private def visitFile(files: Iterable[RepositoryFile], repo: SCMRepository, commit: Commit,
                        writer: PersistenceMechanism): Unit = {
    if (files.isEmpty) return
    val file = files.head
    val realFile = file.getFile

    if ((file.fileNameEndsWith(".c") || file.fileNameEndsWith(".h") ||
      file.fileNameEndsWith(".C") || file.fileNameEndsWith(".H")) &&
      (ProjectFilter.isEmpty || ProjectFilter.contains(realFile.getName)))
    {
      // TODO create cppstats structure folder
      // copy file to another location
      // get checkout parent commit
      // find same file and copy to another location
      // if two files, run cppstats on each and get stats (maybe Romero script?)
      // clean source

      // TODO remove later
      println(realFile.getName)
    }

    visitFile(files.tail, repo, commit, writer)
  }

  override def process(repo: SCMRepository, commit: Commit, writer: PersistenceMechanism): Unit = {
    try {
      repo.getScm.checkout(commit.getHash)
      val files = repo.getScm.files
      this.visitFile(files.asScala, repo, commit, writer)
    } finally {
      repo.getScm.reset()
    }
  }
}
