use re

fn status [&counts=$false]{
  is-o<caret>k = ?($git-status-cmd | eawk [line @f]{})

  result = [
    &is-git-repo=     (bool $is-ok)
  ]
}
