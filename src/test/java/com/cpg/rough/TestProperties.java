package com.cpg.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println(System.getProperty("user.dir"));
Properties ppt=new Properties();
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
ppt.load(fis);
System.out.println(ppt.getProperty("browser"));



Properties ppt1=new Properties();
FileInputStream fis1=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
ppt1.load(fis1);

System.out.println(ppt1.getProperty("bmlbutton"));

	}

}
