package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
	
	
/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	private String name;
	
	private List<Integer> altitudeRanges=new ArrayList<>();
	private Map<String,Municipality> elencoComuni=new HashMap<>();
	private Map<String,MountainHut> elencoRifugi=new HashMap<>();
	private Map<String,Provincia> elencoProvincie=new HashMap<>();
	private List<String> dati=new ArrayList<>();


	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name=name;

	}
	
	public Region(String name,List<String> dati) {
		this.name=name;
		this.dati=dati;
		for(int i=1;i<dati.size();i++) {
			String[] linea=dati.get(i).split(";");
			Provincia p=CreateSearchProvince(linea[0]);
			Municipality m=createOrGetMunicipality(linea[1], linea[0], Integer.valueOf(linea[2]));
			MountainHut mh;
			if(linea[4].isEmpty()) {
				mh=createOrGetMountainHut(linea[3],linea[5],Integer.valueOf(linea[6]),m);
			}
			else {
				 mh=createOrGetMountainHut(linea[3],Integer.valueOf(linea[4]),linea[5],Integer.valueOf(linea[6]),m);
			}
			
			elencoProvincie.put(linea[0],p);
			elencoComuni.put(linea[1],m);
			elencoRifugi.put(linea[3],mh);
			p.addMunicipality(m, m.getName());
			m.addMountainHut(mh, mh.getName());
			
		}
	}
	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		 for(int i=0;i<ranges.length;i++) {
			 String[] interval=ranges[i].split("-");
			 
			 altitudeRanges.add(Integer.valueOf(interval[0]));
			 altitudeRanges.add(Integer.valueOf(interval[1]));
		 }
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		if(altitudeRanges.isEmpty()) return "0-INF";
		
		if(altitude<altitudeRanges.get(0))return "0-INF";
		//forse devo toglierla
		if(altitude==altitudeRanges.get(0))return altitudeRanges.get(0)+ "-" + altitudeRanges.get(1);
		
		for(int i=0;i<altitudeRanges.size();i+=2) {
			if(altitude>altitudeRanges.get(i) && altitude<=altitudeRanges.get(i+1)) {
				

				return altitudeRanges.get(i)+ "-" + altitudeRanges.get(i+1);
				
			}
		}
		return "0-INF";
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		if(elencoComuni.get(name)==null) {
			Municipality m=new Municipality(name, province, altitude);
			elencoComuni.put(name, m);
		}
		return elencoComuni.get(name);
		
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return elencoComuni.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		
		if(elencoRifugi.get(name)==null) {
			MountainHut m=new MountainHut(name, category, bedsNumber, municipality);
			elencoRifugi.put(name, m);
		}
		
		return elencoRifugi.get(name);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {

		if(elencoRifugi.get(name)==null) {
			MountainHut m=new MountainHut(name, category, bedsNumber, municipality,altitude);
			elencoRifugi.put(name, m);
		}
		
		return elencoRifugi.get(name);
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return elencoRifugi.values();
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		List<String> dati=readData(file);
		
		return new Region(name,dati);
		
		}

	
	public Provincia CreateSearchProvince(String nomeP) {
		if(elencoProvincie.get(nomeP)==null) {
			Provincia p=new Provincia(nomeP, this);
			elencoProvincie.put(nomeP, p);
		}
		return elencoProvincie.get(nomeP);
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		
		return (elencoComuni.values()).stream().collect(Collectors.groupingBy(x->x.getProvince(),
																			Collectors.counting()));
		
		
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		return (elencoRifugi.values()).stream()
				.collect(Collectors.groupingBy(x->x.getMunicipality().getProvince(),
						 Collectors.groupingBy(x->x.getMunicipality().getName(),
								 Collectors.counting())));
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		
	return null;
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return (elencoRifugi.values()).stream().collect(Collectors.groupingBy(x->x.getMunicipality().getProvince(),
																		Collectors.summingInt(MountainHut::getBedsNumber)));
		
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		return null;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		
		/*return (elencoComuni.values()).stream()
				.collect(Collectors.groupingBy(x->x.getElencoRifugi().keySet().size(),
					this.elencoComuni.keySet().stream().collect(Collectors.toList()))) ;*/
		
		
		return (elencoRifugi.values()).stream()
		.collect(Collectors.groupingBy(x -> x.getMunicipality().getName(),
										() -> new TreeMap<String, Long>(Comparator.naturalOrder()),Collectors.counting())).entrySet().stream() 
		.collect(Collectors.groupingBy(x -> x.getValue(),
				Collectors.mapping(x -> x.getKey(), Collectors.toList())));
		
	}

}
