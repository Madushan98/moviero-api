package com.webproject.api.purches;

import java.util.List;

public interface StreamPurchesService {
	
	StreamPurchesDetailResponse getStreamDetails(String userId);

	StreamPurchesDetailResponse buyMovies(List<String> movies, String userId);

}
