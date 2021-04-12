#!/usr/bin/env python3
import os
import stat
import re
import sys
import semver
import subprocess


def git(*args):
    return subprocess.check_output(["git"] + list(args))


def commit(tag):

    url = os.environ["CI_REPOSITORY_URL"]

    git("config", "user.email", "gitlab-runner@gitlab.siilicloud.com")
    git("config", "user.name", "Gitlab Runner")

    # Transforms the repository URL to the SSH URL
    # Example input: https://gitlab-ci-token:xxxxxxxxxxxxxxxxxxxx@gitlab.com/threedotslabs/ci-examples.git
    # Example output: git@gitlab.com:threedotslabs/ci-examples.git
    push_url = re.sub(r'.+@([^/]+)/', r'git@\1:', url)

    git("remote", "set-url", "--push", "origin", push_url)

    # runner runs on a detached HEAD, checkout current branch for editing
    git("reset", "--hard")
    git("clean", "-df")
    git("checkout", "master")
    git("pull")

    update_manifest(tag)

    msg = os.environ['VERSION_BUMP_MSG']

    git("commit", "--all", "-m", msg)
    git("tag", "-a", tag, "-m", msg)

    try:
        git("push", "--follow-tags")

    except subprocess.CalledProcessError as e:
        print(e.output)
        raise


def update_manifest(tag):
    return subprocess.check_output(["version-info/update_manifest.sh", tag])

def bump(currentVer):
    # TODO: TT-235
    # As a Developer I want to be able to set which semver level I bump when I merge to master
    return semver.bump_minor(currentVer)


def main():
    try:
        git("fetch", "--all", "--tags")
        currentVer = git("describe", "--tags").decode().strip()[1:]
    except subprocess.CalledProcessError:
        # No tags in the repository
        releaseVer = "0.1.0"
    else:
        # Skip already tagged commits
        if '-' not in currentVer:
            print(currentVer)
            return 0

        releaseVer = bump(currentVer)

    tag = f"v{releaseVer}"
    commit(tag)

    print("Release version:")
    print(tag)

    return 0


if __name__ == "__main__":
    sys.exit(main())
