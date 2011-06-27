package test;

import java.util.ArrayList;
import java.util.List;

import com.semrobots.math.Matrix;
import com.semrobots.rotmat.GenericRotationMatrix;
import com.semrobots.tp1.rotmat.BiAxisRotationMatrix;
import com.semrobots.tp1.rotmat.RotationMatrix;
import com.semrobots.tp2.CorrectorDePosicionResolver;
import com.semrobots.tp2.ParametrosDeCamaraYPuntos;
import com.semrobots.tp2.PerspectivaResolver;

import junit.framework.TestCase;

public class ResolverTest extends TestCase {

	
	public void test1() {
		List<Matrix> puntos = new ArrayList<Matrix>();
		puntos.add(new Matrix(new double[][]{{12.0, 15.0, 8.0}}));
		puntos.add(new Matrix(new double[][]{{-6.0, 9.0, 10.0}}));
		puntos.add(new Matrix(new double[][]{{-16.0, -4.0, 7.0}}));
		puntos.add(new Matrix(new double[][]{{80.0, 40.0, 100.0}}));
		puntos.add(new Matrix(new double[][]{{-110.0, -140.0, 50.0}}));
		puntos.add(new Matrix(new double[][]{{312.0, 15.0, 8.0}}));
		puntos.add(new Matrix(new double[][]{{-63.0, 92.0, 110.0}}));
		puntos.add(new Matrix(new double[][]{{-163.0, -24.0, 77.0}}));
		puntos.add(new Matrix(new double[][]{{8.0, 440.0, 140.0}}));
		puntos.add(new Matrix(new double[][]{{-10.0, -137.0, 51.0}}));
		
		testGenericSolucionDesplazadaRotadaArbitraria(new Matrix(new double[][]{{-5,-7,-9}}), new Matrix(new double[][]{{2,-2,2}}),
				BiAxisRotationMatrix.rotateYX(4.0 / 180.0 * Math.PI, 6.0 / 180.0 * Math.PI), 
				BiAxisRotationMatrix.rotateXZ(1.1 / 180.0 * Math.PI, 1.0 / 180.0 * Math.PI)
				, puntos );
	}
	
	public void test2() {
		List<Matrix> puntos = new ArrayList<Matrix>();
		puntos.add(new Matrix(new double[][]{{12.0, 15.0, 8.0}}));
		puntos.add(new Matrix(new double[][]{{-6.0, 9.0, 10.0}}));
		puntos.add(new Matrix(new double[][]{{-16.0, -4.0, 7.0}}));
		puntos.add(new Matrix(new double[][]{{80.0, 40.0, 100.0}}));
		puntos.add(new Matrix(new double[][]{{-110.0, -140.0, 50.0}}));
		puntos.add(new Matrix(new double[][]{{312.0, 15.0, 8.0}}));
		puntos.add(new Matrix(new double[][]{{-63.0, 92.0, 110.0}}));
		puntos.add(new Matrix(new double[][]{{-163.0, -24.0, 77.0}}));
		puntos.add(new Matrix(new double[][]{{8.0, 440.0, 140.0}}));
		puntos.add(new Matrix(new double[][]{{-10.0, -137.0, 51.0}}));
		
		testGenericSolucionDesplazadaRotadaArbitraria(new Matrix(new double[][]{{-150,-70,-90}}), new Matrix(new double[][]{{21,-12,26}}),
				BiAxisRotationMatrix.rotateYX(164.0 / 180.0 * Math.PI, 6.0 / 180.0 * Math.PI), 
				BiAxisRotationMatrix.rotateXZ(1.1 / 180.0 * Math.PI, 1.0 / 180.0 * Math.PI)
				, puntos );
	}
	
