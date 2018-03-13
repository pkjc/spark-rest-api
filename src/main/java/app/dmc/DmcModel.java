package app.dmc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DmcModel {
	@JsonProperty("Status of aneurysm")
	private String annStatus;
	@JsonProperty("Type of Aneurysm")
	private String annType;
	@JsonProperty("Aneurysm 1 location")
	private String annLoc;
	@JsonProperty("Size of Aneurysm 1")
	private Integer annSize;
	
	public DmcModel() {
		super();
	}
	
	public DmcModel(String annStatus, String annType, String annLoc, Integer annSize) {
		super();
		this.annStatus = annStatus;
		this.annType = annType;
		this.annLoc = annLoc;
		this.annSize = annSize;
	}
	public String getAnnStatus() {
		return annStatus;
	}
	public void setAnnStatus(String annStatus) {
		this.annStatus = annStatus;
	}
	public String getAnnType() {
		return annType;
	}
	public void setAnnType(String annType) {
		this.annType = annType;
	}
	public String getAnnLoc() {
		return annLoc;
	}
	public void setAnnLoc(String annLoc) {
		this.annLoc = annLoc;
	}
	public Integer getAnnSize() {
		return annSize;
	}
	public void setAnnSize(Integer annSize) {
		this.annSize = annSize;
	}
	
	@Override
	public String toString() {
		return "AnnData [annStatus=" + annStatus + ", annType=" + annType + ", annLoc=" + annLoc + ", annSize="
				+ annSize + "]";
	}
}
