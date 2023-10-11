package myDBoxClientMediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientMediator {

	private String queryResult;

	public ClientMediator(){
 
System.out.println("-------------");	
System.out.println("-------------");	
System.out.println("-------------");	
System.out.println("I am Mediator...");	
System.out.println("-------------");	
	}

	public void sendRequest(String str) throws URISyntaxException, IOException {	
		
		               
		
		URI redirectURI= new URI("https://websitetest591203.azurewebsites.net/v1/callback"); //any url to where you want to redirect the user
		String appKey="g4vks3umi0djyec"; //app key that would be get after creating an app at Dropbox
		URI uri=new URI("https://www.dropbox.com/oauth2/authorize");
		StringBuilder requestUri=new StringBuilder(uri.toString());
		requestUri.append("?client_id=");
		requestUri.append(URLEncoder.encode(appKey,"UTF-8"));
		requestUri.append("&response_type=");
		requestUri.append(URLEncoder.encode("code","UTF-8"));
		requestUri.append("&redirect_uri="+redirectURI.toString());
		
		URL requestUrl=new URL(requestUri.toString());
		System.out.println("RequestURL : " + requestUrl.toString());

		
		      
			
		queryResult=requestUri.toString();
		
	}
	
	
	
	public void accessToken(String str) throws URISyntaxException, IOException {	
		
System.out.println("-----IN Access Token--------");			
		
		
		
		    String code = ""+str; //code get from previous step
	        String appKey = "g4vks3umi0djyec"; //appKey get using previous step
	        String appSecret = "fj34nvru8mnybt5"; //appSecret get using previous step
	        String redirectURI="http://localhost:8080/MyDBoxClient/"; //any url to where you want to redirect the user
	        StringBuilder tokenUri=new StringBuilder("code=");
	        tokenUri.append(URLEncoder.encode(code,"UTF-8"));
	        tokenUri.append("&grant_type=");
	        tokenUri.append(URLEncoder.encode("authorization_code","UTF-8"));
	        tokenUri.append("&client_id=");
	        tokenUri.append(URLEncoder.encode(appKey,"UTF-8"));
	        tokenUri.append("&client_secret=");
	        tokenUri.append(URLEncoder.encode(appSecret,"UTF-8"));
	        tokenUri.append("&redirect_uri="+redirectURI.toString());
	        URL url=new URL("https://api.dropbox.com/oauth2/token");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        try {
	        	
	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());

	            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
	            outputStreamWriter.write(tokenUri.toString());
	            outputStreamWriter.flush();
	            
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    		String inputLine;
	    		StringBuffer response = new StringBuffer();

	    		while ((inputLine = in.readLine()) != null) {
	    			response.append(inputLine);
	    		}
	    		in.close();

	    		//print result
	    		System.out.println(response.toString());
	    		queryResult=response.toString();        
	            
	 
	        } finally {
	            connection.disconnect();
	        }
		
		                
		            
		       
		   
	}
	
	public void getAccountInfo(String str, String str2) throws URISyntaxException, IOException {	
		
		System.out.println("-----IN Account Info--------");			
				
				
				
		            String access_token = ""+str; 
                    String account_id = ""+str2; 
			        String content = "{\"account_id\": \""+account_id+"\"}";
			        URL url=new URL("https://api.dropboxapi.com/2/users/get_account");
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        try {
			        	
			            connection.setDoOutput(true);
			            connection.setRequestMethod("POST");
			            connection.setRequestProperty("Authorization", "Bearer "+access_token);
			            connection.setRequestProperty("Content-Type", "application/json");
			            connection.setRequestProperty("Content-Length", "" + content.length());

         	            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			            outputStreamWriter.write(content);
			            outputStreamWriter.flush();
			            
			            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    		String inputLine;
			    		StringBuffer response = new StringBuffer();

			    		while ((inputLine = in.readLine()) != null) {
			    			response.append(inputLine);
			    		}
			    		in.close();

			    		//print result
			    		System.out.println(response.toString());
			    		queryResult=response.toString();        
			            
			 
			        } finally {
			            connection.disconnect();
			        }
				
				                
				            
				       
				   
	}
	
    public void uploadFile(String token, String path)  throws URISyntaxException, IOException {	
		
    	System.out.println("-----IN upload--------");			
		
		System.out.println("path is : "+path);						
		
					String access_token = ""+token; 
					String sourcePath = ""+path; //required file path on local file system
					Path pathFile = Paths.get(sourcePath);
					byte[] data = Files.readAllBytes(pathFile);
					
			        String content = "{\"path\": \"/MyDBoxClient_App01_files/images/image_initial_uploaded.png\",\"mode\": \"add\",\"autorename\": true,\"mute\": false,\"strict_conflict\": false}";
			        URL url=new URL("https://content.dropboxapi.com/2/files/upload");
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        try {
			        	
			            connection.setDoOutput(true);
			            connection.setRequestMethod("POST");
			            connection.setRequestProperty("Authorization", "Bearer "+access_token);
			            connection.setRequestProperty("Content-Type", "application/octet-stream");
			            connection.setRequestProperty("Dropbox-API-Arg", "" + content);
			            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
			            
			            OutputStream outputStream = connection.getOutputStream();
			            outputStream.write(data);
			            outputStream.flush();
			            
			            
			            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    		String inputLine;
			    		StringBuffer response = new StringBuffer();

			    		while ((inputLine = in.readLine()) != null) {
			    			response.append(inputLine);
			    		}
			    		in.close();

			    		//print result
			    		System.out.println(response.toString());
			    		queryResult=response.toString();        
			            
			 
			        } finally {
			            connection.disconnect();
			        }
				
				                				            			   
	}
    
    public void listFileRequests(String token) throws URISyntaxException, IOException {
        System.out.println("-----IN listFileRequests--------");

        String access_token = "" + token;

        String content = "{\"limit\": 1000}"; // Set the desired limit

        URL url = new URL("https://api.dropboxapi.com/2/file_requests/list_v2");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + access_token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(content.length()));

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(content);
            outputStreamWriter.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print result
            System.out.println(response.toString());
            queryResult = response.toString();

        } finally {
            connection.disconnect();
        }
    }

    
	public void getAccountInfo_(String str) throws URISyntaxException, IOException {	
		
		System.out.println("-----IN account info--------");			
				
				
				
				    String access_token = ""+str; 
				    StringBuilder accountInfoUri=new StringBuilder("https://api.dropbox.com/1/account/info");
				    accountInfoUri.append("?access_token=");
			        accountInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
			        URL url=new URL(accountInfoUri.toString());
			        
			        
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        try {
			        	
			            connection.setRequestMethod("GET");
			 //           InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
			 //           String responseDropBox=inputStreamReader.toString();
			 //           System.out.println(responseDropBox);
			//responseDropBox contains result JSON from Dropbox is something like:
			//  {"access_token": "<access token>", "token_type": "Bearer", "uid": "<user ID>"}
			            
			            
			            
			            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    		String inputLine;
			    		StringBuffer response = new StringBuffer();

			    		while ((inputLine = in.readLine()) != null) {
			    			response.append(inputLine);
			    		}
			    		in.close();

			    		//print result
			    		System.out.println(response.toString());
			    		queryResult=response.toString();        
			            
			  
			        } finally {
			            connection.disconnect();
			        }
				
				                
				            
				       
				   
			}
	
	public void uploadFile_(String token, String path) throws URISyntaxException, IOException {	
		
		System.out.println("-----IN upload--------");			
				
		System.out.println("path is : "+path);						
		
			String access_token = ""+token; 
			String sourcePath = ""+path; //required file path on local file system
			Path pathFile = Paths.get(sourcePath);
			byte[] data = Files.readAllBytes(pathFile);
//			URL url = new URL("https://api-content.dropbox.com/1/files_put/${root}/${destinationPath}?access_token=${access_token}");
			
			
			
	        StringBuilder accountInfoUri=new StringBuilder("https://api-content.dropbox.com/1/files_put/dropbox/MyFirstDApp_files/images/image_initial_uploaded.png");
	        accountInfoUri.append("?access_token=");
	        accountInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
	        URL url=new URL(accountInfoUri.toString());
		
		
		
				
				   
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        try {
			            connection.setDoOutput(true);
			            connection.setRequestMethod("PUT");
			            connection.setRequestProperty("Content-Type", "mime/type");
			            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
			            OutputStream outputStream = connection.getOutputStream();
			            outputStream.write(data);
			            outputStream.flush();
			            
			                     
			            
			            
			            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    		String inputLine;
			    		StringBuffer response = new StringBuffer();

			    		while ((inputLine = in.readLine()) != null) {
			    			response.append(inputLine);
			    		}
			    		in.close();

			    		//print result
			    		System.out.println(response.toString());
			    		queryResult=response.toString();        
			            
			 
			        } finally {
			            connection.disconnect();
			        }
				
				                
				             
				       
				   
			}
	
	public void listFilesRequests_(String token) throws URISyntaxException, IOException {
	    System.out.println("-----IN listFilesRequests_--------");

	    String access_token = "" + token;

	    StringBuilder accountInfoUri = new StringBuilder("https://api.dropboxapi.com/2/file_requests/list_v2");
	    accountInfoUri.append("?access_token=");
	    accountInfoUri.append(URLEncoder.encode(access_token, "UTF-8"));
	    URL url = new URL(accountInfoUri.toString());

	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    try {
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json");

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	        // Print result
	        System.out.println(response.toString());

	        // Extract and store destination values in a variable
	        String destinations = "";

	        // Extract destination values manually (assuming the response format)
	        String responseBody = response.toString();
	        int startIndex = responseBody.indexOf("\"destination\": \"");
	        while (startIndex != -1) {
	            int endIndex = responseBody.indexOf("\"", startIndex + 16);
	            if (endIndex != -1) {
	                String destination = responseBody.substring(startIndex + 16, endIndex);
	                destinations += destination + "\n";
	                startIndex = responseBody.indexOf("\"destination\": \"", endIndex);
	            } else {
	                break;
	            }
	        }

	        queryResult = destinations;
	        System.out.println(response.toString());
    		queryResult=response.toString();        

	    } finally {
	        connection.disconnect();
	    }
	}


			
	
	public String getResult(){
	//	queryResult = "Done!";
		return queryResult;
	}
	
	
	
	
	
}