	public void testGenericSolucionDesplazadaRotadaArbitraria(Matrix S, Matrix errS, Matrix R, Matrix errR, List<Matrix> puntos) {
		
		ParametrosDeCamaraYPuntos params = new ParametrosDeCamaraYPuntos();
		params.setP(1.0);
		
		Matrix R0 = errR.multiply(R);
		params.setR(R0);
		
		//desplazamiento
		Matrix S0 = S.add(errS);
		params.setS(S0);
		
		params.getPuntos().addAll(puntos);
		
		calcularMediciones(S, R, params);
		
		realizarTestDeConvergenciaSinChequeo(params);
		System.out.println("S real: " + S.transpose());
		System.out.println("R real: " + R);
	}

	
	public void testSolucionDesplazadaRotadaArbitraria() {
		
		ParametrosDeCamaraYPuntos params = new ParametrosDeCamaraYPuntos();
		params.setP(1.0);
		
		//rotacion
		Matrix R = RotationMatrix.rotateZ(19.487/180.0*Math.PI).multiply(
				RotationMatrix.rotateX(1.541/180.0*Math.PI).multiply(
						RotationMatrix.rotateY(0.739/180.0*Math.PI)
				)
		);
		Matrix errR = RotationMatrix.rotateY(1/180.0*Math.PI);
		Matrix R0 = errR.multiply(R);
		params.setR(R0);
		
		//desplazamiento
		Matrix S0 = new Matrix(new double[][]{{14.5,15.2,15.6}});
		Matrix S = new Matrix(new double[][]{{13.0,12.0,13.8}});
		params.setS(S0);
		
		params.getPuntos().add(new Matrix(new double[][]{{12.0, 15.0, 8.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-6.0, 9.0, 10.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-16.0, -4.0, 7.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{80.0, 40.0, 100.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-110.0, -140.0, 50.0}}));
		
		realizarTestDeConvergencia(S, R, params);
		
		System.out.println(params.getS().transpose() + " vs " + S.transpose().toString());
	}
	
	public void testSolucionDesplazadaRotadaArbitraria2() {
		
		ParametrosDeCamaraYPuntos params = new ParametrosDeCamaraYPuntos();
		params.setP(1.0);
		
		//rotacion
		Matrix R = RotationMatrix.rotateZ(28.487/180.0*Math.PI).multiply(
				RotationMatrix.rotateX(16.541/180.0*Math.PI).multiply(
						RotationMatrix.rotateY(7.739/180.0*Math.PI)
				)
		);
		Matrix errR = RotationMatrix.rotateY(10/180.0*Math.PI);
		Matrix R0 = errR.multiply(R);
		params.setR(R0);
		
		//desplazamiento
		Matrix S0 = new Matrix(new double[][]{{14.5,15.2,15.6}});
		Matrix S = new Matrix(new double[][]{{13.0,12.0,13.8}});
		params.setS(S0);
		
		params.getPuntos().add(new Matrix(new double[][]{{12.0, 15.0, 8.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-6.0, 9.0, 10.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-16.0, -4.0, 7.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{80.0, 40.0, 100.0}}));
		params.getPuntos().add(new Matrix(new double[][]{{-110.0, -140.0, 50.0}}));
		
		realizarTestDeConvergencia(S, R, params);
		
		System.out.println(params.getS().transpose() + " vs " + S.transpose().toString());
	}
	
	
	public void realizarTestDeConvergencia(Matrix realS, Matrix realR, ParametrosDeCamaraYPuntos params) {
		System.out.println(Math.sqrt(realS.substract(params.getS()).quadSum()));
		int i = 0;
		calcularMediciones(realS, realR, params);
		while (!(realS.almostEqual(params.getS()) && realR.almostEqual(params.getR(), 1E2))){
			
			limpiarParamsTemporales(params);
			
			i++;
			if (i == 10000){
				TestCase.fail("Muchas iteraciones.");
			}
			
			PerspectivaResolver presolver = new PerspectivaResolver();
			List<Matrix> ptspersp = presolver.calcularPerspectiva(params);
			
			params.getPuntosVista().addAll(ptspersp);
			
			CorrectorDePosicionResolver resolver = new CorrectorDePosicionResolver();
			Matrix resultado = resolver.resolverProblema(params);
			double alfa = resultado.get(0, 0);
			double beta = resultado.get(0, 1);
			double gama = resultado.get(0, 2);
			Matrix axis = new Matrix(new double[][]{{alfa, beta, gama}});
			double ang = Math.sqrt(axis.quadSum());
			axis = axis.scale(1/ang);
			params.setR(GenericRotationMatrix.rotate(axis, -ang).multiply(params.getR()));
			params.setS(params.getS().add(new Matrix(new double[][]{{resultado.get(0, 3), resultado.get(0, 4), resultado.get(0, 5)}})));
			System.out.println(Math.sqrt(realS.substract(params.getS()).quadSum()));
		}
	}

	private void limpiarParamsTemporales(ParametrosDeCamaraYPuntos params) {
		params.getPuntosVista().clear();
	}


	private Matrix discardLambda(Matrix next) {
		return new Matrix(new double[][]{{next.get(0, 0), next.get(0, 1)}});
	}
	
	private void calcularMediciones(Matrix realS, Matrix realR,
			ParametrosDeCamaraYPuntos params) {
		for (Matrix p : params.getPuntos()){
			Matrix Msreal = realR.transpose().multiply(p.substract(realS));
			Matrix medicion = Msreal.scale(-params.getP()/Msreal.get(0, 2));
			params.getMediciones().add(discardLambda(medicion));
		}
	}

	
	public void realizarTestDeConvergenciaSinChequeo(ParametrosDeCamaraYPuntos params) {
		int i = 0;
		Matrix prevS = null;
		Matrix prevR = null;
		do{
			prevS = params.getS();
			prevR = params.getR();
			limpiarParamsTemporales(params);
			
			i++;
			if (i == 10000){
				TestCase.fail("Muchas iteraciones.");
			}
			
			PerspectivaResolver presolver = new PerspectivaResolver();
			List<Matrix> ptspersp = presolver.calcularPerspectiva(params);
			
			params.getPuntosVista().addAll(ptspersp);
			
			CorrectorDePosicionResolver resolver = new CorrectorDePosicionResolver();
			Matrix resultado = resolver.resolverProblema(params);
			double alfa = resultado.get(0, 0);
			double beta = resultado.get(0, 1);
			double gama = resultado.get(0, 2);
			Matrix axis = new Matrix(new double[][]{{alfa, beta, gama}});
			double ang = Math.sqrt(axis.quadSum());
			axis = axis.scale(1/ang);
			params.setR(GenericRotationMatrix.rotate(axis, -ang).multiply(params.getR()));
			params.setS(params.getS().add(new Matrix(new double[][]{{resultado.get(0, 3), resultado.get(0, 4), resultado.get(0, 5)}})));
			System.out.println(Math.sqrt(prevS.substract(params.getS()).quadSum()));
		} while (!(prevS.almostEqual(params.getS()) && prevR.almostEqual(params.getR())));
		
		System.out.println("S conv: " + prevS.transpose());
		System.out.println("R conv: " + prevR);
	}

}
