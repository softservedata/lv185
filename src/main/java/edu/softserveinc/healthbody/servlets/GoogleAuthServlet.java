package edu.softserveinc.healthbody.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.log.LoggerWrapper;
import edu.softserveinc.healthbody.webservice.HealthBodyServiceImpl;

@WebServlet("/GoogleAuth")
public class GoogleAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoogleAuthServlet() {
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

		try {
			// get code
			String code = request.getParameter("code");
			// format parameters to post
			String urlParameters = "code=" + code
					+ "&client_id=48524677967-juniqolaio06efre3m3q7774097q50u8.apps.googleusercontent.com"
					+ "&client_secret=KBpMscuWOZc43u-4KKpwbE5T" + "&redirect_uri=http://localhost:8080/lv185/GoogleAuth"
					+ "&grant_type=authorization_code";

			// post parameters
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			// get output in outputString
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}

			// get Access Token
			JsonObject json = new JsonParser().parse(outputString).getAsJsonObject();
			String access_token = json.get("access_token").getAsString();
			LoggerWrapper.info(this.getClass(), access_token + rn);

			// get User Info
			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
			urlConn = url.openConnection();
			outputString = "";
			reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "windows-1251"));
			while ((line = reader.readLine()) != null)
				outputString += line;
			LoggerWrapper.info(this.getClass(), outputString + rn);
			byte[] utf8JsonString = outputString.getBytes();
			String str = new String(utf8JsonString, StandardCharsets.UTF_8);
			// Convert JSON response into Pojo class
			GooglePojo data = new Gson().fromJson(str, GooglePojo.class);
			LoggerWrapper.info(this.getClass(), data.toString() + rn);
			writer.close();
			reader.close();

			// form UserDTO
			String email = data.getEmail();
			String login = email.substring(0, email.length() - 10).toString(); // minus"@gmail.com"
			String firstname = data.getGiven_name();
			String lastname = data.getFamily_name();
			String photoURL = data.getPicture();
			String fullgender = data.getGender();
			String gender = getGoogleGender(fullgender);
			List<GroupDTO> groups = new ArrayList<GroupDTO>();
			groups.add(new GroupDTO("Name group number 1", "", "", ""));
			UserDTO userDTO = new UserDTO(login, null, firstname, lastname, email, "0", "0.0", gender, photoURL, "user",
					null, "0", groups, "false");
			LoggerWrapper.info(this.getClass(), userDTO.toString());

			// work with base
			HealthBodyServiceImpl healthBodyServiceImpl = new HealthBodyServiceImpl();
			if (healthBodyServiceImpl.getUserByLogin(login) == null) {
				healthBodyServiceImpl.createUser(userDTO);
				UserDTO ud = healthBodyServiceImpl.getUserByLogin(login);
				out.append(login + ", wellcome! You've singed up HealthBody!" + rn);
				out.flush();
			} else {
				UserDTO ud = healthBodyServiceImpl.getUserByLogin(login);
				out.append(login + ", wellcome HealthBody!");
				out.flush();
			}

		} catch (IOException e) {
			LoggerWrapper.error(this.getClass(), "IOException catched" + e);
			return;
		}
	}

	public String getGoogleGender(String a) {
		String b = null;
		if (a.equalsIgnoreCase("male"))
			b = "m";
		else if (a.equalsIgnoreCase("female"))
			b = "w";
		else
			b = "o";
		return b;
	}

}