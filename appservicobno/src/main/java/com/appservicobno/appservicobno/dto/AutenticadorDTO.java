package com.appservicobno.appservicobno.dto;

public record AutenticadorDTO(String login, String senha) {
	// This record is used to represent the authentication data
	// It contains two fields: login and senha (password)
	// The record automatically generates the constructor, getters, equals,
	// hashCode, and toString methods
	// No additional methods or annotations are needed

}
