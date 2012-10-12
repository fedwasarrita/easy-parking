package com.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import org.apache.commons.io.IOUtils;

import android.util.Log;

/**
 * Class permettant de gérer le client Http
 * 
 * @author Aurelie
 *
 */

public class MyHttpClient {
	
    private static MyHttpClient instance;

    private DefaultHttpClient client;

    private HttpContext myHttpContext;
	
    private MyHttpClient() {
    	
            client = new DefaultHttpClient();
            myHttpContext = new BasicHttpContext();
	
    }
	
    public static MyHttpClient getInstance() {
	
            if (instance == null) {
            	
                   	Log.v("SESSION", "Instance is null");

                    instance = new MyHttpClient();
            }
            return instance;	
    }

    public String post(String url, List<NameValuePair> values) throws IOException {
	
            String retour;
	
            HttpPost request = new HttpPost(url);	
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(values);

            request.setEntity(formEntity);

            HttpResponse response = client.execute(request, myHttpContext);
	
            InputStream is = response.getEntity().getContent();

            retour = IOUtils.toString(is);

            for (NameValuePair value : values) {
                    Log.v("SESSION", value.getName() + " : " + value.getValue());	
            }
	
            Log.v("SESSION", retour);
	
            IOUtils.closeQuietly(is);

            return retour;
    }

}
