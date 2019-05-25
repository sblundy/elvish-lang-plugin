if (has-suffix $fname .go) {
    echo go
} elif (has-suffix $fname .kt) {
    echo kotlin
} else {
    echo none
}