package app.henryFord;

import static app.Main.hfDao;
import spark.*;
/*
 * This is the controller class for HF data. 
 * It invokes the right method on the DAO object to return the data in the requested format
 */
public class HfController {
	
	//Returns all Beaumont data
	public static Route getAllHfRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return hfDao.searchHfRecord(request);
		}
		return hfDao.getAllHfRecords();
	};
	//Returns one specified record
	public static Route getOneHfRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return hfDao.getOneHfRecord(request);
	};
	//Searches records for the given search keyword
	public static Route searchHfRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return hfDao.searchHfRecord(request);
	};

}
