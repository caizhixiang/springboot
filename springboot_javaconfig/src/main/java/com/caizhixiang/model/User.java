package com.caizhixiang.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable{
	private Integer id;
	private String name;
	private String password;
	private List<Function> functionList;
}
