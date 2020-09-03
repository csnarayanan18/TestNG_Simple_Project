package com.erp.hrms.object.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credential_PropertiesUtils {

	Properties credprop = new Properties();

	// Constructor to load properties file
	public Credential_PropertiesUtils() {

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/erp/hrms/object/repositories/credentials.properties");
			credprop.load(fis);
			fis.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Method to get URL
	public String getURL() {
		String url = credprop.getProperty("URL");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("URL is incorrect or not valid");

	}

	// Method to get Username
	public String getUsername() {
		String url = credprop.getProperty("Username");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("Username is incorrect or not valid");
	}

	// Method to get Password
	public String getPassword() {
		String url = credprop.getProperty("Password");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("Password is incorrect or not valid");
	}

	// Method to get Supervisor Username
	public String getSupervisorUsername() {
		String url = credprop.getProperty("SupervisorUsername");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("Username is incorrect or not valid");
	}

	// Method to get Supervisor Password
	public String getSupervisorPassword() {
		String url = credprop.getProperty("SupervisorPassword");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("Password is incorrect or not valid");
	}

	// Method to get CC&B URL
	public String getCCBURL() {
		String url = credprop.getProperty("CCBURL");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("CCB URL is incorrect or not valid");

	}

	// Method to get CC&B Username
	public String getCCBUsername() {
		String url = credprop.getProperty("CCBUsername");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("CCB Username is incorrect or not valid");
	}

	// Method to get CC&B Password
	public String getCCBPassword() {
		String url = credprop.getProperty("CCBPassword");
		if (url != null) {
			return url;
		} else
			throw new RuntimeException("CCB Password is incorrect or not valid");
	}

	public String getAssignedRole() throws IOException {
		String assignedRole = credprop.getProperty("AssignedRole");
		if (assignedRole != null) {
			return assignedRole;
		} else
			throw new RuntimeException("assigned role is incorrect or not valid");
	}

	public String getAsssignedUser() throws IOException {
		String assignedUser = credprop.getProperty("AssignedUser");
		if (assignedUser != null) {
			return assignedUser;
		} else
			throw new RuntimeException("assigned user is incorrect or not valid");
	}
}