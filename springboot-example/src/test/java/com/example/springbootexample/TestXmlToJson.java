package com.example.springbootexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.springbootexample.controllers.XmlToJson;

public class TestXmlToJson {
	
	XmlToJson xmlToJson = new XmlToJson();

	@Test
	@DisplayName("XML to json should work")   
    void testXmlfileinputjsonfileoutput() throws IOException {
        assertEquals("The program ran and converted XML file to JSON File", xmlToJson.xmlfileinputjsonfileoutput(),     
                "Test Successful for XMLJSON");  
    }
}
