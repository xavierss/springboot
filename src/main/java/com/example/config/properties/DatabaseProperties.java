package com.example.config.properties;

public interface DatabaseProperties {
	
	public String getDriverClassName();
	
	public String getUrl();
	
	public String getUserName();
	
	public String getPassword();
	
	public int getInitialSize();
	
	public int getMaxActive();
	
	public int getMaxIdle();
	
	public int getMinIdle();
	
	public int getMaxWait();
}