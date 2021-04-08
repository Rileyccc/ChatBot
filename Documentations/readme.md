This folder will contain all documentations. All documentation requirements for Assignment 3 are within this ReadMe.
# Required project plan documentations: #  
(Assignment 2)

1. SDLC rationale  
2. Listing of all phases of SDLC  
3. WBS  
4. Gantt Chart
5. Role Assignments
6. Hour estimation/actuals
7. Project Meeting/meeting documentation
8. System documentations:   
   a)Class explanation  
   b)Class hierarchy diagram  
   c)System limitations  

(Assignment 3)


9. Level 0 Data Flow Diagram (Explanation Within ReadMe)
10. Level 1 Data Flow Diagram  (Explanation Within ReadMe)
11. Explanation of Additions in Assignment 3
4. 30 Rounds of Dialogue
5. Limitations with Examples
6. 5 Features that Could b extracted and shared with others as an API
7. Automated Unit Testing

(Individual Project)

12. Added API's

# Added API's#
1. Wikipedia API

   This API allows access to wikipedia's extensive database of knowledge. The addition of this API allows for questions which start with "who is person" and "what is thing" and    if something in wikipedia database matches the first two sentences of the wikipedia article will be outputed as an answer

![alt text](https://github.com/Rileyccc/ChatBot/blob/main/Documentations/WikipediaAPI%20example.png?raw=true)

2. Wolfram Short Answers API

   This API allows access to Wolfram alpha short answers. The addition of this API allows answers to various questions about facts, mathematics and more. If the program can find    no in house answer it will check to see if Wolfram has an answer to this question.

![alt text](https://github.com/Rileyccc/ChatBot/blob/main/Documentations/WolframAPI%20example.png?raw=true)

3. IMDb Api
    
    This API allows access to IMDb database of movies. The addition of this API allows questions which start with "have you seen movietitle" and if a move is found with   
    "movietitle" the bot will return "of course!"  followed by the plot of the movie.
   
![alt text](https://github.com/Rileyccc/ChatBot/blob/main/Documentations/IMDbAPI%20example.png?raw=true)

4. Openweathermap Api 
   
   This API allows for access to various weather infromation. The addition of this API allows questions which start with "whats the current weather in city (or city, country tag    ex CA = canada). The Bot will return the weather description and the current temperture. 

![alt text](https://github.com/Rileyccc/ChatBot/blob/main/Documentations/WeatherAPI%20example.png?raw=true)

# Level 0 Data Flow Diagram #

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Level%200%20Data%20Flow%20Diagram.png?raw=true)

This dataflow diagram shows a generalized overview of how information is passed through the chatbot. The diagram generalizes classes and their functions into processes which are labeled with verbs, while data and their sources are labelled with nouns. 

The input is taken in from the user through the GUI and is then broken up into Parts of Speech. The input is then analyzed to produce a response through several processes. First, the input is checked for sentiment and a sentimental response is returned if so. Next the input is checked for containing a name. Next if no name is present, the input is "stemmed" or each word of the input is turned into a root for easier analysis. If there is a name present, the input is not stemmed and the name is identified and used in a response phrase including that name. Finally, a list of keywords and phrases are passed and used to produce a response if the other methods have not been able to and a canned response is used if this also does not produce a response. The response is then printed on the GUI for the user to see.

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/fd0bcfeda9833c60a80bb416c56657d18d4f362c/Documentations/Level%201%20Data%20Flow%20Diagram.png?raw=true)

This dataflow diagram shows a detailed view of how data is passed through the program at a class level. This diagram also uses verb phrases for processes and nouns for data and data sources, but it also identifies the class in which each process is performed in bolded text. Processes are grouped together based on each class to aid clarity, with the exception of the two uses of the PersonFinder class as one of them is reliant on the returned value of the other.

The User gives input through the GUI which is facilitated by the Contents Class and this input is then passed on to the OutputTerminal Class. The input is passed on to the POSTagger Class as well as the Chatbot Class. The POSTagger class retrieves the sentence tokens and parts of speech from the .bin files within the models folder, breaks up and identifies the parts of speech of the input and then prints these parts of speech to the console. The Chatbot class takes the input from the OutputTerminal Class and uses four different classes in it's analysis and production of a response. Firstly the input is passed to the SentimentAnalyzer Class if the phrase begins with "you" and returns a sentimental response based on the degree of sentiment. Next the input is passed to the PersonFinder Class and a boolean determining whether a name is present in the input is returned. Next if the PersonFinder Class was unable to find a name, the input is passed to the Stemmer Class where each word of the input is broken into their roots to aid analysis. If the PersonFinder class did find a name, then the input is passed to the PersonFinder class again and the name is used in a response format that is returned. Finally, the Rules Class retrieves the list of keywords and phrases from the word.txt file and passes this list to the Chatbot Class. If none of the previous classes have produced a response and a response can't be chosen from the list based on the topic keywords, then the Chatbot class will randomly pick one of five canned responses. The response is then returned to the OutputTerminal Class where both the input and response are printed on the GUI for the User to see.

# List of Features Added

1. Simple GUI

   A simple interface allowed for our chat bot to look cleaner and be more usable.

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/feature1.png?raw=true)

2. 5 Reasonable Responses for Inputs Outside of Topic

   If no response can be made organically, there are 5 canned responses that will be used.

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/feature2.png?raw=true)

