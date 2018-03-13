package app.dmc;

import static app.Main.dmcDao;
import spark.*;

/*
 * This is the controller class for DMC data. 
 * It invokes the right method on the DAO object to return the data in the requested format
 */
public class DmcController {
	
	//Returns all Beaumont data
	public static Route getAllDmcRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return dmcDao.searchDmcRecord(request);
		}
		return dmcDao.getAllDmcRecords();
	};
	//Returns one specified record
	public static Route getOneDmcRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return dmcDao.getOneDmcRecord(request);
	};
	//Searches records for the given search keyword
	public static Route searchDmcRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return dmcDao.searchDmcRecord(request);
	};

}
