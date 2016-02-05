package com.hackerrank.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.hackerrank.dto.MatrixWrapper;

public class MatrixService {
	
	
	public static Map<Integer, MatrixWrapper> map =  new HashMap<Integer, MatrixWrapper>();
	
	
	
	
	public static void main(String[] args) {
		Integer N = 4;
		Integer M = 2;

		MatrixWrapper mw = createMatrix(N);

		
		
		update(mw.getId(), 2, 2, 2, 4);
		query(mw.getId(), 1, 1, 1, 3, 3, 3);
		update(mw.getId(), 1, 1, 1, 23);		
		query(mw.getId(), 2, 2, 2, 4, 4, 4);
		query(mw.getId(), 1, 1, 1, 3, 3, 3);
		/*
		UPDATE 2 2 2 4
		QUERY 1 1 1 3 3 3
		UPDATE 1 1 1 23
		QUERY 2 2 2 4 4 4
		QUERY 1 1 1 3 3 3
		*/
		
		mw = createMatrix(2);
		/*
		UPDATE 2 2 2 1
		QUERY 1 1 1 1 1 1
		QUERY 1 1 1 2 2 2
		QUERY 2 2 2 2 2 2
		
		4
4
27
0
1
1


		*/
		update(mw.getId(), 2, 2, 2, 1);
		query(mw.getId(), 1, 1, 1, 1, 1, 1);
		query(mw.getId(), 1, 1, 1, 2, 2, 2);
		query(mw.getId(), 2, 2, 2, 2, 2, 2);

	}
	
	
	
	
	
	
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

	public static void update(int matrixId, int x, int y, int z, int w) {
		int[][][] m = map.get(matrixId).getMatrix();
		
		x = x - 1;
		y = y - 1;
		z = z - 1;
		
		System.out.println("UPDATE");
		System.out.println("X " +x);
		System.out.println("Y " +y);
		System.out.println("Z " +z);
		System.out.println("w " +w);
		m[x][y][z] = w;
		
		System.out.println(Arrays.deepToString(map.get(matrixId).getMatrix()));
	}

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
