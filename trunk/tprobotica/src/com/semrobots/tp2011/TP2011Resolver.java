package com.semrobots.tp2011;

import com.semrobots.math.Matrix;

public class TP2011Resolver {

	public Matrix resolver(ParametrosTp2011 parametros,
			ParametrosTp2011 parametros2) {
		Matrix B1=parametros.getS();
		Matrix B2=parametros2.getS();
		Matrix A1=parametros.getR().multiply(parametros.getUvp());
		Matrix A2=parametros2.getR().multiply(parametros2.getUvp());
		
		
		double A2B2=A2.get(0, 0)*B2.get(0, 0)+A2.get(0, 1)*B2.get(0, 1)+A2.get(0, 2)*B2.get(0, 2);
		double A1B1=A1.get(0, 0)*B1.get(0, 0)+A1.get(0, 1)*B1.get(0, 1)+A1.get(0, 2)*B1.get(0, 2);
		
		double moduloA2=Math.sqrt(A2.get(0, 0)*A2.get(0, 0)+A2.get(0, 1)*A2.get(0, 1)+A2.get(0, 2)*A2.get(0, 2));
		double moduloA1=Math.sqrt(A1.get(0, 0)*A1.get(0, 0)+A1.get(0, 1)*A1.get(0, 1)+A1.get(0, 2)*A1.get(0, 2));
		
		double moduloB2=Math.sqrt(B2.get(0, 0)*B2.get(0, 0)+B2.get(0, 1)*B2.get(0, 1)+B2.get(0, 2)*B2.get(0, 2));
		double moduloB1=Math.sqrt(B1.get(0, 0)*B1.get(0, 0)+B1.get(0, 1)*B1.get(0, 1)+B1.get(0, 2)*B1.get(0, 2));
		
		double moduloA1cuadrado=moduloA1*moduloA1;
		double moduloA2cuadrado=moduloA2*moduloA2;
		
		double A1porB1sobreModuloA1=(A1B1)/moduloA1;
		double A2porB2sobreModuloA2=(A2B2)/moduloA2;
		
		double A1porB1sobreModuloA1cuadrado=(A1B1)/(moduloA1cuadrado);
		double A2porB2sobreModuloA2cuadrado=(A2B2)/(moduloA2cuadrado);
		
		double escalarGrande=Math.sqrt(Math.abs((3*A1porB1sobreModuloA1*A1porB1sobreModuloA1+moduloB1*moduloB1-(3*A2porB2sobreModuloA2*A2porB2sobreModuloA2+moduloB2*moduloB2))))/2;
		
		Matrix res=A2.scale(A2porB2sobreModuloA2cuadrado).add(B2).substract(A1.scale(A1porB1sobreModuloA1cuadrado).add(B1)).scale(escalarGrande).add(A1.scale(A1porB1sobreModuloA1cuadrado)).add(B1);
		return res;
	}
	
