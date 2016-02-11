package br.com.ifce.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cars")
public class ListCars implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Car> listCars;
	
	@XmlElement(name="car")
	public List<Car> getListCars(){
		return listCars;
	}
	
	public void setListCars(List<Car> listCars){
		this.listCars = listCars;
	}
	
	@Override
	public String toString(){
		return "ListCars = [ cars "+listCars+"]";
	}
}
