# housing
testing authenticity of tweet

1) Baseline method - checking positive or negative count based on list of positive and negative words in repository
2) Window algorithm - Checking for negators before and after keyword match and using that to decide its positivity and negativity
3) POS method - Use a POS Tagger , then extract only the adjectives and do a keyword match
4) Turney method - Tag a comment using POS tagger and extract the keywords based on rules suggested by Turney and determine the polarity of the comment
5) Sentence level algorithms - Apply sentiment analysis techniques on individual sentences.
