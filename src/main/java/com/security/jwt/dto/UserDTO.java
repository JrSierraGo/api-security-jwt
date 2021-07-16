package com.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String userName;
	private String pwd;
	private String token;

}
