x = 12
fn test1 {
  fn yy { echo 'yy' }
  local:x = 10
  echo $local:<caret>
}