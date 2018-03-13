package app.dmc;

import static app.Main.dmcDao;
import spark.*;

public class DmcController {

	public static Route getAllDmcRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return dmcDao.searchDmcRecord(request);
		}
		return dmcDao.getAllDmcRecords();
	};
	
	public static Route getOneDmcRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return dmcDao.getOneDmcRecord(request);
	};
	
	public static Route searchDmcRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return dmcDao.searchDmcRecord(request);
	};

}
