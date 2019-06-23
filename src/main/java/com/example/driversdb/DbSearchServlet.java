package com.example.driversdb;

import com.example.driversdb.dao.CarDAO;
import com.example.driversdb.dao.CityDAO;
import com.example.driversdb.dao.DriverDAO;

import com.example.driversdb.entity.Car;
import com.example.driversdb.entity.City;
import com.example.driversdb.entity.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/DbSearch")
public class DbSearchServlet extends HttpServlet {

    private static final long serialVersionUID = -744079388448163813L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        CarDAO carDAO = new CarDAO();
        CityDAO cityDAO = new CityDAO();
        DriverDAO driverDAO = new DriverDAO();

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        ArrayList<Driver> result = new ArrayList<>();

        String searchType = request.getParameter("searchType");

        switch (searchType) {
            case ("byDriver"):
                String familyName = request.getParameter("familyName");
                String firstName = request.getParameter("firstName");
                String secondName = request.getParameter("secondName");
                if (familyName == null || "".equals(familyName)) {
                    if (firstName == null || "".equals(firstName)) {
                        out.println("<h3>Поиск по отчеству \"<strong>" + secondName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversBySecondName(secondName));
                    } else if (secondName == null || "".equals(secondName)) {
                        out.println("<h3>Поиск по имени \"<strong>" + firstName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversByFirstName(firstName));
                    } else {
                        out.println("<h3>Поиск по имени \"<strong>" + firstName + "</strong>\" и отчеству \"<strong>"
                                + secondName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversByFirstNameAndSecondName(firstName, secondName));
                    }
                } else {
                    if ((firstName == null || "".equals(firstName)) && (secondName == null || "".equals(secondName))) {
                        out.println("<h3>Поиск по фамилии \"<strong>" + familyName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversByFamilyName(familyName));
                    } else if (firstName == null || "".equals(firstName)) {
                        out.println("<h3>Поиск по отчеству \"<strong>" + secondName + "</strong>\" и фамилии \"<strong>"
                                + familyName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversBySecondNameAndFamilyName(secondName, familyName));
                    } else if (secondName == null || "".equals(secondName)) {
                        out.println("<h3>Поиск по имени \"<strong>" + firstName + "</strong>\" и фамилии \"<strong>" +
                                familyName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversByFirstNameAndFamilyName(firstName, familyName));
                    } else {
                        out.println("<h3>Поиск по имени \"<strong>" + firstName + "</strong>\", отчеству \"<strong>" +
                                secondName + "</strong> и фамилии \"<strong>" + familyName + "</strong>\":</h3>");
                        result.addAll(driverDAO.findDriversByFirstNameAndSecondNameAndFamilyName(firstName, secondName,
                                familyName));
                    }
                }
                break;
            case ("byCar"):
                ArrayList<Car> cars = new ArrayList<>();
                String plate = request.getParameter("plate");
                out.println("<h3>Поиск по госномеру автомобиля \"<strong>" + plate + "</strong>\":</h3>");
                cars.addAll(carDAO.findCarsByPlate(plate));
                cars.forEach(car -> {
                    Driver driver = driverDAO.findDriverById(car.getDriverId()).get();
                    if (!result.contains(driver)) result.add(driver);
                });
                break;
            case ("byCity"):
                ArrayList<City> cities = new ArrayList<>();
                String cityName = request.getParameter("cityName");
                out.println("<h3>Поиск по городу \"<strong>" + cityName + "</strong>\":</h3>");
                cities.addAll(cityDAO.findCitiesByName(cityName));
                cities.forEach(city -> {
                    result.addAll(driverDAO.findDriversByCityId(city.getId()));
                });
                break;
            default:
                out.println("<h3>Неправильное значение searchType: " + searchType + "</h3>");
        }
        if (result.size() == 0 || result == null) {
            out.println("<h3 style=\"color:red;\">РЕЗУЛЬТАТЫ НЕ НАЙДЕНЫ</h3>");
        } else {
            out.println("<table border=1><thead><tr><th>Фамилия<th>Имя<th>Отчество<th>Город<th>Авто<th>Модель<th>Госномер</tr></thead><tbody><tr>");
            result.forEach(driver -> {
                ArrayList<Car> cars = carDAO.findCarsByDriverId(driver.getId());
                String rowspan = "";
                if (cars.size() > 1) rowspan = " rowspan=" + cars.size();
                out.println("<td" + rowspan + ">" + driver.getFamilyName() + "<td" + rowspan + ">" +
                        driver.getFirstName() + "<td" + rowspan + ">" + driver.getSecondName() + "<td" +
                        rowspan + ">" + cityDAO.findCityById(driver.getCityId()).get().getName());
                if (cars.size() != 0) cars.forEach(car -> out.println("<td>" + car.getType() + "<td>" + car.getModel() +
                        "<td>" + car.getPlate() + "</tr><tr>"));
                else out.println("</tr><tr>");
            });
            out.println("</tbody></table>");
        }
    }
}