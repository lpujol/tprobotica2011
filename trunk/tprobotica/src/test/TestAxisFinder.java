package test;

import junit.framework.TestCase;

import com.semrobots.math.Matrix;
import com.semrobots.math.MatrixConstants;
import com.semrobots.math.util.DoubleUtil;
import com.semrobots.rotmat.GenericRotationMatrix;
import com.semrobots.tp1.rotmat.BiAxisRotationMatrix;
import com.semrobots.tp1.rotmat.RotationMatrix;
import com.semrobots.tp3.AxisFinder;

public class TestAxisFinder extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAxis() {
		//el versor esta escalado por -1 debido a que las matrices de rotacion son de
		//sistemas de coordenadas, y no de rotacion de vectores/matrices lo que hace AxisFinder.
		Matrix rX = RotationMatrix.rotateX(0.2);
		rX = rX.transpose();	//transpongo porque estamos haciendo rotacion de vectores, no de coordenadas
		assertEquals(MatrixConstants.X_VERSOR, AxisFinder.calculateRotationAxis(rX));
		assertEquals(0.2, AxisFinder.getAnguloDeRotacion(rX));	//el angulo siempre es positivo

		Matrix rY = RotationMatrix.rotateY(0.4635);
		rY = rY.transpose();	//transpongo porque estamos haciendo rotacion de vectores, no de coordenadas
		assertEquals(MatrixConstants.Y_VERSOR, AxisFinder.calculateRotationAxis(rY));
		assertTrue(DoubleUtil.almostEquals(0.4635, AxisFinder.getAnguloDeRotacion(rY)));	//el angulo siempre es positivo
		
		Matrix rZ = RotationMatrix.rotateZ(0.53826);
		rZ = rZ.transpose();	//transpongo porque estamos haciendo rotacion de vectores, no de coordenadas
		assertEquals(MatrixConstants.Z_VERSOR, AxisFinder.calculateRotationAxis(rZ));
		assertTrue(DoubleUtil.almostEquals(0.53826, AxisFinder.getAnguloDeRotacion(rZ)));	//el angulo siempre es positivo
		
		Matrix rZInv = RotationMatrix.rotateZ(-0.53826);
		assertEquals(MatrixConstants.Z_VERSOR, AxisFinder.calculateRotationAxis(rZInv));
		assertTrue(DoubleUtil.almostEquals(0.53826, AxisFinder.getAnguloDeRotacion(rZInv)));	//el angulo siempre es positivo
	}
	
	
	public void testMatrixBiAxis() {
		BiAxisRotationMatrix bixy = BiAxisRotationMatrix.rotateZX(Math.PI/2, Math.PI/2);	//90° Z, luego 90° X = 90° (1,1,1)
		Matrix xyaxis = AxisFinder.calculateRotationAxis(bixy);
		double ang = AxisFinder.getAnguloDeRotacion(bixy);

		double v = -1.0/Math.sqrt(3);
		xyaxis.almostEqual(new Matrix(new double[][]{{v,v,v}}));
		assertTrue(DoubleUtil.almostEquals(ang, 120.0/180.0*Math.PI));

		GenericRotationMatrix g = GenericRotationMatrix.rotate(xyaxis, ang);
		assertTrue(bixy.almostEqual(g));
	}
	
	public void testRandom() {
		testRandomParametrizado(-28.0, 34.0, -12.0, 1.3632);
		testRandomParametrizado(-2.0, 4.2, 7.28, -2.8764);
		testRandomParametrizado(2.35433, -3.530, -12.0, 0.28287);
		testRandomParametrizado(-12.3533, -3.530, -12.0, 0.28287);
	}

	private void testRandomParametrizado(double d, double e, double f,
			double ang) {
		Matrix axis = new Matrix(new double[][]{{d, e, f}});
		axis = axis.scale(1.0/Math.sqrt(axis.quadSum()));	//versor
		
		GenericRotationMatrix g = GenericRotationMatrix.rotate(axis, ang);
		Matrix axisCalc = AxisFinder.calculateRotationAxis(g);
		double angCalc = AxisFinder.getAnguloDeRotacion(g);
		assertTrue(axisCalc.almostEqual(axis) || axisCalc.almostEqual(axis.scale(-1.0)));
		assertTrue(DoubleUtil.almostEquals(Math.abs(angCalc), Math.abs(ang), 50.0));	//mucho calculo, asi que le doy un margen mayor de holgura
	}
}
