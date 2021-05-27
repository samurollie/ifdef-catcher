from git import Repo


def clone(url, destiny):
    return Repo.clone_from(url, destiny)