package fr.epsi.projet.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.beans.Emplacements;
import fr.epsi.projet.common.Constantes;

public class ServiceRest {
	
	/**
	 * @return la liste d'emplacement o� se trouve les bennes � verre.
	 */
	public List<Emplacement> getBennes() {
		
		//liste de toutes les bennes � verres au format xml
		String ret = callHttpRequest("{\"TYPE_DECHETS\":{\"$eq\":\"verre_ent\"}}");
		Emplacements e = parseXML(ret);	   
		
        return e.getData();
	}
	
	private String callHttpRequest(String data){
		String returnString = null;
		
		try {
			
			URL url = new URL(Constantes.URL_API_BENNES + data);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				if(returnString == null)
					returnString = output;
				else
					returnString += output;
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnString;
	}
	
	public Emplacements parseXML(String xml) {
		final int INDEX_QUARTIER = 3;
		final int INDEX_COORDONNEES = 5;
		final int INDEX_COMMUNE = 10;
		final int INDEX_ADRESSE = 15;
		
		Emplacements e = new Emplacements();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("ISO-8859-1"))));
			doc.getDocumentElement().normalize();
			
			NodeList list = doc.getElementsByTagName("nb_results"); 
			e.setNb_results(Integer.parseInt(list.item(0).getChildNodes().item(0).getNodeValue()));
			
			//liste d'emplacement
			list = doc.getElementsByTagName("element");
			System.out.println("nombre personne : " + list.getLength());
			
			e.setData(new ArrayList<Emplacement>());
			Emplacement emp = null;
			int max = e.getNb_results();
			int i = 0;
			for(i = 0 ; i < max ; i++) {
				NodeList n = list.item(i).getChildNodes();

				emp = new Emplacement();
				String[] temp = n.item(INDEX_COORDONNEES).getChildNodes().item(0).getNodeValue().split(" , ");
				temp[0] = temp[0].substring(2);
				temp[1] = temp[1].substring(0, temp[1].length() -1);
				Double[] coordonnees = {Double.parseDouble(temp[0]),Double.parseDouble(temp[1])} ;
				emp.set_l(coordonnees);
				
				emp.setQuartier(n.item(INDEX_QUARTIER).getChildNodes().item(0).getNodeValue());
				emp.setCommune(n.item(INDEX_COMMUNE).getChildNodes().item(0).getNodeValue());
				emp.setAdresse(n.item(INDEX_ADRESSE).getChildNodes().item(0).getNodeValue());
				
				e.getData().add(emp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
}
