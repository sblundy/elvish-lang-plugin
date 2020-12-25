fn func [t]{
  use ./yy
  yy:x<caret>x = 'a'
  echo $yy:xx $t
}

func xx