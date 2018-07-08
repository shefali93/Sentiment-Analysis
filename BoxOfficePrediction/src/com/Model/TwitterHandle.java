package com.Model;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandle {
	
	private static final String AUTH_CONSUMER_KEY = "6Mxclen2F4BR48FbW6DxDz8WW";
	private static final String AUTH_CONSUMER_SECRET ="I6u0bIIgS5rnTvXoHRl7634pqq5cwZvDqngMqIkZjkgFs68TcW";
	private static final String AUTH_ACCESS_TOKEN ="3494884994-CLBotjHDercrbbHGcWQs0TkcAoOIRl2i4W9lmpl";
	private static final String AUTH_ACCESS_TOKEN_SECRET ="b6z0HEAP2UVjY9W8iUU4A8O57DFFaxQ0K8l9RCBoIv6CK";
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
