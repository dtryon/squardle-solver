# squardle-solver

Attempt to build a squaredle puzzle solver. Currently, using an english dictionary that does _not_ match
the dictionary used by squaredle (e.g., the official scrabble dictionary). Because of this, there are many
false positives and some words are not found.

### Notes

- it seems that you might not have to get paths for all points in a square. Since all corners, for example, will have the same paths transposed. Therefore, a 4x4 square will only have 4 unique point paths. With these unique points, it seems you can resolve the unique paths and then transpose the grid 4 times in order to generate all words. This would cut down the number of path iterations to 1/4 of an exhaustive search. However, you still need 4 unique points for a 3x3 grid. And you would not want to use all 4 when transposing.
