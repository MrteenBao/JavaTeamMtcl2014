/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.database;

/**
 *
 * @author Vuong Gia Phu
 */
public class Data {
	private String _Link;
	private String _Title;
	private String _Image;
	private String _Author;
	private String _Date;
	private String _Hashtag;
	public Data(String _Link,String _Title,String _Image, String _Author, String _Date, String _Hashtag)
	{
		this._Link=_Link;
		this._Title=_Title;
		this._Image=_Image;
		this._Author=_Author;
		this._Date=_Date;
		this._Hashtag=_Hashtag;
	}
	public String Set_Link(){
		return _Link;
	}
	public String Set_Title(){
		return _Title;
	}
	public String Set_Image(){
		return _Image;
	}
	public String Set_Author(){
		return _Author;
	}
	public String Set_Date(){
		return _Date;
	}
	public String Set_Hashtag(){
		return _Hashtag;
	}
	
}
