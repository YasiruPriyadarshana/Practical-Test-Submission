package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HospitalService {
	Hospital hospitalObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return hospitalObj.readHospitals();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Address") String address,
	@FormParam("City") String city,
	@FormParam("Phone") String phone,
	@FormParam("Name") String name,
	@FormParam("Rooms") String rooms)
	{
	String output = hospitalObj.insertHospital(address, city, phone, name, rooms);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String id = itemObject.get("HospitalID").getAsString();
	String address = itemObject.get("Address").getAsString();
	String city = itemObject.get("City").getAsString();
	String phone = itemObject.get("Phone").getAsString();
	String name = itemObject.get("Name").getAsString();
	String room = itemObject.get("Rooms").getAsString();
	String output = hospitalObj.updateHospital(id, address, city, phone, name, room);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String hData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(hData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String id = doc.select("HospitalID").text();
	String output = hospitalObj.deleteHospital(id);
	return output;
	}
}