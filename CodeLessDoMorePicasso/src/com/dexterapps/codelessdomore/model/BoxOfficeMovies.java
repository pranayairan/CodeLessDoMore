
package com.dexterapps.codelessdomore.model;

import java.util.List;

public class BoxOfficeMovies{
   	private String link_template;
   	private Links links;
   	private List<Movies> movies;

 	public String getLink_template(){
		return this.link_template;
	}
	public void setLink_template(String link_template){
		this.link_template = link_template;
	}
 	public Links getLinks(){
		return this.links;
	}
	public void setLinks(Links links){
		this.links = links;
	}
 	public List<Movies> getMovies(){
		return this.movies;
	}
	public void setMovies(List<Movies> movies){
		this.movies = movies;
	}
}
