package test;

import com.semrobots.math.Matrix;
import com.semrobots.math.MatrixConstants;
import com.semrobots.math.util.DoubleUtil;
import com.semrobots.tp1.MatrixCalculator;

import junit.framework.TestCase;

public class RotTest extends TestCase {
	
	public void testBasic() {
		//p sobre el plano x-y
		//1era rot sobre eje z
		double u = Math.sqrt(2.0)/2.0;
		double v = Math.sqrt(2.0)/2.0;
		double w = 0.0;		//z deberia valer (0,-1,0)
		String eje = "x";	//2da rot sobre x
		String plano = "xy";
		Matrix m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		Matrix xconv = m.multiply(MatrixConstants.X_VERSOR);
		Matrix yconv = m.multiply(MatrixConstants.Y_VERSOR);
		Matrix zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		assertTrue(zconv.almostEqual(MatrixConstants.Y_VERSOR.scale(-1.0)));
		
		
		
		u = Math.sqrt(2.0)/2.0;
		v = Math.sqrt(2.0)/2.0;
		w = 0.0;		//z deberia valer (-1,0,0)
		eje = "y";		//2da rot sobre y
		plano = "xy";
		m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		xconv = m.multiply(MatrixConstants.X_VERSOR);
		yconv = m.multiply(MatrixConstants.Y_VERSOR);
		zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		assertTrue(zconv.almostEqual(MatrixConstants.X_VERSOR.scale(-1.0)));
		
		
		//p sobre el plano x-z
		//1era rot sobre eje y
		u = Math.sqrt(2.0)/2.0;
		v = 0.0;
		w = Math.sqrt(2.0)/2.0;
		eje = "x";		//2da rot sobre x
		plano = "xz";
		m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		xconv = m.multiply(MatrixConstants.X_VERSOR);
		yconv = m.multiply(MatrixConstants.Y_VERSOR);
		zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		assertTrue(yconv.almostEqual(MatrixConstants.Z_VERSOR.scale(-1.0)));
		
		
		u = Math.sqrt(2.0)/2.0;
		v = 0.0;
		w = Math.sqrt(2.0)/2.0;
		eje = "z";		//2da rot sobre z
		plano = "xz";
		m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		xconv = m.multiply(MatrixConstants.X_VERSOR);
		yconv = m.multiply(MatrixConstants.Y_VERSOR);
		zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		assertTrue(yconv.almostEqual(MatrixConstants.X_VERSOR.scale(-1.0)));
		
		
		
		//p sobre el plano y-z
		//1era rot sobre eje x
		u = 0.0;
		v = Math.sqrt(2.0)/2.0;
		w = Math.sqrt(2.0)/2.0;
		eje = "y";		//2da rot sobre y
		plano = "yz";
		m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		xconv = m.multiply(MatrixConstants.X_VERSOR);
		yconv = m.multiply(MatrixConstants.Y_VERSOR);
		zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		assertTrue(xconv.almostEqual(MatrixConstants.Z_VERSOR.scale(-1.0)));
		
		
		u = 0.0;
		v = Math.sqrt(2.0)/2.0;
		w = Math.sqrt(2.0)/2.0;
		eje = "z";		//2da rot sobre z
		plano = "yz";
		m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		xconv = m.multiply(MatrixConstants.X_VERSOR);
		yconv = m.multiply(MatrixConstants.Y_VERSOR);
		zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0));
		
		assertTrue(xconv.almostEqual(MatrixConstants.Y_VERSOR.scale(-1.0)));
	}
	
	public void testAleatorios() {
		testBasicoParametrizado(0.45623, 0.16253, 0.87489324274450765699, "x", "xy");
		testBasicoParametrizado(0.45623, 0.16253, 0.87489324274450765699, "x", "xz");
		testBasicoParametrizado(0.45623, 0.16253, 0.87489324274450765699, "y", "xy");
		
		testBasicoParametrizado(0.1786754, 0.438765, 0.88065905787077441494, "y", "xy");
	}

	/**
	 * testea modulo y ortogonalidad de los nuevos ejes
	 * @param u
	 * @param v
	 * @param w
	 * @param eje
	 * @param plano
	 */
	private void testBasicoParametrizado(double u, double v, double w,
			String eje, String plano) {
		Matrix m = MatrixCalculator.calculateMatrix(u, v, w, eje, plano);
		Matrix xconv = m.multiply(MatrixConstants.X_VERSOR);
		Matrix yconv = m.multiply(MatrixConstants.Y_VERSOR);
		Matrix zconv = m.multiply(MatrixConstants.Z_VERSOR);
		
		assertTrue(DoubleUtil.almostEquals(xconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.quadSum(), 1.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(zconv.quadSum(), 1.0, 5.0));
		
		//deberian ser ortogonales entre si
		assertTrue(DoubleUtil.almostEquals(xconv.transpose().multiply(yconv).quadSum(), 0.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(xconv.transpose().multiply(zconv).quadSum(), 0.0, 5.0));
		assertTrue(DoubleUtil.almostEquals(yconv.transpose().multiply(zconv).quadSum(), 0.0, 5.0));
	}
}
