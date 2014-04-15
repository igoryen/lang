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
	    //Log.d("a2.act2.onCreate", "entered"); // !
		setContentView(R.layout.secondactivity);
		

    	//Log.d("a2.act2.onCreate", "set content");//!
    	
    	
    	
    	//TextView langName = (TextView) findViewById(R.id.); // 42
    	TextView langName = (TextView) findViewById(R.id.textView1);
    	Intent i = getIntent();
    	//Log.d("a2.act2.onCreate", "got intent");
    	String languagePickedByUser = i.getStringExtra("language");
    	//Log.d("a2.act2.onCreate", "got language picked by user");
    	langName.setText(String.valueOf(languagePickedByUser));
    	//Log.d("a2.act2.onCreate", "set language picked by user");
		//======================================================================
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	//======================================
    	String x = "";
    	InputStream is1 = null;
    	InputStream is2 = null;
    	   
        //Log.d("a2.act2.getStats", "entered");
        
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
	        
	        //==================================================================
	        // get the header
    		String strHeader = "";
    	    NodeList tagNameNodeSet = doc1.getElementsByTagName("Name"); // 5	        
            //Log.d("a2.act2.getHeader.nameElements.getLength",  ""+tagNameNodeSet.getLength());
            Node itemNodeEn = tagNameNodeSet.item(0); // 19
            strHeader = itemNodeEn.getTextContent() + ". \n"; // 20
            //Log.d("a2.act2.getHeader.strHeader", strHeader);
            
            // TODO set strHeader to View
            //TextView header = (TextView) findViewById(R.id.header);
            //header.setText(strHeader);
            
    	    //return strHeader; // 13
    	    
	    	//Log.d("a2.act2.getStats", "---------------------------");
	        //getStatsHeader(doc1, doc2);
	        
	        //==================================================================
	    	// get the unit of measure
            
            String UnitOfMeasure = "";      
            //Log.d("a2.main.getUnitOfMeasure", "entered");    
            
            NodeList nodeListOfCodeLists = doc1.getElementsByTagName("CodeLists"); // 5        
            //Log.d("a2.main.getUnitOfMeasure.nodeListOfCodeLists.getLength",  ""+nodeListOfCodeLists.getLength());              
            Node nodeCodeLists = nodeListOfCodeLists.item(0); // 19
            Element elementCodeLists = (Element) nodeCodeLists; // 8
            
            NodeList nodeListOfCodeList = (elementCodeLists).getElementsByTagName("structure:CodeList");       
            //===============================================

                         
            Node nodeUOM = nodeListOfCodeList.item(0); // <====
            Element elementUOM = (Element) nodeUOM;
            
            NodeList nodeListOfUOMname = (elementUOM).getElementsByTagName("structure:Name");
            Node nodeUOMname = nodeListOfUOMname.item(0);
            Element elementUOMname = (Element) nodeUOMname;
            String stringUOMname = elementUOMname.getTextContent() + ". \n"; // 20
            //Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure.header", stringUOMname);
            
            NodeList nodeListOfUOMdesc = (elementUOM).getElementsByTagName("structure:Description");// lang name
            Node nodeUOMdesc = nodeListOfUOMdesc.item(0);
            Element elementUOMdesc = (Element) nodeUOMdesc;
            String strUOMdesc = elementUOMdesc.getTextContent() + ". \n";
            //Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure.value", strUOMdesc);
            
            // TODO set the unit of measure
            //TextView uom = (TextView) findViewById(R.id.UOM);
            //uom.setText(strUOMdesc);
            
            //Log.d("a2.main.getUnitOfMeasure.UnitOfMeasure", UnitOfMeasure);
            
                //return UnitOfMeasure; // 13

            //getStatsUnitOfMeasure(doc1, doc2);
            //Log.d("a2.main.getStats", "---------------------------");
	    	//=================================================================
            // get Geography
            

            Node nodeGEO = nodeListOfCodeList.item(1); // <====
            Element elementGEO = (Element) nodeGEO;
            NodeList nodeListOfGEOname = (elementGEO).getElementsByTagName("structure:Name");        
            Node nodeGEOname = nodeListOfGEOname.item(0);
            Element elementGEOname = (Element) nodeGEOname;
            String stringGEOname = elementGEOname.getTextContent() + ". \n"; // 20
            //Log.d("a2.main.getGeography.Geography.header", stringGEOname);        
            NodeList nodeListOfGEOdesc = (elementGEO).getElementsByTagName("structure:Description");
            Node nodeGEOdesc = nodeListOfGEOdesc.item(0);
            Element elementGEOdesc = (Element) nodeGEOdesc;
            String stringGEOdesc = elementGEOdesc.getTextContent() + ". \n";
            //Log.d("a2.main.getGeography.Geography.value", stringGEOdesc);
            // TODO set text view for geography
            //TextView geo = (TextView) findViewById(R.id.GEO);
            //geo.setText(stringGEOdesc);
 
    	    //=================================================================
            // get language and statistics

            Node nodeMOT = nodeListOfCodeList.item(2); // <==== Third, for languages - <structure:CodeList>
            Element elementMOT = (Element) nodeMOT;
            NodeList nodeListOfMOTname = (elementMOT).getElementsByTagName("structure:Name"); // "Mother tongue"
            
            Node nodeMOTname = nodeListOfMOTname.item(0); // the english word "Mother Tongue"
            Element elementMOTname = (Element) nodeMOTname;
            String stringMOTname = elementMOTname.getTextContent() + ". \n"; // 20
            //Log.d("a2.main.getStatsLangs.Langs.header", stringMOTname); // => "Mother tongue"
            
            NodeList nodeListOfMOTcode = (elementMOT).getElementsByTagName("structure:Code"); // 47 langueges i.e. <structure:Code value="1">
            //Log.d("a2.main.getStatsLangs.codes: ", ""+nodeListOfMOTcode.getLength());
            
            
            
            //loop through the 47 <structure:Code value="x"> - the amount of languages
            
            for (int xx = 0; xx < nodeListOfMOTcode.getLength(); xx++){ 
            
            	// 1 - pick lang by its ordinal number
                Node nodeMOTcode = nodeListOfMOTcode.item(xx); // for <structure:Code value="x"> out of 47
                
                
                //=======================================================
                Element elementMOTcode = (Element) nodeMOTcode;
                
                // pick language by <structure:Description...
                
                // get the language's parent <structure:Code value="1">
                // Each node has method getParentNode(), you could get node's parent node.
                // pick stats by the "value="
                
                
                // 1 - pick stat section by its ordinal number
                
                
                // 2 - lang description i.e. "Total languages" or "French" or "English"

                
                //==========================================================
                // <structure:Description xml:lang="en">English</structure:Description>
                //<structure:Description xml:lang="fr">Anglais</structure:Description>
                NodeList nodeListOfMOTdesc = (elementMOTcode).getElementsByTagName("structure:Description");
                //Log.d("a2.main.getStatsLangs.descriptions: ", ""+nodeListOfMOTdesc.getLength()); // 2 names of lang x
                
                
                
                
                for (int ii = 0; ii < nodeListOfMOTdesc.getLength(); ii++){ // for each of 2 - Eng and French name of language ii
                	
                	// <structure:Description xml:lang="en">English</structure:Description>
                    Node nodeMOTdesc = nodeListOfMOTdesc.item(ii); // take the 1st node 
                    Element elementMOTdesc = (Element) nodeMOTdesc; // turn it into an Element
                    
                    //Log.d("a2.main.getStatsLangs.descriptions.xml:lang: ", ""+nodeMOTdesc.getAttribute("xml:lang"));

                    
                    if(elementMOTdesc.getAttribute("xml:lang").contains("en")){ // take english name of lang x
                		//Log.d("a2.act2", elementMOTdesc.getAttribute("xml:lang"));


                        //langArrayStatList.add(lang); // array [0] = language name
                    	//String nodeMOTcodeTextContent = nodeMOTdesc.getTextContent(); // get language name
                    	
                		//Log.d("a2.act2", "user's "+languagePickedByUser + " vs " + nodeMOTdesc.getTextContent());
                		

                		//String languagePickedByUser_ = languagePickedByUser.toString();
                		//=========================================================
                		String al = nodeMOTdesc.getTextContent();
                		String ul = languagePickedByUser;
                		//==========================================================
                		/*
                		  if(al.compareTo(ul) <0){
                			    Log.d("a2.act2", "al is strictly < to ul");
                			  }
                			  else if (al.compareTo(ul) == 0){
                			    //a equals to another_string
                  			    Log.d("a2.act2", "MATCH!!! al == ul");

                			  }
                			else{
                			    Log.d("a2.act2", "al is strictly > than ul");

                			}    
                			*/
                    	//===========================================================
                		
                		//if (nodeMOTdesc.getTextContent() == languagePickedByUser){ // if it's the language that the user picked
                		//if (nodeMOTdesc_.equals(languagePickedByUser)){
                		if(al.compareTo(ul) == 0){
                			
                    		Log.d("a2.act2", "match: " + al + " = " + ul);
                    		
                    		// TODO
                    		//TextView mot = (TextView) findViewById(R.id.MOT); // mot = mother tongue
                            //mot.setText(ul); // 
            			    Log.d("a2.act2", "ul: " + ul);

                            
                            //Log.d("a2", "--------"+nodeListOfObs.getLength());
                    		
                    		// get the 'value' attribute in the parent node
                    		
                    		Node nodeParent = nodeMOTdesc.getParentNode(); // take its parent node
                    		Element elementParent = (Element) nodeParent; // turn it into an Element
                    		String userChoiceLanguageNumber = elementParent.getAttribute("value"); // get the value of the element's attribute
                    		
                    		// SEARCH FOR THE STAT INFO NODE WITH THE SAME ATTR VALUE
                    		
                    		// get the list of the 47 Nodes with the STAT info                        		
                    		NodeList nodeListOfSeries = doc2.getElementsByTagName("cansim:Series"); // 47 stats <cansim:Series UOM="1" GEO="1" MOT="1">
                            Log.d("a2.act2.cansim:Series.getLength",  ""+nodeListOfSeries.getLength());
                            
                            // !!!find the needed node by its attribute value
                            
                            // search through each of the 47 nodes with stat info
                            for (int z = 0; z < nodeListOfSeries.getLength(); z++){
                            	
                            	Node nodeSeries = nodeListOfSeries.item(z); // stats x for language x                                	
                            	Element elementSeries = (Element) nodeSeries;
                            	
                            	String aln = elementSeries.getAttribute("MOT");
                            	String uln = userChoiceLanguageNumber;
                            	
                            	// if the language found 
                            	if(aln.compareTo(uln)==0){
                            		Log.d("a2.act2", "match: " + aln + " = " + uln);
                            		
                            		//2 - stat
                        			//<cansim:Obs TIME_PERIOD="1931" OBS_VALUE="5914402"/>
                        			//<cansim:Obs TIME_PERIOD="1941" OBS_VALUE="6448190"/>
                        			//<cansim:Obs TIME_PERIOD="1951" OBS_VALUE="8280809"/>
                        			//<cansim:Obs TIME_PERIOD="1961" OBS_VALUE="10660534"/>
                        			//<cansim:Obs TIME_PERIOD="1971" OBS_VALUE="12973810"/>
                                    NodeList nodeListOfObs = (elementSeries).getElementsByTagName("cansim:Obs");
                                    
                                    Log.d("a2.act2.cansim:Obs.getLength(): ", 
                                    		""+nodeListOfObs.getLength()); // 1-5 stats for language x
                                    
                                    ArrayList<String> langStats = new ArrayList<String>();
                                    
                            		// gather all the Stat Info lines the language has
                                    for(int y = 0; y < nodeListOfObs.getLength(); y ++){
                                    	
                                    	Node nodeObsCurr = nodeListOfObs.item(y);
                                    	Element elementObsCurr = (Element) nodeObsCurr;
                                        String nsnCurr = elementObsCurr.getAttribute("OBS_VALUE"); // nsn = number of native speakers
                                        //int prev = 0;
                                        int curr = 0;
                                        
                                        try {
                                        	curr = Integer.parseInt(nsnCurr);
                                        } catch(NumberFormatException nfe) {
                                           System.out.println("Could not parse " + nfe);
                                        } 
                                        // ===========================================
                                        // if there is a previous year
                                        /*
                                        Log.d("a2.act2", "before prev");                                        
                                    	Node nodeObsPrev = null;
                                    	String nsnPrev = "";
                                    	
                                    	
                                        
                                        int diff = 0;
                                        Log.d("a2.act2", "before if prev not null");
                                    	if(((nodeListOfObs.item(y-1)) == null)){
                                    		Log.d("a2.act2", "prev not null");
                                    		nodeObsPrev = nodeListOfObs.item(y-1); 
                                    		Element elementObsPrev = (Element) nodeObsPrev;
                                    		nsnPrev = elementObsPrev.getAttribute("OBS_VALUE");                                      		
                                            // convert to integers
                                            
                                            try {
                                            	prev = Integer.parseInt(nsnPrev);
                                            } catch(NumberFormatException nfe) {
                                               System.out.println("Could not parse " + nfe);
                                            }                                        
                                            
                                            diff = curr - prev;
                                            Log.d("a2.act2", "diff = " + diff);
                                    	} // if there is a previous year                 
                                    	*/
                                        //===============================================

                                        //======================================================
                                        // format the number of native speakers so that it has commas
                                        nsnCurr = NumberFormat.getIntegerInstance().format(curr);
                                        //===============================================
                                        
                                        String line = "";
                                        line = elementObsCurr.getAttribute("TIME_PERIOD") + ": " + nsnCurr;
                                        langStats.add(line);
                                        
                                        //Log.d("a2.langStatSet.year_numbers", yv.year+" - " +yv.numbers);
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
                    		//Log.d("a2.act2", "no match");
                    	}// if language not found
                        
                        //Log.d("a2.main.getStatsLangs.Lang.value", x+". " +lang);
                    } // if
                }// for
            } // for
            
            //Log.d("a2.main.getStatsLangs.lss.size", ""+lss.size());
            //Log.d("a2", lss.get(0).langName);
            /*
            for(int y = 0; y < lss.size(); y++){
                Log.d("a2/a2.main.getStatsLangs.lss.ls(y)size", y+")"+lss.get(y).langName);
                for(int yy = 0; yy < lss.get(y).YearValueSet.size(); yy++){
                    Log.d("a2/a2.main.getStatsLangs.lss.get(y).YearValueSet", y+")"+lss.get(y).YearValueSet.get(yy).year + " - " + lss.get(y).YearValueSet.get(yy).numbers);
                }           
            }*/
            

        //    return lss; // 13
         // getStats
        // 900
	    //=================================================================
	    	
	        
	    } catch (IOException e1) {
	        Log.d("a2", e1.getLocalizedMessage());
	    }		
    		

    	
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	//========================================================================
        
	}// onCreate()

	
	
	

	
	
    private void DisplayToast(String msg){ // 14
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    	//Log.d("a2", "M: Toast fired");
    }
	
} // MainActivity
