package org.ron;

import java.util.Comparator;

public class Movie {

	int year;
	String name;
	long totalCollection;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(long totalCollection) {
		this.totalCollection = totalCollection;
	}
	
	 public static Comparator<Movie> movieCollectionComparator = new Comparator<Movie>() {
		 
		 public int compare(Movie m1, Movie m2){
			 
			 Long total1 = m1.getTotalCollection();
			 Long total2 = m2.getTotalCollection();
			 return total2.compareTo(total1);
		 }
	};
	
	public static Comparator<Movie> movieNameComparator = new Comparator<Movie>() {
		 
		 public int compare(Movie m1, Movie m2){
			 
			 String name1 = m1.getName();
			 String name2 = m2.getName();
			 return name1.compareTo(name2);
		 }
	};
	
	@Override
    public String toString() {
        return "[ year=" + this.year + ", name=" + this.name + ", totalCollection=" + this.totalCollection + "]";
    }
	
}
