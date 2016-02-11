package br.com.ifce.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {
	private CarDAO db = new CarDAO();
	
	public List<Car> getCars(){
		try{
			List<Car> cars = db.getListCars();
			return cars;
		}catch(SQLException e){
			e.printStackTrace();
			return new ArrayList<Car>();
		}
	}
	
	public Car getCar(Long id){
		try {
			return db.getCarroById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteCar(Long id){
		try {
			return db.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean save(Car car){
		try {
			db.save(car);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Car> findByName(String name){
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Car> findByType(String type){
		try {
			return db.findByType(type);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
