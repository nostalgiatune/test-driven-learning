#!/bin/bash

mkdir -p ~/.ssh && chmod 700 ~/.ssh
ssh-keyscan ${GITLAB_HOST} >> ~/.ssh/known_hosts && chmod 644 ~/.ssh/known_hosts

eval $(ssh-agent -s)

echo "$SSH_PRIVATE_KEY_GITLAB" | tr -d '\r' | ssh-add -