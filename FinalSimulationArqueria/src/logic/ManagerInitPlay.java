package logic;

import java.util.ArrayList;
import java.util.Iterator;

import models.Constants;
import models.EnumGender;
import models.Name;
import models.Person;
import models.Team;

/**
 * Administrador para las condiciones inciales de un juego
 *
 * @author Ivan Valderrama
 * @author Maria Latorre
 */
public class ManagerInitPlay {

	private static final int CANTIDAD_NOMBRES = 21;
	private static final int NUMERO_JUGADORES = 15;
	private ArrayList<Team> listTeams;
    private ArrayList<Name> listWomen;
    private ArrayList<Name> listMen;
    private int countW;
    private int countM;

    public ManagerInitPlay() {
        this.countW = 0;
        this.countM = 0;
        listTeams = new ArrayList<>();
        createNames();
    }

    /**
     * Obtener el nombre aleatorio segun el genero que se tenga
     *
     * @param gender
     * @return
     */
    public Name randomName(EnumGender enumGender) {
        int res = 0;
        res = generarNumero();
        if (enumGender.getNameGender().equals("Hombre")) {
        	while (listMen.get(res).isSelected()) {
				res = generarNumero();
			}
        	listMen.get(res).setSelected(true);
            return listMen.get(res);
        } else {
        	while (listWomen.get(res).isSelected()) {
				res = generarNumero();
			}
        	listWomen.get(res).setSelected(true);
            return listWomen.get(res);
        }
    }

	private int generarNumero() {
		int res;
		MethodsRandom method = new MethodsRandom();
        method.RandomUniform(1, CANTIDAD_NOMBRES, 0);
        res = (int) method.getListRandomUniform().get(0).doubleValue();
		return res;
	}

    /**
     * Obtener el genero en forma aleatoria
     *
     * @return
     */
    public EnumGender randomGender() {
        EnumGender enumGender = null;
        MethodsRandom method = new MethodsRandom();
        method.RandomUniform(1, 3, 1);
        int res = (int) method.getListRandomUniform().get(0).doubleValue();
//         System.out.println("metodos uniforme: "+res);
        if (res == 1) {
            enumGender = EnumGender.MALE;
        } else if(res == 2){
            enumGender = EnumGender.FEMALE;
        }

        return enumGender;
    }

    /**
     * Obtener el valor de la resistencia aleatorio entre 45 y 65.
     * @param 
     * @return
     */
    public int randomResistence() {
        int res = 0;
        MethodsRandom method = new MethodsRandom();
            method.RandomUniform(1, 66, 46);
            res = (int) method.getListRandomUniform().get(0).doubleValue();
            return res;
    }

    /**
     * Obtener el valor de la precision aleatorio entre 50 y 54
     *
     * @param gender
     * @return
     */
    public int randomPrecision(EnumGender gender) {
        int res = 0;
        MethodsRandom method = new MethodsRandom();
        if (gender.getNameGender().equals("Hombre")) {
            method.RandomUniform(1, 51, 46);
            res = (int) method.getListRandomUniform().get(0).doubleValue();
            return res;
        } else {
            method.RandomUniform(1, 55, 51);
            res = (int) method.getListRandomUniform().get(0).doubleValue();
            return res;
        }

    }

    /**
     * Obtener el valor de la suerte aleatoria usando todos los decimales para una mayor precision
     *
     * @return
     */
    public float randomLucky() {
        float res = 0;
        MethodsRandom method = new MethodsRandom();
        method.RandomUniform(1, Constants.MAXIMUM_LUCKY, 1);
        res = (float) method.getListRandomUniform().get(0).doubleValue();
        return res;
    }

    /**
     * Metodo para crear los jugadores para un solo equipo con las condiciones
     * necesarias
     *
     * @return retorna un equipo de jugadores
     */
    private ArrayList<Person> createPlayersOneTeam() {
        ArrayList<Person> auxListPlayers = new ArrayList<>();
        for (int i = 0; i < NUMERO_JUGADORES; i++) {
            EnumGender genderSelected = randomGender();
            Person person = new Person(
                    randomName(genderSelected).getName(),
                    genderSelected,
                    randomResistence(), 10,
                    randomLucky());
            auxListPlayers.add(person);
        }
        Person.id = 1;
        return auxListPlayers;
    }

    /**
     * Crear los dos equipos con sus jugadores
     */
    public void createTeams() {
        Team teamA = new Team(1, "Equipo A", createPlayersOneTeam());
        Team teamB = new Team(2, "Equipo B", createPlayersOneTeam());
        teamA.numberGender(); // para saber cuantas mujeres y cuantos hombres hay por equipo
        teamB.numberGender();
        listTeams.add(teamA);
        listTeams.add(teamB);
        System.out.println("ya creamos 2 equipos");
        System.out.println(teamA.getListPlayers().toString());
        System.out.println(teamB.getListPlayers().toString());
    }
//----------------------------Get and set-----------------------------------

    public ArrayList<Team> getListTeams() {
        return listTeams;
    }

    public void setListTeams(ArrayList<Team> listTeams) {
        this.listTeams = listTeams;
    }

    /**
     * Crea una lista con posibles nombres para los jugadores 
     * Tanto hombres como mujeres
     */
    	private void createNames() {
    		listWomen = new ArrayList<>();
            listWomen.add(new Name("Lorwein"));
            listWomen.add(new Name("Lucia"));
            listWomen.add(new Name("Manuela"));
            listWomen.add(new Name("Sara"));
            listWomen.add(new Name("Yenny"));
            listWomen.add(new Name("Liliana"));
            listWomen.add(new Name("Gloria"));
            listWomen.add(new Name("Anyi"));
            listWomen.add(new Name("Lisbeth"));
            listWomen.add(new Name("Dora"));
            listWomen.add(new Name("Andrea"));
            listWomen.add(new Name("Juliana"));
            listWomen.add(new Name("Natalia"));
            listWomen.add(new Name("Rosa E"));
            listWomen.add(new Name("Julieta"));
            listWomen.add(new Name("Carmenza"));
            listWomen.add(new Name("Eliza"));
            listWomen.add(new Name("Yasmin"));
            listWomen.add(new Name("Lorena"));
            listWomen.add(new Name("Carla"));
            listWomen.add(new Name("Gissela"));

            listMen = new ArrayList<>();
            listMen.add(new Name("Carlos"));
            listMen.add(new Name("Mateo"));
            listMen.add(new Name("Luis A"));
            listMen.add(new Name("Marcos"));
            listMen.add(new Name("Jose"));
            listMen.add(new Name("Pedro"));
            listMen.add(new Name("Daniel"));
            listMen.add(new Name("Leonardo"));
            listMen.add(new Name("Alejandro"));
            listMen.add(new Name("Jaime"));
            listMen.add(new Name("Duvan"));
            listMen.add(new Name("Moises"));
            listMen.add(new Name("Yeffer"));
            listMen.add(new Name("Camilo"));
            listMen.add(new Name("Guillermo"));
            listMen.add(new Name("Manuel"));
            listMen.add(new Name("Leonidas"));
            listMen.add(new Name("Pablo"));
            listMen.add(new Name("Jesus"));
            listMen.add(new Name("Lucas"));
            listMen.add(new Name("Antonio"));
    	}
}
