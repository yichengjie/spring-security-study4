package com.yicj.security.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class User {
	private String id;
	private String username;
	private String password;
	private Date birthday;
}