package fr.epsi.projet.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.common.Constantes;

public class ServiceRest {
	
	/**
	 * @return la liste d'emplacement où se trouve les bennes à verre.
	 */
	public List<Emplacement> getBennes() {
		
		String ret = callHttpRequest("{\"TYPE_DECHETS\":{\"$eq\":\"verre_ent\"}}");
		
		//TODO : conversion des données JSON en objet Java 
		System.out.println(ret);
		return null;
	}
	
	public String callHttpRequest(String data){
		String returnString = null;
		
		try {
			
			URL url = new URL(Constantes.URL_API_BENNES + data);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
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
}
