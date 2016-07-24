package ph.globe.hackathon.controller;

import java.io.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


@Controller
public class AppController {
    @Autowired
    MessageSource messageSource;
 
    /*
     * This method will redirect the page to the home page.
     */
    @RequestMapping(value = { "/" , "home"}, method = RequestMethod.GET)
    public String goToHomePage() {
        return "ecc";
    }
    
    /*
     * This method will redirect the page to the Approval admin page.
     */
    @RequestMapping(value = {"/approval"}, method = RequestMethod.GET)
    public String goToApprovalPage() {
        return "approval";
    }
    
    /*
     * This method will redirect the page to the Emergency admin page.
     */
    @RequestMapping(value = {"/emergency"}, method = RequestMethod.GET)
    public String goToEmergencyPage() {
        return "emergency";
    }
    
    /*
     * This method will get coordinates of Metro Manila map and return to client.
     */
	@RequestMapping(value = { "/getManilaJson" }, method = RequestMethod.GET)
    @ResponseBody
    public String getMNLJson(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String x = sc.getRealPath("/");
        String philJson = x + "resources/json/mm.geojson";
        
    	JSONParser parser = new JSONParser();
    	String json = "";
        try {
 
            Object obj = parser.parse(new FileReader(philJson));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            Gson gson = new Gson();
            json = gson.toJson(jsonObject);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	/*
     * This method will get all messages and return to client.
     */
	@RequestMapping(value = { "/getMessages" }, method = RequestMethod.GET)
    @ResponseBody
    public String getEmergencyMessages(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String x = sc.getRealPath("/");
        String messagesJson = x + "resources/json/messages.json";
        
    	JSONParser parser = new JSONParser();
    	String json = "";
        try {
 
            Object obj = parser.parse(new FileReader(messagesJson));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            Gson gson = new Gson();
            json = gson.toJson(jsonObject);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
