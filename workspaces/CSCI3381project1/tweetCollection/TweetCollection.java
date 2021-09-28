package tweetCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class TweetCollection  {

	//collection stores all the tweets in an array
	private Tweet[] collection;
	private int collectionSize = 0;
	private int maxCollectionSize = 1700000;
	//users stores the users in an array
	//A user holds the ids of all of thats users tweets
	private User[] users;
	private int userSize = 0;
	private int maxUserSize = 800000;

	//sortedIds holds the tweet Ids in relation to their fast access id (which is an array itt) for the collection[]
	Map<Long,Integer> sortedIds; 
	//sortedUsers holds the usernames in relation to their fast access id for the users[]
	Map<String,Integer> sortedUsers; 

	
	public TweetCollection(String fileName){
		collection = new Tweet[maxCollectionSize];
		users = new User[maxUserSize];
		sortedIds = new HashMap<Long,Integer>();
		sortedUsers = new HashMap<String,Integer>();
		addFile(fileName);
	}
	
	private void addFile(String fileName) {
		BufferedReader lineReader = null;
		try { //read the file
			System.out.println("readingFIle");
			FileReader fr = new FileReader(fileName);
			System.out.println("readFile");
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				//act of reading file
				String[] tokens = line.split(",");
				short pol = Short.parseShort(tokens[0]);
				long id = Long.parseLong(tokens[1]);
				String userNm = tokens[2];
				String text = tokens[3];
				//adds tweet
				add(new Tweet(pol, id, userNm, text, collectionSize));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader.");
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	
	public int getSize() {
		return collectionSize;
	}
	
	public void add(Tweet tweet) {
		//doubles collection
		if(collectionSize == maxCollectionSize) {
			System.out.print("Doubling Collection Size");
			maxCollectionSize *= 2;
			Tweet []newTweets = new Tweet[maxCollectionSize];
			for(int i = 0; i < collectionSize; i++) {
				newTweets[i] = collection[i];
			}
			collection = newTweets;
		}
		//add the tweet to appropriate data within TweetCollection class.
		sortedIds.put(tweet.getId(),collectionSize);
		collection[collectionSize] = tweet;
		if(!sortedUsers.containsKey(tweet.getUserName())) {
			//doubles users
			if(userSize == maxUserSize) {
				System.out.print("Doubling User Size");
				maxUserSize *= 2;
				User []newUsers = new User[maxUserSize];
				for(int i = 0; i < userSize; i++) {
					newUsers[i] = users[i];
				}
				users = newUsers;
			}
			sortedUsers.put(tweet.getUserName(), userSize); 
			users[userSize++] = new User(collectionSize++);
		} else {
			users[sortedUsers.get(tweet.getUserName())].add(collectionSize++);
		}
	}
	
	public void remove(Tweet tweet) {
		sortedIds.remove(tweet.getId());
		collection[tweet.getFastId()] = null;
	}
	
	//removes the user and all of their tweets
	public void remove(String userName) {
		if(sortedUsers.containsKey(userName)) {
			int u = sortedUsers.get(userName);
			int size = users[u].getSize();
			int[] ids = users[u].getIDs();
			for (int i = 0; i < size; i++) {
				if(collection[ids[i]] != null) {				
					sortedIds.remove(collection[ids[i]].getId());
					collection[ids[i]] = null;
				}
			}
			users[u]  = null;
			sortedUsers.remove(userName);
		}
	}
	
	public int userSize(String userName) {
		if(sortedUsers.containsKey(userName))
			return users[sortedUsers.get(userName)].getSize();
		else
			return 0;
	}
	
	//returns an array of tweet fast ids that the user has
	public Tweet[] search(String userName) {
		if(sortedUsers.containsKey(userName)) {
			//return users[sortedUsers.get(userName)].getIDs();
			int id = sortedUsers.get(userName);
			int size = users[id].getSize();
			Tweet[] tweets = new Tweet[size];
			int[] tweetIds = users[id].getIDs();
			for(int i = 0; i < size; i++) {
				tweets[i] = collection[tweetIds[i]];
			}
			return tweets;
		}
		else
			return null;
	}
	
	public Tweet search(int fastID) {
		if(fastID < collectionSize) 
			return collection[fastID];
		else
			return null;
	}
	
	public Tweet search(long tweetID) {
		if(sortedIds.containsKey(tweetID)) 
			return collection[sortedIds.get(tweetID)];
		else
			return null;
	}
	
	public int test(Tweet tweet) {
		return 0;
	}
	
	public double collectionTest() {
		double numRight = 0.0;
		double numTested = collectionSize;
		for(int i = 0; i < collectionSize; i++) {
			if(collection[i] != null)
			{
				if(test(collection[i]) == collection[i].getPolarity())
					numRight++;
			}
			else
				numTested--;	
		}
		return numRight/numTested;
	}
	
	public void write(String fileName) {
		try
		{
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			System.out.println("Writing to " + fileName);
			for (int i = 0; i < collectionSize; i++) {
				if(collection[i] != null)
					myOutfile.write(collection[i].to_string() + "\n");
			}
			myOutfile.flush();
			myOutfile.close();
			System.out.println("File is complete");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fileName);
		}
	}	
	
	public void writeWithFastIds(String fileName) {
		try
		{
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter myOutfile = new BufferedWriter(fw);						
			System.out.println("Writing to " + fileName);
			for (int i = 0; i < collectionSize; i++) {
				if(collection[i] != null)
					myOutfile.write(collection[i].to_string_fastID() + "\n");
			}
			myOutfile.flush();
			myOutfile.close();
			System.out.println("File is complete");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fileName);
		}
	}	
	
	public String toString() {
		String output = "";
		if(collectionSize < 200)
			output = "Collection Size must be above 200 to print out to the screen";
		else {
			output += "First 100\n";
			for(int i = 0; i < 100; i++)
			{
				if(collection[i] != null)
					output += collection[i].to_string() + "\n";
			}
			output += "\nLast 100\n";
			for(int i = collectionSize - 100; i < collectionSize; i++)
			{
				if(collection[i] != null)
					output += collection[i].to_string() + "\n";
			}
		}
		return output;
	}
	
	public void toStringAll() {
		for(int i = 0; i < collectionSize; i++)
		{
			if(collection[i] != null)
				System.out.println(collection[i].to_string());
		}
	}
}