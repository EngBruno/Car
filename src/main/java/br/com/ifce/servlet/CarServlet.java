package br.com.ifce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ifce.domain.Car;
import br.com.ifce.domain.CarService;
import br.com.ifce.domain.ListCars;
import br.com.ifce.util.JAXBUtil;
import br.com.ifce.util.ServletUtil;

@WebServlet("/cars/*")
public class CarServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CarService carservice = new CarService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Car> listCars=carservice.getCars();
		ListCars list = new ListCars();
		list.setListCars(listCars);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		ServletUtil.writeJson(resp, json);

	}
}
