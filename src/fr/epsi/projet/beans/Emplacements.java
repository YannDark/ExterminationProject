package fr.epsi.projet.beans;

import java.util.List;

public class Emplacements {
	
	int nb_results;
	List<Emplacement> data;
	

	/**
	 * @return the nb_results
	 */
	public int getNb_results() {
		return nb_results;
	}
	/**
	 * @param nb_results the nb_results to set
	 */
	public void setNb_results(int nb_results) {
		this.nb_results = nb_results;
	}
	/**
	 * @return the data
	 */
	public List<Emplacement> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<Emplacement> data) {
		this.data = data;
	}
	
	
}
