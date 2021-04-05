package assignment2;

import org.fastily.jwiki.core.*;
import org.fastily.jwiki.dwrap.*;


// use Jwiki api to find definitons of topics or people 
public class Definitions {
	private String domain;
	private Wiki wiki;
	public Definitions() {
		wiki = new Wiki.Builder().build();
	}
	
	// input will be sent here if it begins with "who is person" or if it begins with "what is noun"; 
	public String getDefinition(String input) {
		//we only want to return the first couple sentences where the article defines 
		String sentences = "";
		// remove question marks if any exist
		input = input.replace("?", "");
		try {
			// extract the leading paragraph from wikipedia
			sentences = wiki.getTextExtract(input);
		}catch(NullPointerException ex) {
			return null;
		}
		return getSentences(sentences);
	}
	// takes a paragraph returns sub-paragraph with two periods or entire paragraph if there is not 2 periods
	public String getSentences(String paragraph) {
		String sentences = "";

		int i = 0;
		int periodCount = 0;
		while(periodCount < 2 && i < paragraph.length()) {
			if(paragraph.charAt(i) == ('.')) {
				periodCount++;
			}
			sentences += paragraph.charAt(i);
			i++;
		}
		return sentences;	
	}
}
