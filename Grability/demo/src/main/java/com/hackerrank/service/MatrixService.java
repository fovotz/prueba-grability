package com.hackerrank.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.hackerrank.dto.MatrixWrapper;
/**********************
 * Clase encargada de exponer los servicios relacionado con la matrix
 *********************/
public class MatrixService {
	
	//Map que conteine MatrixWrapper para manejar temas de concurrencia
	public static Map<Integer, MatrixWrapper> map =  new HashMap<Integer, MatrixWrapper>();
	
	/***********************************
	 * Realiza la operacion QUERY sobre la matriz
	 **********************************/ 
	public static int query(int matrixId, int x1, int y1, int z1, int x2, int y2, int z2) {
		int[][][] m = map.get(matrixId).getMatrix();
		x1 = x1 - 1;
		y1 = y1 - 1;
		z1 = z1 - 1;
		
		x2 = x2 - 1;
		y2 = y2 - 1;
		z2 = z2 - 1;
		int suma = 0;
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				for (int z = z1; z <= z2; z++) {
					suma += m[x][y][z];
				}
			}
		}
		System.out.println(suma);
		return suma;
	}
	/***********************************
	 * Realiza la operacion UPTDATE sobre la matriz
	 **********************************/ 
	public static void update(int matrixId, int x, int y, int z, int w) {
		int[][][] m = map.get(matrixId).getMatrix();
		x = x - 1;
		y = y - 1;
		z = z - 1;
		m[x][y][z] = w;
	}
	
	/***********************************
	 * Crea la matrix
	 **********************************/ 
	public static MatrixWrapper createMatrix(int N) {
		MatrixWrapper mw = new MatrixWrapper();
		mw.setMatrix(new int[N][N][N]);
		if(map.size() > 50){
			map =  new HashMap<Integer, MatrixWrapper>();
			System.gc();
		}
		
		mw.setId(map.size());
		map.put(map.size(), mw);
		
		
		return mw;
	}
}
