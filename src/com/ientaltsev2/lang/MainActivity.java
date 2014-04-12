package com.ientaltsev2.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//final DBAdapter dba = new DBAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	Log.d("a2.main.onCreate", "entered");

		setContentView(R.layout.activity_main);
    	getHeader();
    	getUnitOfMeasure();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// 200
	// 300
	// 400
	// 500
	// 600
	// 700
	
	// 800
	private String getHeader() { // 1
	    InputStream is = null;
	    String strHeader = "";
	    
    	Log.d("a2.main.getHeader", "entered");

	    try {
	        is = getAssets().open("00750017_structure.xml"); //2
	        Document doc = null; // 15
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 16
	        DocumentBuilder db; // 17
	        try {
	            db = dbf.newDocumentBuilder(); // 3
	            doc = db.parse(is); // 4
	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        doc.getDocumentElement().normalize(); // 18
	        NodeList tagNameNodeSet = doc.getElementsByTagName("Name"); // 5
	        
        	Log.d("a2.main.getHeader.nameElements.getLength",  ""+tagNameNodeSet.getLength());

            Node itemNodeEn = tagNameNodeSet.item(0); // 19
            strHeader = itemNodeEn.getTextContent() + ". \n"; // 20
            Log.d("a2.main.getHeader.strHeader", strHeader);

	    } catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    }

	    return strHeader; // 13
	} // getHeader
	
	
	private String getUnitOfMeasure() { // 1
	    InputStream is = null;
	    String UnitOfMeasure = "";
	    
    	Log.d("a2.main.getUnitOfMeasure", "entered");

	    try {
	        is = getAssets().open("00750017_structure.xml"); //2
	        Document doc = null; // 15
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 16
	        DocumentBuilder db; // 17
	        try {
	            db = dbf.newDocumentBuilder(); // 3
	            doc = db.parse(is); // 4
	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        doc.getDocumentElement().normalize(); // 18
	        NodeList a = doc.getElementsByTagName("CodeLists"); // 5
	        
        	Log.d("a2.main.getUnitOfMeasure.NodeListOfCodeList.getLength",  ""+a.getLength());
            
        	Node b = a.item(0); // 19
        	
        	Element c = (Element) b; // 8
        	
        	NodeList d = (c).getElementsByTagName("structure:CodeList");
            Node e = d.item(0);
            
            Element f = (Element) e;
            NodeList g = (f).getElementsByTagName("structure:Name");
            
            Node h = g.item(0);
            Element i = (Element) h;
            String j = i.getTextContent() + ". \n"; // 20
            Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure.header", j);
            
            NodeList n = (f).getElementsByTagName("structure:Description");

            Node k = n.item(0);
            Element l = (Element) k;
            String m = l.getTextContent() + ". \n";
            Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure.value", m);

            UnitOfMeasure = m;
            Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure", UnitOfMeasure);


	    } catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    }

	    return UnitOfMeasure; // 13
	} // getStats

    private void DisplayToast(String msg){ // 14
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    	//Log.d("a2", "M: Toast fired");
    }
	
} // MainActivity
