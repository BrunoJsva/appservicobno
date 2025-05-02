package com.appservicobno.appservicobno.enums;

public enum RolesEnum {
    ADMIN,
    USER;
    
    private String role;
	
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
}
