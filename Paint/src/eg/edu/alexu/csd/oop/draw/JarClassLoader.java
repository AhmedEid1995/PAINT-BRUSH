package eg.edu.alexu.csd.oop.draw;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Class<? extends Shape>> supportedShape(String jarPath) throws ClassNotFoundException, IOException{
		List lista = new ArrayList<Class<?>>();

		JarFile jarFile = new JarFile( jarPath);
		Enumeration e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" +  System.getProperty("java.class.path")+"!/") };

		URLClassLoader cl = URLClassLoader.newInstance(urls);
		

		    while (e.hasMoreElements()) {

		        JarEntry je = (JarEntry) e.nextElement();
		        if(je.isDirectory() || !je.getName().endsWith(".class")){
		            continue;
		        }
		        

		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    Class c = cl.loadClass(className);

		    if(Shape.class.isAssignableFrom(c)) { lista.add((Class<? extends Shape>) c);} //System.out.println(cls.getName()); }
		    else lista.add(c);

		    }

		return lista;
	}
	
	public List<Class<? extends Shape>> supportedShape() throws ClassNotFoundException, IOException{
		List lista = new ArrayList<Class<?>>();
		String jarPath = System.getProperty("java.class.path");
		String x=System.getProperty("path.separator");
		String[] parts = jarPath.split(x);
		for(int i=1;i<parts.length;i++){
		JarFile jarFile = new JarFile( parts[i]);
		Enumeration e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" +  System.getProperty("java.class.path")+"!/") };
		
		ClassLoader cl=getClass().getClassLoader();
		

		    while (e.hasMoreElements()) {
		        JarEntry je = (JarEntry) e.nextElement();
		        if(je.isDirectory() || !je.getName().endsWith(".class")){
		            continue;
		        }
		    
		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    Class c = cl.loadClass(className);
		    if(Shape.class.isAssignableFrom(c)) { lista.add((Class<? extends Shape>) c); //System.out.println(cls.getName()); }
		    lista.add(c);
		    }
		    
		   
		}
		    

	}
		return lista;
	}
	}