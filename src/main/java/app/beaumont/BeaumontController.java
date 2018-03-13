package app.beaumont;

import static app.Main.beaumontDao;
import spark.*;

/*
 * This is the controller class for BM data. 
 * It invokes the right method on the DAO object to return the data in the requested format
 */
public class BeaumontController {
	
	//Returns all Beaumont data
	public static Route getAllBeaumontRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return beaumontDao.searchBeaumontRecord(request);
		}
		return beaumontDao.getAllBeaumontRecords();
	};
	
	//Returns one specified record
	public static Route getOneBeaumontRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return beaumontDao.getOneBeaumontRecord(request);
	};
	
	//Searches records for the given search keyword
	public static Route searchBeaumontRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return beaumontDao.searchBeaumontRecord(request);
	};

}
