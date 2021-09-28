package tweetCollection;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//NOTE!!!!!!!!!!!!!!!
		//I've created a faster ID implementation than the one the tweets came with, 
		//which means that the search is faster than a hash map, 
		//but this implies you know the fast ID.
		//You can still search with the original ID the tweet came with, but it will be slower access (it uses a hash map).
		//A file will be wrote with just the original ids
		//Another file will be wrote that has both the fast and original ids
		
		//P.S. can you inform me if you could tell a difference at how fast things worked with my implementation?
		//Please and Thanks!
		
		//Initialized collection with trainingProcessed file
		TweetCollection collection = new TweetCollection("I:/eclipse/workspaces/CSCI3381project1/tweetCollection/trainingProcessed.txt");
		
		//prints out first and last 100 tweets in collection
		System.out.print(collection.toString());
		
		//tests search for the fast Id
		System.out.println("\n\nSearch Fast Id");
		//for(int i = 0; i < 100; i++) 
			System.out.println(collection.search(2567).to_string());
		
		//tests search for the original ID
		System.out.println("\nSearch Original Id");
		System.out.println(collection.search(Long.parseLong("2185371091")).to_string());
		
		//tests search for user
		System.out.println("\nSearch User Name");
		int size = collection.userSize("NB8");
		Tweet[] userTweets = collection.search("NB8");
		for(int i = 0; i < size; i++)
			System.out.println(userTweets[i].to_string());
		
		//prediction = 0
		System.out.println("\nPredicton");
		System.out.println(collection.test(userTweets[size -1]));
		
		//collection prediction
		System.out.println("\nCollection Prediction");
		System.out.println(collection.collectionTest());
		
		//add tweet
		System.out.println("\nAdd Tweet");
		collection.add(new Tweet(Short.parseShort("2"),Long.parseLong("1230987234"),"Ian", "This project was not that fun", collection.getSize()));
		
		//modify tweet
		int tweetID = collection.getSize() - 1;
		//System.out.println("\nNew Tweet Before and After");
		System.out.println(collection.search(tweetID).to_string());
		collection.search(tweetID).setPolarity(Short.parseShort("0"));
		collection.search(tweetID).setUserName("Ian Lawrence");
		collection.search(tweetID).setText("I was Here");
		System.out.println("\nAfter Modifications");
		System.out.println(collection.search(tweetID).to_string());
		
		//remove tweet
		System.out.println("\nRemove A single Tweet");
		collection.remove(collection.search(tweetID));
		System.out.println(collection.search(tweetID));
		
		//modify a user's tweets
		System.out.println("\nModify a user's tweets");
		userTweets[1].setText("Loser!!!!");
		collection.remove(userTweets[2]);
		userTweets = collection.search("NB8");
		for(int i = 0; i < size; i++) {
			if(userTweets[i] != null)
				System.out.println(userTweets[i].to_string());
		}
		
		//remove user
		System.out.println("\nDelete A user");
		collection.remove("NB8");
		userTweets = collection.search("NB8");
		if(userTweets == null)
			System.out.println("User has no tweets or doesn't exist");
		
		//write file after
		collection.write("I:/eclipse/workspaces/CSCI3381project1/tweetCollection/trainingProcessedWrite.txt");
		
		//write file with fast ids
		collection.writeWithFastIds("I:/eclipse/workspaces/CSCI3381project1/tweetCollection/trainingProcessedWriteFastID.txt");

		//to string collection
		collection.toStringAll();
		System.out.println("\n\nYes, all 1.6 million tweets just printed to the screen!");
		System.out.println("You might want to comment out line 92 in Tester to actuially look at the rest of the code");
		
	}

}
