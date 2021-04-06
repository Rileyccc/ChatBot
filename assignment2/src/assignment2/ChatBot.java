package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//new package imported that allows for Regular Expressions
import java.util.regex.*;

public class ChatBot {

	//hash map "rules" containing tuples of (keywords, response)
	//notice that for multiple keywords stored in ArrayList, bot has one response
	private Rule rules;
	private SentimentAnalyzer sentiment;
	private Stemmer stemmer;
	private PersonFinder personFinder;
	private Definitions definition;
	private Wolfram wolfram;
	private IMDb imdb;
	private Weather weather;
	public ChatBot() {
		//initializing rules with one tuple
		rules = new Rule();
		sentiment = new SentimentAnalyzer();
		stemmer = new Stemmer();
		personFinder = new PersonFinder();
		definition = new Definitions();
		wolfram = new Wolfram();
		imdb = new IMDb();
		weather = new Weather();
		
	}

	/*
	 * takes string and returns same string with stemmed words
	 */

	public String stemInput(String input) {
		//initialize final result
		String output = "";
		//Create an array of words from the input string by splitting them by spaces
		String[] inputArray = input.split("\\s+");
		//loop through the words in the array
		for (String word:inputArray) {
			//add word to the stemmer by character
			stemmer.add(word.toCharArray(), word.length());
			stemmer.stem();
			//add newly stemmed word to the output with a space
			output += stemmer.toString() + " ";
		}
		return output;
	}

	 /*
     * takes String outputs "intelligent" answer
     */
    public String getResponse(String input){
    	// checks to see if the user is asking about the weather
    	if(input.contains("what's the current weather in ")) {
    		String test = weather.getWeatherResponse(input.replace("what's the current weather in ", ""));
    		if(test != null) {
    			return test;
    		}
    	}
    	// checks to see if the user is asking about a movie
    	if(input.contains("have you seen")) {
    		String test = imdb.getIMDbResponse(input.replace("have you seen", ""));
    		if(test != null) {
    			return test;
    		}
    	}
    	// save a unstemmed version of input for wolfram class if needed
    	String unStemmedInput = input;
    	
    	// check if the input contains who is, or what is... if it does get response from definiton
    	String output  = "";
    	if(input.contains("who is ")) {
    		// remove "who is" and send it to get wiki definiton
    		output = definition.getDefinition(input.replace("who is", ""));	
    		System.out.println(output);
    		
    	}else if(input.contains("what is")) {
    		// remove "what is" and send it to get wiki definiton
    		output = definition.getDefinition(input.replace("what is", ""));
    	}
    	// if output is not null and has been changed then return output
    	if(output != null && !output.equals("") ) {
    		return output;
    	}
    	String[] words = input.split("\\s+");
    	// if first sentence in sentence is addressing bot
    	if(words[0].equals("you")) {
    		return addressFeedback(input);
    	}
    	// check to see if a person was mentioned in input 
    	boolean personRefernce = personFinder.findPerson(input);
    	// if person not metioned stem the input
    	if(!personRefernce) {
    		input = stemInput(input);
    	}
    	// if a person name was metioned replace the input with the new string which changes any name to person
    	input = (personRefernce)? personFinder.getSentence() :input;
        //loop through all possible responses
        for(ArrayList<String> keywords : rules.keySet()) {
        	//build a keyword pattern for each response (regex standard)
        	String pattern_str = String.join("\\b|\\b", keywords);
        	pattern_str = String.format("\\b%s\\b", pattern_str);
        	Pattern pattern = Pattern.compile(pattern_str, Pattern.CASE_INSENSITIVE);

        	//match with input
        	Matcher matcher = pattern.matcher(input);

        	while(matcher.find()) {
        		//if match found, return respective response from rules
        		// if person Refernce is true then replace word person from output with the proper name
        		if(personRefernce) {
        			return personFinder.replaceNameWithPerson(rules.get(keywords), true);
        		}
        		return rules.get(keywords);
        	}
        }
        // if no good answer found check Wolfram for an answer
        String wolfOutput = wolfram.getWolframSimpleResponse(unStemmedInput);
        if(wolfOutput != null) {
        	// if wolfram has answer output it
        	return wolfOutput;
        }
        return notUnderstood();
    }

    public String notUnderstood() {
    	int random = (int) (Math.random() * 5);
    	String[] responses ={
    			"Sorry, I didn't quite get that",
    			"Sorry, I'm a little confused. Try again?",
    			"I did not understand your query",
    			"My apologies, I am not sure what you are trying to ask",
    			"I don't recognize what you are trying to ask"
    			};
		return responses[random];
    }
    // takes a string addressing the bot specificly and outputs will display apropriate response
    public String addressFeedback(String input) {

    	int rating  = sentiment.analyze(input);
    	switch(rating){
    	case 0:
    		return "Sorry to upset you <3, how about we go for dinner and fix this up?";
    	case 1:
    		return "jeez, I thought we were friends! I still love you.";
    	case 2:
    		return "noted, anymore questions ma'am?";
    	case 3:
    		return "thank you dear, anymore questions?";
    	case 4:
    		return "That is the nicest thing anyone has ever said to me <3";
    	}
    	return "this should never be called";


    }

}
