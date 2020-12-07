x = 12
if $true {
  fn yy { echo 'yy' }
  local:x = 10
  echo $local:<caret>
}