	public void resolver2(ParametrosTp2011 parametros,
			ParametrosTp2011 parametros2) {
		Matrix R=parametros.getR();
		double U=parametros.getUvp().get(0, 0)/parametros.getUvp().get(0, 2);
		double V=parametros.getUvp().get(0, 1)/parametros.getUvp().get(0, 2);
		
		Matrix L=R.multiply(parametros.getS());
		double T1=U*L.get(0, 2)-L.get(0, 0);
		double T2=V*L.get(0, 2)-L.get(0, 1);
		
		Matrix N1=new Matrix(1, 3);
		N1.set(0, 0, R.get(0, 0)-U*R.get(2, 0));
		N1.set(0, 1, R.get(0, 1)-U*R.get(2, 1));
		N1.set(0, 2, R.get(0, 2)-U*R.get(2, 2));
		
		Matrix N2=new Matrix(1, 3);
		N2.set(0, 0, R.get(1, 0)-V*R.get(2, 0));
		N2.set(0, 1, R.get(1, 1)-V*R.get(2, 1));
		N2.set(0, 2, R.get(1, 2)-V*R.get(2, 2));
		
		double x1=N1.get(0, 0);
		double x2=N1.get(0, 1);
		double x3=N1.get(0, 2);
		double x4=N2.get(0, 0);
		double x5=N2.get(0, 1);
		double x6=N2.get(0, 2);
		
		System.out.println((x1==0?"":String.valueOf(N1.get(0, 0))+"X+")+(x2==0?"":String.valueOf(N1.get(0, 1))+"Y+")+(x3==0?"":String.valueOf(N1.get(0, 2))+"Z")+"="+String.valueOf(T1));
		System.out.println((x4==0?"":String.valueOf(N2.get(0, 0))+"X+")+(x5==0?"":String.valueOf(N2.get(0, 1))+"Y+")+(x6==0?"":String.valueOf(N2.get(0, 2))+"Z")+"="+String.valueOf(T2));
		
		Matrix R2=parametros2.getR();
		double U2=parametros2.getUvp().get(0, 0)/parametros2.getUvp().get(0, 2);
		double V2=parametros2.getUvp().get(0, 1)/parametros2.getUvp().get(0, 2);
		
		Matrix L2=R2.multiply(parametros2.getS());
		double T3=U2*L2.get(0, 2)-L2.get(0, 0);
		double T4=V2*L2.get(0, 2)-L2.get(0, 1);
		
		Matrix N3=new Matrix(1, 3);
		N3.set(0, 0, R2.get(0, 0)-U2*R2.get(2, 0));
		N3.set(0, 1, R2.get(0, 1)-U2*R2.get(2, 1));
		N3.set(0, 2, R2.get(0, 2)-U2*R2.get(2, 2));
		
		Matrix N4=new Matrix(1, 3);
		N4.set(0, 0, R2.get(1, 0)-V2*R2.get(2, 0));
		N4.set(0, 1, R2.get(1, 1)-V2*R2.get(2, 1));
		N4.set(0, 2, R2.get(1, 2)-V2*R2.get(2, 2));
		
		/*double x7=N3.get(0, 0);
		double x8=N3.get(0, 1);
		double x9=N3.get(0, 2);
		double x10=N4.get(0, 0);
		double x11=N4.get(0, 1);
		double x12=N4.get(0, 2);*/
		System.out.println(String.valueOf(N3.get(0, 0))+"X+"+String.valueOf(N3.get(0, 1))+"Y+"+String.valueOf(N3.get(0, 2))+"Z="+String.valueOf(T3));
		System.out.println(String.valueOf(N4.get(0, 0))+"X+"+String.valueOf(N4.get(0, 1))+"Y+"+String.valueOf(N4.get(0, 2))+"Z="+String.valueOf(T4));
		
		Matrix res1=resolverEcuacion3(N1,N2,N3,T1,T2,T3);
		System.out.println(String.valueOf(res1.get(0, 0))+" "+String.valueOf(res1.get(0, 1))+" "+String.valueOf(res1.get(0, 2)));
		
		
		res1=resolverEcuacion3(N1,N2,N4,T1,T2,T4);
		System.out.println(String.valueOf(res1.get(0, 0))+" "+String.valueOf(res1.get(0, 1))+" "+String.valueOf(res1.get(0, 2)));
		
		res1=resolverEcuacion3(N1,N3,N4,T1,T3,T4);
		System.out.println(String.valueOf(res1.get(0, 0))+" "+String.valueOf(res1.get(0, 1))+" "+String.valueOf(res1.get(0, 2)));
		
		res1=resolverEcuacion3(N2,N3,N4,T2,T3,T4);
		System.out.println(String.valueOf(res1.get(0, 0))+" "+String.valueOf(res1.get(0, 1))+" "+String.valueOf(res1.get(0, 2)));
		
		
	}

