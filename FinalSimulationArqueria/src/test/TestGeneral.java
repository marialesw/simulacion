package test;

import logic.ManagerInitPlay;
import logic.MethodsRandom;

public class TestGeneral {

	public static void main(String[] args) {
		ManagerInitPlay initPlay = new ManagerInitPlay();
		MethodsRandom methodsRandom = new MethodsRandom();
		methodsRandom.RandomUniform(20, 6, 1);
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " " + methodsRandom.getListRandomUniform().get(i));
		}
	}
}
