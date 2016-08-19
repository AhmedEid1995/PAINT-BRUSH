package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class SaveAndLoad {
	public void SaveXml (String path, Shape []shapes){
		 String fileName= path ;		    
	    try {
			FileWriter fw = new FileWriter(fileName,true);
		
		    fw.write("<paint>");
   
		    for(int i=0;i<shapes.length;i++){
		    	Object temp=shapes[i];		    	
		    	String className = temp.getClass().getName();
		    	fw.write("<"+"shape"+" id = "+"\""+className+"\">");
		    	if(((Shape) temp).getPosition()!=null){
		    		fw.write("<x>"+((Shape) temp).getPosition().x+"</x>");
		    		 fw.write("<y>"+((Shape) temp).getPosition().y+"</y>");
		    	}else{
		    		fw.write("<x>"+"-1"+"</x>");
		    		 fw.write("<y>"+"-1"+"</y>");
		    	}
		    	 fw.write("<map>");
	    		 Map <String, Double> properities = new HashMap<String, Double>();
	    		 properities=((Shape)temp).getProperties();
	    		 if(properities!=null){
	    			 for (Entry<String, Double> e : properities.entrySet()) {
	    				 fw.write("<"+e.getKey()+">");
	    				 if(e.getValue() != null){
	    					 fw.write(e.getValue().toString());
	    				 }else{
	    					 fw.write("-1");
	    				 }
	    		    	 
	    		    	 fw.write("</"+e.getKey()+">");		   
	    			 }
	    		 }else{
	    			 fw.write("<key>-1</key>");
	    		 }
	    		 fw.write("</map>");
	    		 if(((Shape) temp).getColor()!=null)
	    			 fw.write("<color>"+((Shape) temp).getColor().getRGB()+"</color>");
	    		 else {
	    			 fw.write("<color>"+-1+"</color>");
	    		 }
	    		 if(((Shape) temp).getFillColor()!=null)
	    			 fw.write("<fillcolor>"+((Shape) temp).getFillColor().getRGB()+"</fillcolor>");
	    		 else {
	    			 fw.write("<fillcolor>"+-1+"</fillcolor>");
	    		 }
	    		 fw.write("</"+"shape"+">");
 
		    }
			fw.write("</paint>");
		    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public LinkedList<Shape> loadXml (String path){
		try {	
			LinkedList<Shape> shapesList=new LinkedList<Shape>();

	         File inputFile = new File(path);
	         DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("shape");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	        	 Node nNode = nList.item(temp);   
	        	 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		 Element eElement =  (Element) nNode;
	        		 String className =eElement.getAttribute("id");
	        		 Class<?> c = Class.forName(className);
	        		 Shape tempShape = (Shape)c.newInstance();	        		
	        		 Point center = new Point() ;	        		 
	        		 center.x=Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());	        		
	        		 center.y=Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());	        		
	        		 if(center.x==-1&&center.y==-1){
	        			 
	        			 tempShape.setPosition(null);
	        		 }else{
	        			 tempShape.setPosition(center);
	        		 }
	        		Map <String, Double>tempProperities = new HashMap<String, Double>();
	        		
	        		NodeList getMap = eElement.getElementsByTagName("map").item(0).getChildNodes();
	        		for(int i=0;i<getMap.getLength();i++){
	        			Node node =getMap.item(i);
	        			if(node.getNodeType()==Node.ELEMENT_NODE){
	        				Element eElementMap =  (Element) node;
	        				String key =eElementMap.getTagName();
	        				
	        				Double value=Double.parseDouble(eElementMap.getTextContent());
	        				if(key!="key")
	        					tempProperities.put(key, value); 
	        				else
	        					tempShape.setProperties(null);
	        			}	
	        		}
	        		
	        		tempShape.setProperties(tempProperities);
	
	        		int color =Integer.parseInt(eElement.getElementsByTagName("color").item(0).getTextContent());
	        		if(color!=-1){
	        			
		        		 tempShape.setColor(new Color(color)); 
	        		}else{
	        			tempShape.setColor(null); 
	        		}
	        		 
	        		
	        		int fillColor =Integer.parseInt(eElement.getElementsByTagName("fillcolor").item(0).getTextContent());
	        		 if(fillColor!=-1){
	        		 		
		        		 tempShape.setFillColor(new Color(fillColor)); 
	        		 }else{
		        			tempShape.setFillColor(null); 
		        		}
	       		 
	        		 shapesList.push(tempShape);
	        		 /********************************************************************************/
	        	//	tempShape.draw(Gui.panel.getGraphics());
	        	//	Gui.engine.addShape(tempShape);
	        		
				//	Gui.itrator++;  
	        		
	        		 /********************************************************************************/
	           }
	         }
	         return shapesList;
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
		return null;
		
	         }
	
	
	public void SaveJson (String path,Shape []shapes) {
		
		String filename= path ;		    
	    FileWriter fw;
		try {
			fw = new FileWriter(filename,true);
			
		     fw.write("{\"ShapeArray\" : [");
		     for(int i=0;i<shapes.length;i++){
		    	 if(i>0)fw.write(",");
		    	 fw.write("{ \"className\" :  \" ");
			    	Shape temp=shapes[i];		    	
			    	String className = temp.getClass().getName();
			    	fw.write(className+"\",");
			    	if(temp.getPosition()!=null){
			    		fw.write("\"x\" : \""+temp.getPosition().x+"\",");
				    	fw.write("\"y\" : \""+temp.getPosition().y+"\",");
			    	}else{
			    		fw.write("\"x\" : \""+"-1"+"\",");
				    	fw.write("\"y\" : \""+"-1"+"\",");
			    	}
			    	
			    	Map <String, Double> properities = new HashMap<String, Double>();
			    	properities=temp.getProperties();
			    	
			    	
			    	if(properities!=null){
			    		for (Entry<String, Double> e : properities.entrySet()) {
			    			fw.write("\""+e.getKey()+"\" : ");
			    			if(e.getValue()==null){
			    				fw.write("\""+"-1"+"\",");
			    			}else{
			    				fw.write("\""+e.getValue().toString()+"\",");
			    			}
		    			 
		    			 
			    		}
			    	}else{
			    	
			    		fw.write("\""+"key"+"\" : ");
			    		fw.write("\""+"-1"+"\",");
			    		
			    	}
			    	
			    	 if( temp.getColor()!=null)
			    		 fw.write("\"color\" : \" "+ temp.getColor().getRGB()+"\","); 
		    		 else {
		    			 fw.write("\"color\" : \" "+ "-1"+"\","); 
		    		 }
		    		 if( temp.getFillColor()!=null)
		    			 fw.write("\"fillcolor\" : \" "+ temp.getFillColor().getRGB()+"\"");
		    		 else {
		    			 fw.write("\"fillcolor\" : \" "+"-1"+"\"");
		    		 }
			    	
			    	
			    	fw.write("}");
		     }
		     fw.write("]}");
			    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public LinkedList<Shape> loadJson (String path){
		LinkedList<Shape> shapesList=new LinkedList<Shape>();
		StringBuilder sb = null;String everything = null;
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		     sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        
		    }
		     everything = sb.toString();
		     String [] saves = everything.split("}");
		     for(int i=0;i<saves.length-2;i++){
		    	 	Pattern pattern = Pattern.compile(" \"className\" :  \" (.*?)\",");
				    Matcher matcher = pattern.matcher(saves[i]);
				    String temp = null;
				    while (matcher.find()) {
				         temp=matcher.group(1);
				    }
				    Class<?> c = null;
					try {
						
							c = Class.forName(temp);
							
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		 try {
						Shape tempShape = (Shape)c.newInstance();
						Point center =new Point ();
						pattern = Pattern.compile("\"x\" : \"(.*?)\",");
					     matcher = pattern.matcher(saves[i]);
					    while (matcher.find()) {
					        center.x=Integer.parseInt(matcher.group(1));
					    }
					     pattern = Pattern.compile("\"y\" : \"(.*?)\",");
					     matcher = pattern.matcher(saves[i]);
					    while (matcher.find()) {
					        center.y=Integer.parseInt(matcher.group(1));
					    }
					    if(center.x==-1&&center.y==-1){
		        			 
		        			 tempShape.setPosition(null);
		        		 }else{
		        			 tempShape.setPosition(center);
		        		 }
					    
					    Map <String, Double> properities = tempShape.getProperties();
					    boolean containerContainsContent=false;
					     containerContainsContent = saves[i].toLowerCase().contains("key".toLowerCase());
					    if(!containerContainsContent){
					    	for (Entry<String, Double> e : properities.entrySet()) {
					    		String lowBound="\""+e.getKey()+"\" : \"";
					    		String highBound="\",\"";
					    	
					    		pattern = Pattern.compile(lowBound+"(.*?)"+highBound);
					    		matcher = pattern.matcher(saves[i]);
					    		while (matcher.find()) {
						       
						        
					    			if( Double.parseDouble(matcher.group(1))!=-1)
					    				properities.put(e.getKey(), Double.parseDouble(matcher.group(1)));
					    			else
					    				properities.put(e.getKey(),null);
					    		}	  			 
					    	}
					    	 tempShape.setProperties(properities);
					    }else{
					    	 tempShape.setProperties(null);
					    }
					    
					/////////////////////////////////////////////////    
					    pattern = Pattern.compile("\"color\" : \" (.*?)\",\"");
					     matcher = pattern.matcher(saves[i]);
					     int color = 0,fillColor = 0;
					    while (matcher.find()) {
					    	 color =Integer.parseInt(matcher.group(1));
					    	
					    }
					    if(color!=-1){
		        			
			        		 tempShape.setColor(new Color(color)); 
		        		}else{
		        			tempShape.setColor(null); 
		        		}
					    pattern = Pattern.compile("\"fillcolor\" : \" (.*?)\"");
					     matcher = pattern.matcher(saves[i]);
					    while (matcher.find()) {
					    	 fillColor =Integer.parseInt(matcher.group(1));
	
					    }
					    if(fillColor!=-1){
					    	
			        		 tempShape.setFillColor(new Color(fillColor)); 
		        		}else{
		        			tempShape.setFillColor(null); 
		        		}
					  ///////////////////////////////////////////////////////////////////////  
					    /********************************************************************************/
		        	//	tempShape.draw(Gui.panel.getGraphics());
		        		//Gui.engine.addShape(tempShape);
						//Gui.itrator++; 
					    shapesList.add(tempShape);
					    /********************************************************************************/
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	 
		     }
		     
		     
		     return shapesList;
		} catch ( RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
}
