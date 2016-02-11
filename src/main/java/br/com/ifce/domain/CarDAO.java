package br.com.ifce.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends BaseDAO{
	
	public Car getCarroById(long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnetion();
			stmt = conn.prepareStatement("select*from carro where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Car cars = createCar(rs);
				rs.close();
				return cars;
			}
		} finally {
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Car> findByName(String name) throws SQLException{
		List<Car> listCars = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnetion();
			stmt = conn.prepareStatement("select*from carro where lower(nome) like ?");
			stmt.setString(1, "%"+name.toLowerCase()+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Car cars = createCar(rs);
				listCars.add(cars);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
		return listCars;
	}
	
	public List<Car> findByType(String type) throws SQLException{
		List<Car> listCars = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnetion();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, type);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Car cars =createCar(rs);
				listCars.add(cars);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
		return listCars;
	}
	
	public List<Car> getListCars() throws SQLException{
		List<Car> listCars = new ArrayList<>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnetion();
			stmt = conn.prepareStatement("select*from carro");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Car cars = createCar(rs);
				listCars.add(cars);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
		return listCars;
	}
	
	public Car createCar(ResultSet result) throws SQLException{
		Car car = new Car();
		car.setId(result.getLong("id"));
		car.setName(result.getString("nome"));
		car.setDesc(result.getString("descricao"));
		car.setUrlPhoto(result.getString("url_foto"));
		car.setUrlVideo(result.getString("url_video"));
		car.setLatitude(result.getString("latitude"));
		car.setLongitude(result.getString("longitude"));
		car.setType(result.getString("tipo"));
		return car;
	}
	
	void save(Car car) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnetion();
			if(car.getId() == null){
				String url = "insert into carro (nome,descricao,url_foto,url_video,latitude,longitude,tipo) values(?,?,?,?,?,?,?)";
				stmt = conn.prepareStatement(url,Statement.RETURN_GENERATED_KEYS);
			}else{
				stmt = conn.prepareStatement("update carro set nome=?,descricao=?,url_foto=?,"
						+ "url_video=?,latitude=?,longitude=?tipo=?,where id=?");
			}
			
			stmt.setString(1, car.getName());
			stmt.setString(2, car.getDesc());
			stmt.setString(3, car.getUrlPhoto());
			stmt.setString(4, car.getUrlVideo());
			stmt.setString(5, car.getLatitude());
			stmt.setString(6, car.getLongitude());
			stmt.setString(7, car.getType());
			
			if(car.getId()!=null){
				stmt.setLong(8, car.getId());
			}
			int count = stmt.executeUpdate();
			if(count == 0){
				throw new SQLException("Erro ao inserir car");
			}
			
			if(car.getId() == null){
				Long id = getGeneratedId(stmt);
				car.setId(id);
			}
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public static long getGeneratedId(Statement stmt) throws SQLException{
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()){
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}
	
	public boolean delete(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnetion();
			stmt = conn.prepareStatement("delete from carro where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
			if(conn!=null){
				conn.close();
			}
		}
	}
}