	public Matrix resolverEcuacion(Matrix fila1, Matrix fila2, Matrix fila3, double c1,
			double c2, double c3) {
		// TODO Auto-generated method stub
		
		int n, i, j, h, band, siga, miter;
		double m[][];
		double r[];
		double x[];
		double y[];
		double error[];
		double cont[];
		double suma, l, tol;
		n = 3;
		tol = 100;
		miter = 10000;
		m = new double[n][n];
		r = new double[n];
		x = new double[n];
		y = new double[n];
		cont = new double[n];
		error = new double[n];
		r[0]=c1;
		r[1]=c2;
		r[2]=c3;
		m[0][0]=fila1.get(0, 0);
		m[0][1]=fila1.get(0, 1);
		m[0][2]=fila1.get(0, 2);
		m[1][0]=fila2.get(0, 0);
		m[1][1]=fila2.get(0, 1);
		m[1][2]=fila2.get(0, 2);
		m[2][0]=fila3.get(0, 0);
		m[2][1]=fila3.get(0, 1);
		m[2][2]=fila3.get(0, 2);
		Matrix res=new Matrix(1,3);
		  for (i = 0; i <= n - 1; i++) {
			x[i] = 0;
			y[i] = 0;			
		}
		band = 0;
		for (i = 0; i < n; i++) {
			suma = 0;
			for (j = 0; j < n; j++) {
				if (i != j) {
					suma = suma + m[i][j];
				}
			}
			cont[i] = suma;
			if (Math.abs(m[i][i]) > cont[i]) {
				band = band + 1;
			}
		}
		if (band >= n-1) {
			siga = n - 1;
			int iter = 0;
			while (siga != n && iter < miter) {
				iter = iter + 1;
				for (i = 0; i < n; i++) {
					l = 0;
					for (j = 0; j < n; j++) {
						if (i == j) {
							l = l + r[i] / m[i][j];
						} else {
							l = l - ((m[i][j] * x[j]) / m[i][i]);
						}
					}
					x[i] = l;
				}
				for (i = 0; i < n; i++) {
					error[i] = Math.abs((x[i] - y[i]) / x[i]) * 100;
					y[i] = x[i];
				}
				siga = 0;
				for (i = 0; i < n; i++) {
					if (error[i] < tol) {
						siga = siga + 1;
					}
				}
			}
			h = 0;
			
			for (i = 0; i < n; i++) {
				h = h + 1;
				res.set(0, i, -x[i]);
			}
			System.out.println("El número total de iteraciones fue de "+iter);
		} /*else {
			JOptionPane
					.showMessageDialog(
							null,
							"No se puede solucionar por este metodo debido a que la matriz de coeficientes no es diagonalmente dominante");
		}*/

		return res;
	}
	
