import json

# open the text file for reading
with open("engmix.txt") as f:
    # read all the lines into a list
    lines = f.readlines()
    # use a list comprehension to filter out the words that are not 5 letters long
    words = [line.strip() for line in lines if len(line.strip()) == 5]
    # create a dictionary with the key "words" and the value as the list of words
    data = {"words": words}
    # open the json file for writing
    with open("english_words_5.json", "w") as g:
        # write the dictionary to the json file using dump()
        json.dump(data, g, indent=2)
