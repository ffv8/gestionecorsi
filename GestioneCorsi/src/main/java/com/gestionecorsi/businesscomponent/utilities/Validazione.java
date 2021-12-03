package com.gestionecorsi.businesscomponent.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Validazione {
	public boolean convalidaString(String s) {
		if(s == null || !(s instanceof String) || s.matches(".*\\d.*") || s.length() > 30) 
			return false;
		
		return true;
	}
	
	public boolean convalidaCorsista(String nome, String cognome) {
		if(!(convalidaString(nome)) || !(convalidaString(cognome)))
			return false;
		
		return true;
	}
	
	public boolean convalidaData(String data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			format.parse(data);
		} catch(ParseException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean convalidaDifferenzaDate(String inizio, String fine) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date d1 = format.parse(inizio);
			Date d2 = format.parse(fine);
			
			long diffInMillies = Math.abs(d2.getTime() - d1.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    
		    if(diff < 2) 
		    	return false;
		} catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	public boolean convalidaAula(String s) {
		if(s == null || !(s instanceof String) || !(s.matches("^[a-zA-Z0-9]+$")) || s.length() > 30)
			return false;
		
		return true;
	}
	
	public boolean convalidaCorso(String nome, String inizio, String fine, String commenti, String aula) {
		if(!(convalidaString(nome)))
			return false;
		
		if(inizio == null || !(convalidaData(inizio)))
			return false;
		
		if(fine == null || !(convalidaData(fine)))
			return false;
		
		if(!convalidaDifferenzaDate(inizio, fine))
			return false;
		
		if(commenti.length() > 200)
			return false;
		
		if(!(convalidaAula(aula)))
			return false;
		
		return true;
	}
}
