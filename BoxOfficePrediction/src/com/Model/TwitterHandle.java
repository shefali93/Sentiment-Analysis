package com.Model;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandle {
	
	private static final String AUTH_CONSUMER_KEY = <The consumer key>;
	private static final String AUTH_CONSUMER_SECRET =<The consumer secret>;
	private static final String AUTH_ACCESS_TOKEN =<The access token>;
	private static final String AUTH_ACCESS_TOKEN_SECRET =<The access token secret>;
	static Configuration conf;
	static
    {
        ConfigurationBuilder cb;
        cb=new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(AUTH_CONSUMER_KEY)
            .setOAuthConsumerSecret(AUTH_CONSUMER_SECRET)
            .setOAuthAccessToken(AUTH_ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(AUTH_ACCESS_TOKEN_SECRET)
                ;
        conf=cb.build();
    }//static
	 public static TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();
}
