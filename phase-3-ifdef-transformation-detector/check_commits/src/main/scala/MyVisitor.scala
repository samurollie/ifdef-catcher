package ic.ufal.ifdefcatcher

import org.repodriller.domain.Commit
import org.repodriller.persistence.PersistenceMechanism
import org.repodriller.scm.{CommitVisitor, SCMRepository}

class MyVisitor extends CommitVisitor {
  override def process(repo: SCMRepository, commit: Commit, writer: PersistenceMechanism): Unit = {
    this.synchronized {
      writer.write(
        commit.getHash,
        commit.getAuthor.getName,
        commit.getAuthor.getEmail
      )
    }
  }
}
