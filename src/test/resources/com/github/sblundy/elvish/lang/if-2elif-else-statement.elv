if (has-suffix $fname .go) {
    echo go
} elif (has-suffix $fname .kt) {
    echo kotlin
} elif (has-suffix $fname .java) {
    echo java
} else {
    echo none
}