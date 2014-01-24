package fr.epsi.projet.beans;


/**
 * 
 * @author Dark
 * Classe représentant l'emplacement physique de la benne à verre
 */
public class Emplacement {
	//même nom de champ que renvoie l'API, oui c'est moche je sais 
	
	String		quartier;
	Double[]	_l;
	String		commune;
	String 		adresse;
	
	/**
	 * @return the quartier
	 */
	public String getQuartier() {
		return quartier;
	}
	/**
	 * @param quartier the quartier to set
	 */
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	/**
	 * @return the _l
	 */
	public Double[] get_l() {
		return _l;
	}
	/**
	 * @param _l the _l to set
	 */
	public void set_l(Double[] _l) {
		this._l = _l;
	}
	/**
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}
	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}


