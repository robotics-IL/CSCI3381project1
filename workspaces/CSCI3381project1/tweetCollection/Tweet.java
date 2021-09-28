package tweetCollection;

public class Tweet {

	private short polarity;
	private long id;
	private String userName;
	private String tweet;
	private int fastId;
	
	public Tweet()
	{
		polarity = -1;
		id = -1;
		userName = "";
		tweet = "";
		fastId = -1;
	}
	
	public Tweet(short polari, long iD, String userNm, String twee, int fastID)
	{
		polarity = polari;
		id = iD;
		userName = userNm;
		tweet = twee;
		fastId = fastID;
	}
		
	public String getTweet() {
		return tweet;
	}

	public void setText(String text) {
		this.tweet = text;
		}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public short getPolarity() {
		return polarity;
	}

	public void setPolarity(short polarity) {
		this.polarity = polarity;
	}

	public long getId() {
		return id;
	}

	public int getFastId() {
		return fastId;
	}

	public String to_string()
	{
		return polarity + "," + id + "," + userName + "," + tweet;
	}
	
	public String to_string_fastID()
	{
		return polarity + "," + fastId + "," + id + "," + userName + "," + tweet;
	}
}
