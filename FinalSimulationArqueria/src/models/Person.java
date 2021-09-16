package models;

import java.util.ArrayList;

/**
 * Clase Persona 
 *
 * @author Maria Latorre
 */
public class Person implements Comparable<Person> {

	public static int identificadorUnico=1;
	private int identificador;
	public static int id = 1;
	private int idPersona;
	private String name;
	private EnumGender gender;
	private int resistence;
	private int resistenceInit;
	private int experienceInit;
	private int experience;
	private int noLoseResis;
	private boolean isDiscount;
	private float lucky;//suerte
	private ArrayList<Launching> listLaunching;
	private ArrayList<Launching> listLaunchingEspecial;
	private float totalScore;
	private int totalRoundsIndividualWinner;

	public Person(String name, EnumGender gender, int resistence, int experience, float lucky) {
		idPersona = Person.id++;
		identificador=Person.identificadorUnico;
		this.gender = gender;
		this.name = name;
		this.resistence = resistence;
		this.experience = experience;
		this.lucky = lucky;
		this.listLaunching = new ArrayList<>();
		this.listLaunchingEspecial = new ArrayList<>();
		this.totalScore = 0;
		this.resistenceInit = resistence;
		this.experienceInit = experience;
		this.noLoseResis = 0;
		this.isDiscount = true;
		totalRoundsIndividualWinner = 0;
	}

	@Override
	public String toString() {
		return idPersona + " " + name + " " + gender + " " + resistence  + " " + experience + " " + lucky;
	}

	public int getNoLoseResis() {
		return noLoseResis;
	}
	
	public void addNoLoseResis() {
		noLoseResis++;
	}
	
	public String abilities(){
		return "Resistencia: " + resistence + " - Experiencia: " + experience + " - Suerte: " + lucky;
	} 
	public int getResistenceInit() {
		return resistenceInit;
	}

	public void setNoLoseResis(int noLoseResis) {
		this.noLoseResis = noLoseResis;
	}
	
	public int getExperienceInit() {
		return experienceInit;
	}
	public void setExperienceInit(int experienceInit) {
		this.experienceInit = experienceInit;
	}
	/**
	 * Metodo para comparar por suerte, al usarlo ordena el array de mayor a
	 * menor Collection.sort(array);
	 *
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Person o) {
		if (lucky < o.lucky) {
			return -1;
		}
		if (lucky > o.lucky) {
			return 1;
		}
		return 0;
	}

	public int getIdentificador() {
		return identificador;
	}
	public ArrayList<Launching> getListLaunchingEspecial() {
		return listLaunchingEspecial;
	}
	public int getTotalRoundsIndividualWinner() {
		return totalRoundsIndividualWinner;
	}

	public void setTotalRoundsIndividualWinner(int totalRoundsIndividualWinner) {
		this.totalRoundsIndividualWinner = totalRoundsIndividualWinner;
	}

	public ArrayList<Launching> getListLaunching() {
		return listLaunching;
	}
	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public void setListLaunching(ArrayList<Launching> listLaunching) {
		this.listLaunching = listLaunching;
	}

	public EnumGender getGender() {
		return gender;
	}

	public void setGender(EnumGender gender) {
		this.gender = gender;
	}

	public int getId() {
		return idPersona;
	}

	public void setId(int id) {
		this.idPersona = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResistence() {
		return resistence;
	}

	public void setResistence(int resistence) {
		this.resistence = resistence;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		if ((experience - experienceInit) == 9) {
			isDiscount = false;
		}
		this.experience = experience;
	}

	public boolean isDiscount() {
		return isDiscount;
	}
	
	public void setDiscount(boolean isDiscount) {
		experienceInit = experience;
		this.isDiscount = isDiscount;
	}
	
	public float getLucky() {
		return lucky;
	}

	public void setLucky(float lucky) {
		this.lucky = lucky;
	}

}
