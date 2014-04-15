package com.ientaltsev2.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
    String ln; // ln = one language name
    String[] lna; // lna = language names' array
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	//Log.d("a2.main.onCreate", "entered"); // !
		setContentView(R.layout.activity_main);
		
        Spinner s = (Spinner) findViewById(R.id.spinner); // 21

		InputStream is1 = null; // 30
        ArrayList<String> lnl = new ArrayList<String>(); // lnl = language names' list
        //lnl.add("Make a selection"); // 31 
        
    	//Log.d("a2.main.getStats", "entered"); // !
    	
    	try {
	        is1 = getAssets().open("00750017_structure.xml"); //2

	        Document doc1 = null; // 15	        
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 16
	        DocumentBuilder db; // 17
	        
	        try {
	            db = dbf.newDocumentBuilder(); // 3
	            doc1 = db.parse(is1); // 4
	        } // try
	        catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } // catch 
	        catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } // catch
	        
	        doc1.getDocumentElement().normalize(); // 18
	        
	        //===============================================
	        NodeList a = doc1.getElementsByTagName("CodeLists"); // 5        
	    	//Log.d("a2.main.getStatsLangs.NodeListOfCodeList.getLength",  ""+a.getLength()); // !
	    	
	    	Node b = a.item(0); // 19    	
	    	Element c = (Element) b; // 8    	
	    	NodeList d = (c).getElementsByTagName("structure:CodeList");
	    	
	        Node e = d.item(2); // <==== Third, for languages
	        Element f = (Element) e;
	        NodeList g = (f).getElementsByTagName("structure:Name");
	        
	        Node h = g.item(0);
	        Element i = (Element) h;
	        String j = i.getTextContent() + ". \n"; // 20
	        //Log.d("a2.main.getStatsLangs.Langs.header", j); // !
	        
	        NodeList n = (f).getElementsByTagName("structure:Code"); // 47 languages
	        //Log.d("a2.main.getStatsLangs.codes: ", ""+n.getLength()); // !
	        

	        //===============================================
	        
	        for (int x = 0; x < n.getLength(); x++){ 
	        	Node k = n.item(x); // for language x
	            Element l = (Element) k;
	            
	            NodeList o = (l).getElementsByTagName("structure:Description");
	            //Log.d("a2.main.getStatsLangs.descriptions: ", ""+o.getLength()); // 2 names of lang x
	            
	            for (int ii = 0; ii < o.getLength(); ii++){ // 2 times - Eng and French name of language ii
	            	Node p = o.item(ii);
	            	Element q = (Element) p;
	            	
	                //Log.d("a2.main.getStatsLangs.descriptions.xml:lang: ", ""+q.getAttribute("xml:lang"));
	            	
	            	if(q.getAttribute("xml:lang").contains("en")){ // take english name of lang x           		
	            		String ln = q.getTextContent();
	            		//ln = ln.trim();
	                    //langArrayStatList.add(lang); // array [0] = language name
	            		lnl.add(ln);
	            		//Log.d("a2.langStatSet.langName", lnl.get(ii));
	            	} // if
	            	
	            }// for
	        } // for
	        //===============================================

    	} catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    } // catch
        //===================================================================
    	// look at how many languages were extracted
        for(int yy = 0; yy < lnl.size(); yy++){
        	// Log.d("a2.langStatSet.langName", yy+") " +lnl.get(yy));// !
        }    	
    	//===================================================================
        
        Collections.sort(lnl, String.CASE_INSENSITIVE_ORDER); // sort alphabetically
        // set "Total languages" at the top
        lnl.remove("Total languages");
        lnl.add(0,"Total languages");
        
        
        lna = new String[lnl.size()]; // 57
        lnl.toArray(lna); // 60

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_single_choice, lna); // 32
        s.setAdapter(aa); // 33

        OnItemSelectedListener oisl = new OnItemSelectedListener(){ //22
        	@Override
        	public void onItemSelected(AdapterView<?> arg0,	View arg1, int arg2, long arg3){ // 23
        		int i = arg0.getSelectedItemPosition(); // 24
        		//if (i == 0){ // 25
                //    ln = "";
                //    Log.d("a2", "M: language(s): >" + ln + "<");
        		//}
        		//else{ // 26
                    ln = lna[i]; // 27
                    // Log.d("a2.main.oisl", "M: language(s): " + ln); // !
        		//}	
        		//Toast.makeText(getBaseContext(), "Station: " + st, Toast.LENGTH_LONG).show();        		
        	}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { // 28
                //st = "All"; // 33 - doesn't go to second activity
            }
        }; // oisl - OnItemSelectedListener


        s.setOnItemSelectedListener(oisl); // 29
        
		// Log.d("a2.main.onCreate", "exiting"); //!
		
		//=============================================================

        Button btn = (Button) findViewById(R.id.showme); // 34

        final OnClickListener ocl = new OnClickListener(){ // 35
        	public void onClick(View v){ // 36

            	final Intent i2 = new Intent("com.ientaltsev2.lang.ActivityTwo"); // 37

                    Bundle extras = new Bundle(); // 38

                    // Log.d("a2.main.ocl", "checking if language was specified..."); // !
                    // Log.d("a2.main.ocl", "lang >" + ln + "<"); // !
                    extras.putString("language", ln); // 39

                    i2.putExtras(extras); // 40
                    startActivity(i2); // 41

                    //finish();

                
        	} // onClick()
        }; // ocl
        //===================================================================
        // attach the on-click listener to the submit button
        //===================================================================
        btn.setOnClickListener(ocl); 


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	


	
} // MainActivity
