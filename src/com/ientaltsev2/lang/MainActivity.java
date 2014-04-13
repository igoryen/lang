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
import java.util.ArrayList;

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
		
		getStats();
    	

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
	
	private String getStats(){
		String x = "";
		InputStream is1 = null;
		InputStream is2 = null;
	    
    	Log.d("a2.main.getStats", "entered");
    	
    	try {
	        is1 = getAssets().open("00750017_structure.xml"); //2
	        is2 = getAssets().open("00750017.xml"); //2

	        Document doc1 = null; // 15
	        Document doc2 = null; // 15
	        
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 16
	        DocumentBuilder db; // 17
	        
	        try {
	            db = dbf.newDocumentBuilder(); // 3
	            doc1 = db.parse(is1); // 4
	            doc2 = db.parse(is2); // 4
	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        doc1.getDocumentElement().normalize(); // 18
	        doc2.getDocumentElement().normalize(); // 18	  
	        
	    	Log.d("a2.main.getStats", "---------------------------");
	        getStatsHeader(doc1, doc2);
	    	Log.d("a2.main.getStats", "---------------------------");
	        getStatsUnitOfMeasure(doc1, doc2);
	    	Log.d("a2.main.getStats", "---------------------------");
	        getStatsGeography(doc1, doc2);
	    	Log.d("a2.main.getStats", "---------------------------");	    	
	        getStatsLangs(doc1, doc2);
	    	Log.d("a2.main.getStats", "---------------------------");
	    	
	        
	    } catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    }		
		
		return x;
	}
	
	private String getStatsHeader(Document doc1, Document doc2) { // 1
		String strHeader = "";
	    NodeList tagNameNodeSet = doc1.getElementsByTagName("Name"); // 5	        
        Log.d("a2.main.getHeader.nameElements.getLength",  ""+tagNameNodeSet.getLength());
        Node itemNodeEn = tagNameNodeSet.item(0); // 19
        strHeader = itemNodeEn.getTextContent() + ". \n"; // 20
        Log.d("a2.main.getHeader.strHeader", strHeader);
	    return strHeader; // 13
	} // getHeader
	
	
	private String getStatsUnitOfMeasure(Document doc, Document doc2) { // 1
	    String UnitOfMeasure = "";	    
    	Log.d("a2.main.getUnitOfMeasure", "entered");	    
        NodeList a = doc.getElementsByTagName("CodeLists"); // 5        
    	Log.d("a2.main.getUnitOfMeasure.NodeListOfCodeList.getLength",  ""+a.getLength());        
    	Node b = a.item(0); // 19    	
    	Element c = (Element) b; // 8    	
    	NodeList d = (c).getElementsByTagName("structure:CodeList");
        Node e = d.item(0); // <====
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
	    return UnitOfMeasure; // 13
	} // getStats

	
	private String getStatsGeography(Document doc, Document doc2) { // 1	   
	    String geography = "";	    
    	Log.d("a2.main.getGeography", "entered");	    
        NodeList a = doc.getElementsByTagName("CodeLists"); // 5        
    	Log.d("a2.main.getGeography.NodeListOfCodeList.getLength",  ""+a.getLength());        
    	Node b = a.item(0); // 19    	
    	Element c = (Element) b; // 8    	
    	NodeList d = (c).getElementsByTagName("structure:CodeList");
        Node e = d.item(1); // <====
        Element f = (Element) e;
        NodeList g = (f).getElementsByTagName("structure:Name");        
        Node h = g.item(0);
        Element i = (Element) h;
        String j = i.getTextContent() + ". \n"; // 20
        Log.d("a2.main.getGeography.Geography.header", j);        
        NodeList n = (f).getElementsByTagName("structure:Description");
        Node k = n.item(0);
        Element l = (Element) k;
        String m = l.getTextContent() + ". \n";
        Log.d("a2.main.getGeography.Geography.value", m);
        geography = m;
        Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure", geography);
	    return geography; // 13
	} // getStats
	// 900
	
	class YearValue{
		String year;
		String numbers;
	}

	class LangStat{
	    String langName;
	    ArrayList<YearValue> YearValueSet = new ArrayList<YearValue>();
	}
	
	private ArrayList<LangStat> getStatsLangs(Document doc, Document doc2) { // 1
		LangStat  ls = new LangStat();
		ArrayList<LangStat> lss = new ArrayList<LangStat>();

		
	    //ArrayList<String> langArrayStatList = new ArrayList<String>();
    	Log.d("a2.main.getStatsLangs", "entered");
    	
        NodeList a = doc.getElementsByTagName("CodeLists"); // 5        
    	Log.d("a2.main.getStatsLangs.NodeListOfCodeList.getLength",  ""+a.getLength());
    	
    	Node b = a.item(0); // 19    	
    	Element c = (Element) b; // 8    	
    	NodeList d = (c).getElementsByTagName("structure:CodeList");
    	
        Node e = d.item(2); // <==== Third, for languages
        Element f = (Element) e;
        NodeList g = (f).getElementsByTagName("structure:Name");
        
        Node h = g.item(0);
        Element i = (Element) h;
        String j = i.getTextContent() + ". \n"; // 20
        Log.d("a2.main.getStatsLangs.Langs.header", j);
        
        NodeList n = (f).getElementsByTagName("structure:Code"); // 47 languages
        Log.d("a2.main.getStatsLangs.codes: ", ""+n.getLength());
        
        NodeList n2 = doc2.getElementsByTagName("cansim:Series"); // 47 stats for the 47 languages
    	Log.d("a2.main.getStatsLangs.cansim:Series.getLength",  ""+n2.getLength());
        
        for (int x = 0; x < n.getLength(); x++){ 
        	Node k = n.item(x); // for language x
            Element l = (Element) k;
            
        	Node k2 = n2.item(x); // stats x for language x
            Element l2 = (Element) k2;
            
            NodeList o = (l).getElementsByTagName("structure:Description");
            Log.d("a2.main.getStatsLangs.descriptions: ", ""+o.getLength()); // 2 names of lang x

            NodeList o2 = (l2).getElementsByTagName("cansim:Obs");
            Log.d("a2.main.getStatsLangs.cansim:Obs.getLength(): ", ""+o2.getLength()); // 1-5 stats for language x
            
            for (int ii = 0; ii < o.getLength(); ii ++){ // 2 times - Eng and French name of language ii
            	Node p = o.item(ii);
            	Element q = (Element) p;
            	
                //Log.d("a2.main.getStatsLangs.descriptions.xml:lang: ", ""+q.getAttribute("xml:lang"));

            	ls = new LangStat();
            	
            	if(q.getAttribute("xml:lang").contains("en")){ // take english name of lang x           		

                    //langArrayStatList.add(lang); // array [0] = language name
            		ls.langName = q.getTextContent();
            		Log.d("a2.langStatSet.langName", ls.langName);
                    
            		//Log.d("a2", "--------"+o2.getLength());
                    for(int iii = 0; iii < o2.getLength(); iii ++){
            			Node p2 = o2.item(iii);
            			Element q2 = (Element) p2;
            			
            			YearValue yv = new YearValue();
            			yv.year = (q2.getAttribute("TIME_PERIOD"));
            			yv.numbers = (q2.getAttribute("OBS_VALUE"));
            			//Log.d("a2.langStatSet.year_numbers", yv.year+" - " +yv.numbers);
            			ls.YearValueSet.add(yv);
            			yv = null;
            		}
        			lss.add(ls);
                    //Log.d("a2.main.getStatsLangs.Lang.value", x+". " +lang);
            	} // if
            }// for
        } // for
        
        Log.d("a2.main.getStatsLangs.lss.size", ""+lss.size());
        //Log.d("a2", lss.get(0).langName);
        
        for(int y = 0; y < lss.size(); y++){
        	Log.d("a2/a2.main.getStatsLangs.lss.ls(y)size", y+")"+lss.get(y).langName);
        	for(int yy = 0; yy < lss.get(y).YearValueSet.size(); yy++){
            	Log.d("a2/a2.main.getStatsLangs.lss.get(y).YearValueSet", y+")"+lss.get(y).YearValueSet.get(yy).year + " - " + lss.get(y).YearValueSet.get(yy).numbers);
        	}        	
        }
        

	    return lss; // 13
	} // getStats
	// 900
	
	
    private void DisplayToast(String msg){ // 14
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    	//Log.d("a2", "M: Toast fired");
    }
	
} // MainActivity
