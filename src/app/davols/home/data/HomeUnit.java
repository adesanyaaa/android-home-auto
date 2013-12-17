package app.davols.home.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HomeUnit {

	@JsonProperty("Name")
	private String mName;
	
	@JsonProperty("deviceId")
	private char mDeviceId;
	
	@JsonProperty("deviceChannel")
	private int mChannel;
	
	@JsonProperty("mStatus")
	private int mStatus;

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public char getDeviceId() {
		return mDeviceId;
	}

	public void setDeviceId(char mDeviceId) {
		this.mDeviceId = mDeviceId;
	}

	public int getChannel() {
		return mChannel;
	}

	public void setChannel(int mChannel) {
		this.mChannel = mChannel;
	}

	public int getStatus() {
		return mStatus;
	}

	public void setStatus(int mStatus) {
		this.mStatus = mStatus;
	}

	public HomeUnit() {

	}
}
