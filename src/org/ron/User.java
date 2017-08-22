package org.ron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class User {

	ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	HashMap<Character, ArrayList<Movie>>movieMap =  new HashMap<Character, ArrayList<Movie>>();
	
	private void groupMoviesAccToFirstLetter(){
		
		Collections.sort(movieList, Movie.movieNameComparator);
		ArrayList<Movie> list = null;
		Character firstChar = movieList.get(0).getName().charAt(0);
		for(Movie myMovie: movieList){
			Character movieFirstLetter = myMovie.getName().trim().charAt(0);
			if (!movieMap.containsKey(movieFirstLetter)) {
				if(null != list && !list.isEmpty()){
					movieMap.put(firstChar, list);
					firstChar = movieFirstLetter;
				} else{
					list = null;
				}
				list = new ArrayList<Movie>();
				list.add(myMovie);
			} else {
				list.add(myMovie);
			}
			
		}
		
		System.out.println("\nMovies grouped by first letter");
		
		Iterator it = movieMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        ArrayList<Movie> iteratingMovie = (ArrayList<Movie>) pair.getValue();
	        String names = "";
	        for(Movie eachMovie: iteratingMovie){
	        	names= names+eachMovie.getName()+",";
	        }
	        names = names.substring(0, names.length()-1);
	        System.out.println(pair.getKey() + " : " +names);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		
	}
	public void parseMovie(String[] splittedItems) {

		Movie movie = new Movie();
		if (null != splittedItems[0] && !splittedItems[0].isEmpty()) {
			try{
				movie.setYear(Integer.parseInt(splittedItems[0]));
			}catch(Exception exception){
				return;
			}
			
		}
		
		if (null != splittedItems[1] && !splittedItems[1].isEmpty()) {
			movie.setName(splittedItems[1]);
		}
		
		if (null != splittedItems[2] && !splittedItems[2].isEmpty()) {
			try{
				movie.setTotalCollection(Long.parseLong(splittedItems[2]));
			}catch(Exception exception){
				return;
			}
			
		}
		movieList.add(movie);
	}

	private void readLine(String line) {

		String[] splittedItems = line.split(",");
		parseMovie(splittedItems);
		
	}

	public boolean readFile(String fileName) {
		if (null == fileName || fileName.isEmpty()) {
			return false;
		} else {

			String filePath = "/Users/roncherian/Downloads/" + fileName;
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				while ((lineContent = bufferedReader.readLine()) != null) {
					//System.out.println("Found the line: " + lineContent);
					readLine(lineContent);
				}

			}

			catch (Exception exception) {

			}
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			printMoviesInDescOrderOfCollection();
			groupMoviesAccToFirstLetter();
			return true;
	}
	
	private void printMoviesInDescOrderOfCollection(){
		System.out.println("Movie Names in Descending order of total Collection");
		Collections.sort(movieList, Movie.movieCollectionComparator);
		for(Movie movie: movieList){
			System.out.println(movie.name);
		}
	}
}
