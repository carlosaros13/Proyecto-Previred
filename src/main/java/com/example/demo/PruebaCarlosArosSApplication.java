package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaCarlosArosSApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(PruebaCarlosArosSApplication.class, args);

		String json = "{ \"id\": 1, \"fechaCreacion\": \"1987-10-01\", \"fechaFin\": \"2018-08-01\", \"fechas\": [ \"1987-12-01\", \"1988-11-01\", \"1988-12-01\", \"1989-02-01\", \"1990-02-01\", \"1990-04-01\", \"1990-05-01\", \"1990-08-01\", \"1990-12-01\", \"1991-03-01\", \"1991-04-01\", \"1991-05-01\", \"1991-06-01\", \"1991-11-01\", \"1992-06-01\", \"1993-05-01\", \"1993-10-01\", \"1994-07-01\", \"1994-09-01\", \"1995-02-01\", \"1995-04-01\", \"1995-06-01\", \"1995-12-01\", \"1996-03-01\", \"1996-05-01\", \"1996-06-01\", \"1996-12-01\", \"1997-01-01\", \"1997-02-01\", \"1997-03-01\", \"1997-04-01\", \"1997-06-01\", \"1997-07-01\", \"1997-10-01\", \"1997-11-01\", \"1998-01-01\", \"1998-04-01\", \"1998-06-01\", \"1998-11-01\", \"1999-06-01\", \"1999-09-01\", \"1999-12-01\", \"2000-01-01\", \"2000-03-01\", \"2000-05-01\", \"2000-09-01\", \"2000-12-01\", \"2001-11-01\", \"2001-12-01\", \"2002-05-01\", \"2002-11-01\", \"2002-12-01\", \"2003-01-01\", \"2003-11-01\", \"2003-12-01\", \"2004-03-01\", \"2004-06-01\", \"2004-09-01\", \"2005-03-01\", \"2005-06-01\", \"2005-10-01\", \"2005-11-01\", \"2006-04-01\", \"2006-10-01\", \"2007-02-01\", \"2007-03-01\", \"2007-05-01\", \"2007-07-01\", \"2007-09-01\", \"2008-04-01\", \"2008-10-01\", \"2009-01-01\", \"2009-07-01\", \"2010-03-01\", \"2010-06-01\", \"2010-08-01\", \"2010-10-01\", \"2010-11-01\", \"2010-12-01\", \"2011-03-01\", \"2011-07-01\", \"2011-09-01\", \"2011-10-01\", \"2012-01-01\", \"2012-04-01\", \"2012-08-01\", \"2013-02-01\", \"2013-06-01\", \"2013-11-01\", \"2014-06-01\", \"2014-07-01\", \"2014-10-01\", \"2014-11-01\", \"2015-08-01\", \"2015-09-01\", \"2015-11-01\", \"2017-02-01\", \"2017-05-01\", \"2017-10-01\", \"2018-02-01\", \"2018-07-01\" ] }";
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonFinal = new JSONObject();
		try {
			JSONArray fechasFaltantes = new JSONArray();
			ArrayList<Object> fechasGenerales = new ArrayList<Object>();
			ArrayList<Object> fechasGeneradas = new ArrayList<Object>();
			jsonObject = new JSONObject(json);
			String id = jsonObject.getString("id");
			String fechaInicio = jsonObject.getString("fechaCreacion");
			String fechaFin = jsonObject.getString("fechaFin");
			JSONArray arrayFechas = (JSONArray) jsonObject.get("fechas");
			Period diff = Period.between(LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin));
			int years = diff.getYears();
			int months = diff.getMonths();
			int monthsYears = years * 12;
			months += monthsYears;

			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateInicio = dFormat.parse(fechaInicio);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateInicio);
			for (int i = 0; i < months; i++) {
				cal.add(Calendar.MONTH, 1);
				String dateInici = dFormat.format(cal.getTime());
				fechasGenerales.add(dateInici);
			}

			for (int i = 0; i < arrayFechas.length(); i++) {
				fechasGeneradas.add(arrayFechas.get(i));
			}

			for (int i = 0; i < fechasGenerales.size(); i++) {
				if (!fechasGeneradas.contains(fechasGenerales.get(i))) {
					fechasFaltantes.put(fechasGenerales.get(i));
				}
			}

			jsonFinal.put("id", id);
			jsonFinal.put("fechaCreacion", fechaInicio);
			jsonFinal.put("fechaFin", fechaFin);
			jsonFinal.put("fechasFaltantes", fechasFaltantes);

			System.out.println(jsonFinal);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
