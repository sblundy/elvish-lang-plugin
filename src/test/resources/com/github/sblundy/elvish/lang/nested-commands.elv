for seg $segments {
  if $first {
    put (edit:styled $cfg[prefix] $ps)
    first = $false
  }
  put (edit:styled $cfg[segment-suffix] $ending-style)

  last-style = $ending-style
}
