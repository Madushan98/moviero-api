package com.webproject.api.analysisLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.api.entity.Movie;
import com.webproject.api.entity.StreamPurcheses;
import com.webproject.api.repository.MovieRepository;
import com.webproject.api.repository.StreamPurchesRepository;
import com.webproject.api.repository.UserRepository;



@Service
public class AnalysisServiceImplementation implements AnalysisService {

	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	MovieRepository movieRepository ;
	
	@Autowired
	StreamPurchesRepository streamRepository;
	
	@Override
	public AnalysisDetailResponse getData() {
		
		int totalMovies = (int) movieRepository.count();
		
		int totalUsers = (int) userRepository.count() ;
		
		List<Movie> allMovies = movieRepository.findAll();
		
		int totalDownloads = 0;
		
		double totalRevenue = 0 ;
		
		
		for (Movie movie : allMovies) {
		
			totalDownloads += movie.getDownloads() ;
			
			totalRevenue += (movie.getDownloads()*movie.getMoviePrice()) ;
			
		}
		
		
		
		AnalysisDetailResponse analyst = new AnalysisDetailResponse() ;
		
		analyst.setTotalDownloads(totalDownloads);
		analyst.setTotalMovies(totalMovies);
		analyst.setTotalUsers(totalUsers);
		analyst.setTotalRevenue(totalRevenue);
			
			
			
			
			
	
		
		return analyst;
	}

	
	
	
}
