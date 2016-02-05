package com.hackerrank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackerrank.dto.MatrixWrapper;
import com.hackerrank.dto.Peticion;
import com.hackerrank.dto.Respuesta;
import com.hackerrank.service.MatrixService;

@Controller
@RequestMapping("/prueba")
public class HackerrankController {

	@RequestMapping(value = "/enviar", method = RequestMethod.POST)
	public @ResponseBody Respuesta greeting(@RequestBody Peticion peticion) {
		Respuesta r = new Respuesta();
		r.setM(peticion.getM());
		r.setN(peticion.getN());
		MatrixWrapper mw = MatrixService.createMatrix(peticion.getN());
		r.setMatrixId(mw.getId());
		r.setCasos(peticion.getCasos());
		return r;
	}
	
	@RequestMapping(value = "/accion", method = RequestMethod.POST)
	public @ResponseBody Respuesta accion(@RequestBody Peticion peticion) {
		Respuesta r = new Respuesta();
		
		if("QUERY".equals(peticion.getOperacion())){
			System.out.println("QUERY");
			int suma = MatrixService.query(
					peticion.getMatrixId(), 
					peticion.getX1(),
					peticion.getY1(), 
					peticion.getZ1(),
					peticion.getX2(),
					peticion.getY2(), 
					peticion.getZ2());
			r.setSuma(suma);
		}else{
			System.out.println("UPDATE");
			MatrixService.update(peticion.getMatrixId(), 
					peticion.getX1(),
					peticion.getY1(), 
					peticion.getZ1(), 
					peticion.getW());
		}
		return r;
	}
	
}
