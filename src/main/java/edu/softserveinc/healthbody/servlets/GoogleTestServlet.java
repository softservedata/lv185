package edu.softserveinc.healthbody.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

/**
 * Servlet implementation class GoogleTestServlet
 */
@WebServlet("/gt")
public class GoogleTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoogleTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String rn = System.lineSeparator();
		out.print("<html lang=\"en\">" + rn);
		out.print("<head>" + rn);
		out.print("<meta name=\"google-signin-scope\" content=\"profile email\">" + rn);
		out.print(
				"<meta name=\"google-signin-client_id\" content=\"48524677967-juniqolaio06efre3m3q7774097q50u8.apps.googleusercontent.com\">"
						+ rn);
		out.print("<script src=\"https://apis.google.com/js/platform.js\" async defer></script>" + rn);
		out.print("</head>" + rn);
		out.print("<body>" + rn);
		out.print("<div class=\"g-signin2\" data-onsuccess=\"onSignIn\" data-theme=\"dark\"></div>" + rn);
		out.print("<script>" + rn);
		out.print("function onSignIn(googleUser) {" + rn);
		out.print("// Useful data for your client-side scripts:" + rn);
		out.print("var profile = googleUser.getBasicProfile();" + rn);
		out.print("console.log(\"ID: \" + profile.getId()); // Don't send this directly to your server!" + rn);
		out.print("console.log('Full Name: ' + profile.getName());" + rn);
		out.print("console.log('Given Name: ' + profile.getGivenName());" + rn);
		out.print("console.log('Family Name: ' + profile.getFamilyName());" + rn);
		out.print("console.log(\"Image URL: \" + profile.getImageUrl());" + rn);
		out.print("console.log(\"Email: \" + profile.getEmail());" + rn);
		out.print("// The ID token you need to pass to your backend:" + rn);
		out.print("var id_token = googleUser.getAuthResponse().id_token;" + rn);
		out.print("console.log(\"ID Token: \" + id_token);" + rn);
		out.print("console.log(\"JSON: \" + JSON.stringify(googleUser));" + rn);
		out.print("};" + rn);
		out.print("</script>" + rn);
		out.print("</body>" + rn);
		out.print("</html>" + rn);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (Exception e) {
		}

		System.out.println("DO POST was here");
		System.out.println(sb.toString());
		System.out.println();
		
		String CLIENT_SECRET_FILE = "C:\\SA_workspace\\lv185\\src\\main\\resources\\client_secret.json";
		
		String REDIRECT_URI = "http://localhost:8080/";

		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets =
		    GoogleClientSecrets.load(
		        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		System.out.println(this.getClass());
		
		GoogleTokenResponse tokenResponse =
		          new GoogleAuthorizationCodeTokenRequest(
		              new NetHttpTransport(),
		              JacksonFactory.getDefaultInstance(),
		              "https://www.googleapis.com/oauth2/v4/token",
		              clientSecrets.getDetails().getClientId(),
		              clientSecrets.getDetails().getClientSecret(),
		              sb.toString(),
		              REDIRECT_URI)  
		          
		          // Specify the same redirect URI that you use with your web
		                             // app. If you don't have a web version of your app, you can
		                             // specify an empty string.
		              .execute();
		
		System.out.println(2);

		String accessToken = tokenResponse.getAccessToken();

		// Use access token to call API
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		Drive drive =
		    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
		        .setApplicationName("Auth Code Exchange Demo")
		        .build();
		File file = drive.files().get("appfolder").execute();

		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");
		
	}

}
