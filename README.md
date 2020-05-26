# Hangman
Computer plays hangman by a pretty simple algorithm.

## Quickstart
1. Clone repository
2. Run `./gradle build`
3. Run `./gradle run --console=plain` (plain console makes stdin neater)
4. Once prompted for `Word:`, enter a word that the computer will try to guess
5. Watch the computer guess the word

## How it works
- When deciding a new letter to guess, it:
  - Goes through the list of words from [dwyl/english-words](https://github.com/dwyl/english-words)
  - Finds all of the words that could possibly work
  - Finds the most common letter out of those words (that hasn't been guessed already)
  - Guesses that letter

## Output format
- For each word, the format is `character guessed: word so far`
- Candidate list shows a snapshot of the possible words *before* the next guess
  - Number of candidates shown at end of list
- Program finally outputs how many mistakes it made