3. Spelling Mistakes (Porter Stemmer)

   The Porter Stemmer algorithm allowed us to turn words in the user input into their roots to resolve issues with users using words with slightly different endings or minor spelling mistakes. In this example, only "program" and "discount" are hard coded key words that can be recognized, but our implementation of the Porter Stemmer algorithm removes the suffixes as well as the extra "m" so that "programing", "programming" and "discounted" can be recognized and trigger the proper response.

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/feature3.png?raw=true)

4. Name Entity Recognition (OpenNLP)

   Our implementation of name recognition allows the bot to recognize a name within the input (only one name at a time though) and use that name in a response based on the rest of the input.

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/feature4.png?raw=true)

5. Sentimental Analysis

   Our implementation of the Stanford Tool Kit allowed us to use sentimental analysis, where if an input to the bot begins with "you" it is recognized as a sentimental statement, and is then judged on a scale of being very friendly/nice to very rude/mean based on key words and phrases and returns one of five responses based on where it lies on this scale.

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/feature5.png?raw=true)

# 30 Rounds of Dialogue

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/30%20Rounds%20(1).png?raw=true)

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/30%20Rounds%20(2).png?raw=true)

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/30%20Rounds%20(3).png?raw=true)

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/30%20Rounds%20(4).png?raw=true)

# Limitations and Examples

1. Only one name can be recognized at a time

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Limitations%20(1).png?raw=true)

2. Synonyms are not able to be recognized and can lead to the wrong response

   ![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Limitations%20(2).png?raw=true)

# Features that could be shared with others

1. The Graphical User Interface (The Window, Output Terminal and Contents Classes)
2. The ability to read keywords and phrases from a .txt file (Rules Class)
3. The canned responses for inputs outside of topic (Found in ChatBot Class in the notUnderstood() method)
4. Address Feedback (Using Sentiment Classes analyze() method, found within ChatBot Class in the addressFeedback() method)
5. Create New Sentence out of Stemmed Input (Using the Stemmer class, found within ChatBot Class in the stemInput() Method)

# Overall Class Structure (Assignment 2): #

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/UML%20(1).png?raw=true)

# Automated Unit Testing

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/JUnit%20TestCases.png?raw=true)

Unit tests were performed using JUnit 4. Class [TestChatBot.class](../assignment2/src/assignment2/TestChatBot.java) contains all the unit tests.
These cases are testing some major classes and their methods that constitute our ChatBot:
- testAnalyzeSentiment: to check if bot correctly identifies different emotional input
- testNotUnderstood: to check if premade "not understood" phrases are returned correctly
- testFindPerson: to check if bot successfully recognizes names
- testStemInput: to check if words are correctly stemmed

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Travis%20CI%20Automated%20Testing.png?raw=true)

We successfully configured our project for continuous integration.
- Every commit that was made in testing branch automatically triggers the build/testing
- Travis CI uses [.travis.yml](../.travis.yml) file to recognize the configuration
- Some of the jobs were not successful since implementing JUnit in Maven is a bit more complex than pure JUnit, but Travis CI definitely verifies and builds our project
