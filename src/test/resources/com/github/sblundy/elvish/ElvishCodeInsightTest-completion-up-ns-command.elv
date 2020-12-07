x = 12
fn x { echo 'x' }
if $true {
  fn x { echo 'x' }
  up:<caret>
}