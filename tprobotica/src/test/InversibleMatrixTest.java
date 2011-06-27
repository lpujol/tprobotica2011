package test;

import com.semrobots.math.InversibleMatrix;

import junit.framework.TestCase;

public class InversibleMatrixTest extends TestCase {

//	public void testDescartar() {
//		double[][] v = new double[][]{
//				{00,01,02,03,04},
//				{10,11,12,13,14},
//				{20,21,22,23,24},
//				{30,31,32,33,34},
//				{40,41,42,43,44}
//		};
//		InversibleMatrix m00 = new InversibleMatrix(InversibleMatrix.descartarFilaYColumna(v, 0, 0));
//		assertEquals(m00, new InversibleMatrix(new double[][]{
//				{11,12,13,14},
//				{21,22,23,24},
//				{31,32,33,34},
//				{41,42,43,44}
//		}));
//		InversibleMatrix m23 = new InversibleMatrix(InversibleMatrix.descartarFilaYColumna(v, 2, 3));
//		assertEquals(m23, new InversibleMatrix(new double[][]{
//				{00,01,03,04},
//				{10,11,13,14},
//				{20,21,23,24},
//				{40,41,43,44}
//		}));
//		InversibleMatrix m42 = new InversibleMatrix(InversibleMatrix.descartarFilaYColumna(v, 4, 2));
//		assertEquals(m42, new InversibleMatrix(new double[][]{
//				{00,01,02,03},
//				{10,11,12,13},
//				{30,31,32,33},
//				{40,41,42,43}
//		}));
//	}
//	
//	public void testDeterminante() {
//		InversibleMatrix m = new InversibleMatrix(new double[][]{
//				{1.0}
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 1.0));
//		
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 0.0},
//				{0.0, 1.0},
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 1.0));
//		
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 1.0},
//				{1.0, 1.0},
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 0.0));
//	
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 0.0},
//				{1.0, 1.0},
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 1.0));
//	
//		m = new InversibleMatrix(new double[][]{
//				{0.0, 1.0},
//				{1.0, 1.0},
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), -1.0));
//		
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 0.0, 0.0},
//				{0.0, 1.0, 0.0},
//				{0.0, 0.0, 1.0}
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 1.0));
//		
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 1.0, 1.0},
//				{0.0, 1.0, 1.0},
//				{0.0, 0.0, 1.0}
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 1.0));
//
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 1.0, 1.0},
//				{1.0, 1.0, 1.0},
//				{1.0, 1.0, 1.0}
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 0.0));
//		
//		m = new InversibleMatrix(new double[][]{
//				{1.0, 1.0, 1.0},
//				{0.0, 1.0, 1.0},
//				{0.0, 1.0, 1.0}
//		});
//		assertTrue(DoubleUtil.almostEquals(m.determinante(), 0.0));
//	}
//	
	
	public void testInvertir() {
		InversibleMatrix i = new InversibleMatrix(new double[][]{{1.0, 0.0},{0.0, 1.0}});
		InversibleMatrix r = i.invert();
		assertEquals(i, r);
		
		i = new InversibleMatrix(new double[][]{{2.0, 0.0},{0.0, 2.0}});
		r = i.invert();
		assertEquals(new InversibleMatrix(new double[][]{{0.5,0.0},{0.0,0.5}}), r);
		
		i = new InversibleMatrix(new double[][]{{1.0, 1.0},{1.0, -1.0}});
		r = i.invert();
		assertTrue(new InversibleMatrix(new double[][]{{0.5,0.5},{0.5,-0.5}}).almostEqual(r));
		
		
		i = new InversibleMatrix(new double[][]{{1.0, 0.0, 0.0, 0.0},{0.0, 2.0, 0.0, 0.0},{0.0, 0.0, 3.0, 0.0},{0.0, 0.0, 0.0, 4.0}});
		r = i.invert();
		assertTrue(new InversibleMatrix(new double[][]{{1.0, 0.0, 0.0, 0.0},{0.0, 0.5, 0.0, 0.0},{0.0, 0.0, 1/3.0, 0.0},{0.0, 0.0, 0.0, 0.25}}).almostEqual(r));
		
		//creo que funciona
	}
}
