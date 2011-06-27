package test;

import com.semrobots.math.Matrix;
import com.semrobots.math.MatrixConstants;
import com.semrobots.math.exceptions.InvalidMatrixOpException;

import junit.framework.TestCase;

public class TestMatrix extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	
	public void testConstruction() {
		//cada uno de los constructores debe funcionar
		Matrix m = new Matrix(2, 4);
		assertEquals(m.getXLen(), 2);
		assertEquals(m.getYLen(), 4);
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < 4; j++){
				assertEquals(m.get(i, j), 0.0);
			}
		}
		
		m = new Matrix(4);
		assertEquals(m.getXLen(), 1);
		assertEquals(m.getYLen(), 4);
		for (int i = 0; i < 1; i++){
			for (int j = 0; j < 4; j++){
				assertEquals(m.get(i, j), 0.0);
			}
		}
		
		m = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		assertEquals(m.getXLen(), 3);
		assertEquals(m.getYLen(), 3);
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				assertEquals(m.get(i, j), (1.0*(1+j+3*i)));
			}
		}
		
		Matrix m2 = new Matrix(m);	//debe copiarlo, pero no debe ser igual, ni tampoco su matriz
		assertTrue(m.equals(m2) && m != m2 && m.getArray() != m2.getArray());
	}

	public void testEquals(){
		Matrix m1 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		Matrix m2 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		Matrix n1 = new Matrix(new double[][]{{0.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		Matrix n2 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,8.0,6.0},{7.0,8.0,9.0}});
		Matrix n3 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0},{10.0,11.0,12.0}});
		assertTrue(m1.equals(m1));
		assertTrue(m2.equals(m2));
		assertTrue(n1.equals(n1));
		
		assertTrue(m1.equals(m2));
		assertTrue(m2.equals(m1));
		assertFalse(m1.equals(n1));
		assertFalse(n1.equals(m1));
		assertFalse(m1.equals(n2));
		assertFalse(n2.equals(m1));
		assertFalse(m1.equals(n3));
		assertFalse(n3.equals(m1));
	}
	
	public void testTranspose(){
		//transpuesto bien (incluso en matrices no cuadradas)
		Matrix m = new Matrix(new double[][]{{1.0, 2.0},{3.0,4.0},{5.0,6.0},{7.0,8.0}});
		Matrix m2 = new Matrix(m);
		Matrix mt = new Matrix(new double[][]{{1.0, 3.0,5.0,7.0},{2.0,4.0,6.0,8.0}});
		//el original no es modificado
		assertEquals(m.transpose(), mt);
		assertEquals(m.transpose().transpose(), m2);
	}
	
	public void testAdd() {
		Matrix m = new Matrix(new double[][]{{1.0,2.0},{3.0,4.0},{5.0,6.0},{7.0,8.0}});
		Matrix mcopy = new Matrix(m);
		Matrix m2 = new Matrix(new double[][]{{1.0,4.0},{85.0,3.1},{15.0,26.0},{17.0,21.0}});
		Matrix mt = new Matrix(new double[][]{{1.0,3.0,5.0,7.0},{2.0,4.0,6.0,8.0}});
		try {
			m.add(mt);
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		
		//sumo 2 y da la suma
		assertEquals(m.add(m2), new Matrix(new double[][]{{2.0,6.0},{88.0,7.1},{20.0,32.0},{24.0,29.0}}));
		//el original no es modificado
		assertEquals(m, mcopy);
		//m + 0 = 0 + m = m
		Matrix m33 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		assertEquals(m33.add(MatrixConstants.ZERO_3_3), m33);
		assertEquals(MatrixConstants.ZERO_3_3.add(m33), m33);
	}
	
	public void testSubstract() {
		Matrix m = new Matrix(new double[][]{{1.0,2.0},{3.0,4.0},{5.0,6.0},{7.0,8.0}});
		Matrix mcopy = new Matrix(m);
		Matrix m2 = new Matrix(new double[][]{{1.0,4.0},{85.0,3.5},{15.0,26.0},{17.0,21.0}});
		Matrix mt = new Matrix(new double[][]{{1.0,3.0,5.0,7.0},{2.0,4.0,6.0,8.0}});
		try {
			m.substract(mt);
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		
		//resto 2 y da la resta
		assertEquals(m.substract(m2), new Matrix(new double[][]{{0.0,-2.0},{-82.0,0.5},{-10.0,-20.0},{-10.0,-13.0}}));
		//el original no es modificado
		assertEquals(m, mcopy);
		//m - m = 0 
		//m - 0 = m
		Matrix m33 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		assertEquals(m33.substract(MatrixConstants.ZERO_3_3), m33);
		assertEquals(m33.substract(m33), MatrixConstants.ZERO_3_3);
	}
	
	public void testMultiply() {
		Matrix m = new Matrix(new double[][]{{1.0,2.0},{3.0,4.0},{5.0,6.0},{7.0,8.0}});
		Matrix mcopy = new Matrix(m);
		Matrix m2 = new Matrix(new double[][]{{1.0,2.0,5.0,3.0},{2.0,5.0,6.0,1.0}});
		Matrix m3 = new Matrix(new double[][]{{1.0,2.0,5.0,3.0},{2.0,5.0,6.0,1.0},{1.0,3.0,1.0,1.0}});
		try {
			m.multiply(m);	//al no ser cuadrada, debe fallar por tener distinto tamaño
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		try {
			m2.multiply(m.transpose());
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		try {
			m.multiply(m2.transpose());
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		
		//resto 2 y da la resta
		assertEquals(m.multiply(m2), new Matrix(new double[][]{{53.0,64.0},{54.0,68.0}}));
		//el original no es modificado
		assertEquals(m, mcopy);
		
		assertEquals(m.multiply(m3), new Matrix(new double[][]{{53.0,64.0},{54.0,68.0},{22.0,28.0}}));
		
		//m * id = id * m = m
		//m * 0 = 0 * m = 0
		Matrix m33 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		assertEquals(m33.multiply(MatrixConstants.ZERO_3_3), MatrixConstants.ZERO_3_3);
		assertEquals(MatrixConstants.ZERO_3_3.multiply(m33), MatrixConstants.ZERO_3_3);
		assertEquals(m33.multiply(MatrixConstants.ID_3_3), m33);
		assertEquals(MatrixConstants.ID_3_3.multiply(m33), m33);
	}

	
	public void testScale() {
		Matrix id = MatrixConstants.ID_3_3;
		Matrix scaled = id.scale(2.0);
		assertTrue(scaled.almostEqual(new Matrix(new double[][]{{2.0,0.0,0.0},{0.0,2.0,0.0},{0.0,0.0,2.0}})));
		Matrix m33 = new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}});
		scaled = m33.scale(1.5);
		assertTrue(scaled.almostEqual(new Matrix(new double[][]{{1.5,3.0,4.5},{6.0,7.5,9.0},{10.5,12.0,13.5}})));
	}
	
	
	public void testTraza(){
		assertEquals(3.0, MatrixConstants.ID_3_3.traza());
		assertEquals(15.0, new Matrix(new double[][]{{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}}).traza());
		
		try {
			new Matrix(5, 4).traza();
			fail();
		} catch (InvalidMatrixOpException e) {
		}
		
		assertEquals(0.0, new Matrix(4, 4).traza());
	}
}
