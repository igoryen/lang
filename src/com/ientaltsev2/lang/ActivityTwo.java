package com.ientaltsev2.lang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ActivityTwo extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondactivity);
    	
    	TextView langName = (TextView) findViewById(R.id.textView1);
    	Intent i = getIntent();
    	String languagePickedByUser = i.getStringExtra("language");
    	langName.setText(String.valueOf(languagePickedByUser));
    	InputStream is1 = null;
    	InputStream is2 = null;
        
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
	        doc2.getDocumentElement().normalize(); // 18

    		String strHeader = "";
    	    NodeList tagNameNodeSet = doc1.getElementsByTagName("Name"); // 5	        
            Node itemNodeEn = tagNameNodeSet.item(0); // 19
            strHeader = itemNodeEn.getTextContent() + ". \n"; // 20

            String UnitOfMeasure = ""; 
            NodeList nodeListOfCodeLists = doc1.getElementsByTagName("CodeLists"); // 5        
            Node nodeCodeLists = nodeListOfCodeLists.item(0); // 19
            Element elementCodeLists = (Element) nodeCodeLists; // 8            
            NodeList nodeListOfCodeList = (elementCodeLists).getElementsByTagName("structure:CodeList");       
                         
            Node nodeUOM = nodeListOfCodeList.item(0);
            Element elementUOM = (Element) nodeUOM;
            
            NodeList nodeListOfUOMname = (elementUOM).getElementsByTagName("structure:Name");
            Node nodeUOMname = nodeListOfUOMname.item(0);
            Element elementUOMname = (Element) nodeUOMname;
            String stringUOMname = elementUOMname.getTextContent() + ". \n"; // 20
            
            NodeList nodeListOfUOMdesc = (elementUOM).getElementsByTagName("structure:Description");// lang name
            Node nodeUOMdesc = nodeListOfUOMdesc.item(0);
            Element elementUOMdesc = (Element) nodeUOMdesc;
            String strUOMdesc = elementUOMdesc.getTextContent() + ". \n";
            
            Node nodeGEO = nodeListOfCodeList.item(1); // <====
            Element elementGEO = (Element) nodeGEO;
            NodeList nodeListOfGEOname = (elementGEO).getElementsByTagName("structure:Name");        
            Node nodeGEOname = nodeListOfGEOname.item(0);
            Element elementGEOname = (Element) nodeGEOname;
            String stringGEOname = elementGEOname.getTextContent() + ". \n"; // 20
            NodeList nodeListOfGEOdesc = (elementGEO).getElementsByTagName("structure:Description");
            Node nodeGEOdesc = nodeListOfGEOdesc.item(0);
            Element elementGEOdesc = (Element) nodeGEOdesc;
            String stringGEOdesc = elementGEOdesc.getTextContent() + ". \n";

            Node nodeMOT = nodeListOfCodeList.item(2); // <==== Third, for languages - <structure:CodeList>
            Element elementMOT = (Element) nodeMOT;
            NodeList nodeListOfMOTname = (elementMOT).getElementsByTagName("structure:Name"); // "Mother tongue"
            
            Node nodeMOTname = nodeListOfMOTname.item(0); // the english word "Mother Tongue"
            Element elementMOTname = (Element) nodeMOTname;
            String stringMOTname = elementMOTname.getTextContent() + ". \n"; // 20
            
            NodeList nodeListOfMOTcode = (elementMOT).getElementsByTagName("structure:Code"); // 47 langueges i.e. <structure:Code value="1">
                        
            
            //loop through the 47 <structure:Code value="x"> - the amount of languages
            
            for (int xx = 0; xx < nodeListOfMOTcode.getLength(); xx++){ 
            
            	// 1 - pick lang by its ordinal number
                Node nodeMOTcode = nodeListOfMOTcode.item(xx); // for <structure:Code value="x"> out of 47

                Element elementMOTcode = (Element) nodeMOTcode;
              
                NodeList nodeListOfMOTdesc = (elementMOTcode).getElementsByTagName("structure:Description");
 
                for (int ii = 0; ii < nodeListOfMOTdesc.getLength(); ii++){ // for each of 2 - Eng and French name of language ii
                	
                    Node nodeMOTdesc = nodeListOfMOTdesc.item(ii); // take the 1st node 
                    Element elementMOTdesc = (Element) nodeMOTdesc; // turn it into an Element                    
                    
                    if(elementMOTdesc.getAttribute("xml:lang").contains("en")){ // take english name of lang x
                		
                		String al = nodeMOTdesc.getTextContent();
                		String ul = languagePickedByUser;
                		
                		if(al.compareTo(ul) == 0){
                			                    		
                    		// TODO
                    		//TextView mot = (TextView) findViewById(R.id.MOT); // mot = mother tongue
                            //mot.setText(ul); // 
                    		
                    		// get the 'value' attribute in the parent node
                    		
                    		Node nodeParent = nodeMOTdesc.getParentNode(); // take its parent node
                    		Element elementParent = (Element) nodeParent; // turn it into an Element
                    		String userChoiceLanguageNumber = elementParent.getAttribute("value"); // get the value of the element's attribute
                    		
                    		// SEARCH FOR THE STAT INFO NODE WITH THE SAME ATTR VALUE                    		
                    		// get the list of the 47 Nodes with the STAT info                        		
                    		NodeList nodeListOfSeries = doc2.getElementsByTagName("cansim:Series"); // 47 stats <cansim:Series UOM="1" GEO="1" MOT="1">
                            
                            // !!!find the needed node by its attribute value
                            
                            // search through each of the 47 nodes with stat info
                            for (int z = 0; z < nodeListOfSeries.getLength(); z++){
                            	
                            	Node nodeSeries = nodeListOfSeries.item(z); // stats x for language x                                	
                            	Element elementSeries = (Element) nodeSeries;
                            	
                            	String aln = elementSeries.getAttribute("MOT");
                            	String uln = userChoiceLanguageNumber;
                            	
                            	// if the language found 
                            	if(aln.compareTo(uln)==0){

                                    NodeList nodeListOfObs = (elementSeries).getElementsByTagName("cansim:Obs");
                                    
                                    ArrayList<String> langStats = new ArrayList<String>();
                                    
                            		// gather all the Stat Info lines the language has
                                    for(int y = 0; y < nodeListOfObs.getLength(); y ++){
                                    	
                                    	Node nodeObsCurr = nodeListOfObs.item(y);
                                    	Element elementObsCurr = (Element) nodeObsCurr;
                                        String nsnCurr = elementObsCurr.getAttribute("OBS_VALUE"); // nsn = number of native speakers
                                        int curr = 0;
                                        
                                        try {
                                        	curr = Integer.parseInt(nsnCurr);
                                        } catch(NumberFormatException nfe) {
                                           System.out.println("Could not parse " + nfe);
                                        } 
                                       
                                        // format the number of native speakers so that it has commas
                                        nsnCurr = NumberFormat.getIntegerInstance().format(curr);
                                        
                                        String line = "";
                                        line = elementObsCurr.getAttribute("TIME_PERIOD") + ": " + nsnCurr;
                                        langStats.add(line);                                        
                                     }

                            		// display array list in the text view
                                    // TODO
                                    TextView stats = (TextView) findViewById(R.id.STAT); // mot = mother tongue
                                    stats.setText("");
                                    //stats.setBackgroundResource(R.drawable.postit);
                                    for (int j = 0; j < langStats.size(); j++){
                                        stats.append(langStats.get(j) + "\n");
                                    }
                            		
                            	} // if found stat info for the language picked by user
                            	else{
                            		//TODO there is no stats for this language                            		
                            	} 
                            } // for every <cansim:Series...> with stat info
                    	} // if the language picked by user is found
                    	else{
                    		// TODO
                    		//TextView mot = (TextView) findViewById(R.id.MOT); // mot = mother tongue
                    		//mot.setText("lang picked by user didn't match any");
                    	}// if language not found

                    } // if
                }// for
            } // for   
	    } catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    }
	}// onCreate()
	
    private void DisplayToast(String msg){ // 14
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    	//Log.d("a2", "M: Toast fired");
    }
	
} // MainActivity
