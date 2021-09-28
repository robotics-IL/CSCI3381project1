package tweetCollection;

public class User {
	//A User holds the fast Ids (an array itt) for all of that user's tweets
	private int tweetSortIDs[];
	private int size = 0;
	private int maxSize = 4;
	
	//creates the tweetSortIDs array for each user
	//initializes with the first id
	public User(int tweetId) {
		tweetSortIDs  = new int[maxSize];
		tweetSortIDs[size++] = tweetId;
	}
	
	//adds an id to the list
	//will double the array size if necessary
	//DOES NOT check to see if the id is already there
	//DOES NOT keep the array sorted
	//My implementation will already have a sorted array and no unnecessary duplicates
	//because the file has the data already sorted with desired duplicates included
	public void add(int id) {	
		if(size == maxSize) {
			maxSize *= 2;
			int []newTweets = new int[maxSize];
			for(int i = 0; i < size; i++) {
				newTweets[i] = tweetSortIDs[i];
			}
			tweetSortIDs = newTweets;
		}
		tweetSortIDs[size++] = id;
	}
	
	public int[] getIDs() {
			return tweetSortIDs;
	}
	
	public int getSize() {
		return size;
	}
}