	private Matrix resolverEcuacion2(Matrix fila1, Matrix fila2, Matrix fila3, double c1,
			double c2, double c3) {
		// TODO Auto-generated method stub
		
		int n, i,k,s, j, h, band, siga, miter;
		double m[][],l[][],lt[][];
		double r[];
		double x[];
		double z[];
		double a[];
		double b[];
		double c[];
		double error[];
		double cont[];
		double suma,  tol;
		n = 3;
		tol = 100;
		miter = 10000;
		m = new double[n][n];
		l= new double [n][n];lt= new double [n][n];
		a= new double [n]; b= new double [n];c= new double [n-1];
		z= new double [n];
		r = new double[n];
		x = new double[n];
		cont = new double[n];
		error = new double[n];
		r[0]=c1;
		r[1]=c2;
		r[2]=c3;
		m[0][0]=fila1.get(0, 0);
		m[0][1]=fila1.get(0, 1);
		m[0][2]=fila1.get(0, 2);
		m[1][0]=fila2.get(0, 0);
		m[1][1]=fila2.get(0, 1);
		m[1][2]=fila2.get(0, 2);
		m[2][0]=fila3.get(0, 0);
		m[2][1]=fila3.get(0, 1);
		m[2][2]=fila3.get(0, 2);
		Matrix res=new Matrix(1,3);		 

		for (i = 0; i <= n - 1; i++) {
			k = i + 1;			
			for (j = 0; j <= n - 1; j++) {
				h = j + 1;
				
				if (i == j) {
					l[i][j] = 1;
				} else {
					l[i][j] = 0;
				}
			}
		}
		k = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (m[i][j] != m[j][i]) {
					k = 1;
				}
			}
		}
		h = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (m[i][j] < 0) {
					h = 1;
				}
			}
		}
		if (k == 0 && h == 0) {
			l[0][0] = Math.sqrt(m[0][0]);
			for (i = 0; i < n; i++) {
				for (j = 0; j <= i; j++) {
					if (i == j) {
						suma = 0;
						for (s = 0; s <= i - 1; s++) {
							suma = suma + Math.pow(l[i][s], 2);
						}
						l[i][i] = Math.sqrt(m[i][i] - suma);
					} else {
						suma = 0;
						for (s = 0; s <= j - 1; s++) {
							suma = suma + (l[j][s]) * (l[i][s]);
						}
						l[i][j] = (m[i][j] - suma) / l[j][j];
					}
				}
			}
			for (i = 0; i < n; i++) {
				k = i + 1;
				for (j = 0; j < n; j++) {
					h = j + 1;
					System.out.println(
							"el valor de la incognita x" + k + h + " es "
									+ l[i][j]);
				}
			}
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					a[j] = l[i][j];
				}
				for (j = 0; j < n; j++) {
					lt[j][i] = a[j];
				}
			}
			for (i = 0; i < n; i++) {
				k = i + 1;
				for (j = 0; j < n; j++) {
					h = j + 1;
					System.out.println(
							"el valor de la incognita lt" + k + h + " es "
									+ lt[i][j]);
				}
			}
			for (i = 0; i < n; i++) {
				suma = r[i];
				for (j = i; j >= 0; j--) {
					if (i == j) {
						suma = suma / l[i][j];
					} else {
						suma = suma - ((z[j] * l[i][j]) / l[i][i]);
					}
				}
				z[i] = suma;
			}
			for (i = n - 1; i >= 0; i--) {
				suma = z[i];
				for (j = i; j < n; j++) {
					if (i == j) {
						suma = suma / lt[i][j];
					} else {
						suma = suma - ((x[j] * lt[i][j]) / lt[i][i]);
					}
				}
				x[i] = suma;
			}
			for (i = 0; i < n; i++) {
				k = i + 1;
				System.out.println(
						"el valor de la incognita x" + k + " es " + x[i]);
			}
		} else {
			if (k == 1 && h == 1) {
				System.out.println(
								"La matriz es asimetrica y no esta definida positivamente");
			} else {
				if (k == 1) {
					System.out.println(
							"La matriz es asimetrica");
				} 
			}
		} 
		return res;
	}
	
	public Matrix resolverEcuacion3(Matrix fila1, Matrix fila2, Matrix fila3, double c1,
			double c2, double c3) {
		// TODO Auto-generated method stub
		
		int n,s,k, i, j, h, band, siga, miter;
		double m[][];
		double r[];
		double x[];
		double error[];
		double cont[];
		double suma, l, tol;
		double d;
		n = 3;
		tol = 100;
		miter = 10000;
		m = new double[n][n];
		r = new double[n];
		x = new double[n];
		cont = new double[n];
		error = new double[n];
		r[0]=c1;
		r[1]=c2;
		r[2]=c3;
		m[0][0]=fila1.get(0, 0);
		m[0][1]=fila1.get(0, 1);
		m[0][2]=fila1.get(0, 2);
		m[1][0]=fila2.get(0, 0);
		m[1][1]=fila2.get(0, 1);
		m[1][2]=fila2.get(0, 2);
		m[2][0]=fila3.get(0, 0);
		m[2][1]=fila3.get(0, 1);
		m[2][2]=fila3.get(0, 2);
		Matrix res=new Matrix(1,3);
		  for (i = 0; i <= n - 1; i++) {
			x[i] = 0;			
		}
		  
		  
		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				if (i == j) {
					d = m[i][j];
					for (s = 0; s < n; s++) {
						m[i][s] = ((m[i][s]) / d);
					}
					r[i] = ((r[i]) / d);
				} else {
					d = m[j][i];
					for (s = 0; s < n; s++) {
						m[j][s] = m[j][s] - (d * m[i][s]);
					}
					r[j] = r[j] - (d * r[i]);
				}
			}
		}
		for (i = n - 1; i >= 0; i--) {
			double y = r[i];
			for (j = n - 1; j >= i; j--) {
				y = y - x[j] * m[i][j];
			}
			x[i] = y;
		}
		for (i = 0; i < n; i++) {
			k = i + 1;
			System.out.println( "el valor de la incognita x"
					+ k + " es " + x[i]);
		}

		return res;
	}

}
