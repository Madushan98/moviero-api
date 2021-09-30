package com.webproject.api.purchesLayer;

import java.util.List;

public interface StreamPurchesService {

	
	
	StreamPurchesDetailResponse getStreamDetails(String userId);



	StreamPurchesDetailResponse buyMovies(List<String> movies, String userId);

	
	
}
