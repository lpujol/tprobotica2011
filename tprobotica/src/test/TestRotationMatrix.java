package test;

import com.semrobots.math.MatrixConstants;
import com.semrobots.tp1.rotmat.BiAxisRotationMatrix;
import com.semrobots.tp1.rotmat.RotationMatrix;

import junit.framework.TestCase;

public class TestRotationMatrix extends TestCase {
	public void testInverse(){
		RotationMatrix r = RotationMatrix.rotateX(1.108);
		RotationMatrix ri = r.invert();
		assertTrue(r.multiply(ri).almostEqual(MatrixConstants.ID_3_3));
	}
	
	public void testDerivedMatrix() {
		double a1 = 1.89;
		double a2 = 2.4;
		assertTrue(BiAxisRotationMatrix.rotateZX(a1, a2).almostEqual(RotationMatrix.rotateX(a1).multiply(RotationMatrix.rotateZ(a2))));
		assertTrue(BiAxisRotationMatrix.rotateZY(a1, a2).almostEqual(RotationMatrix.rotateY(a1).multiply(RotationMatrix.rotateZ(a2))));
		assertTrue(BiAxisRotationMatrix.rotateYX(a1, a2).almostEqual(RotationMatrix.rotateX(a1).multiply(RotationMatrix.rotateY(a2))));
		assertTrue(BiAxisRotationMatrix.rotateYZ(a1, a2).almostEqual(RotationMatrix.rotateZ(a1).multiply(RotationMatrix.rotateY(a2))));
		assertTrue(BiAxisRotationMatrix.rotateXY(a1, a2).almostEqual(RotationMatrix.rotateY(a1).multiply(RotationMatrix.rotateX(a2))));
		assertTrue(BiAxisRotationMatrix.rotateXZ(a1, a2).almostEqual(RotationMatrix.rotateZ(a1).multiply(RotationMatrix.rotateX(a2))));
	}
}
