package com.webproject.api.analysis;

public class AnalysisDetailResponse {

	private int totalMovies ;
	
	private double totalRevenue;
	
	private int totalDownloads;

	private int totalUsers ;
	
	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalMovies() {
		return totalMovies;
	}

	public void setTotalMovies(int totalMovies) {
		this.totalMovies = totalMovies;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue2) {
		this.totalRevenue = totalRevenue2;
	}

	public int getTotalDownloads() {
		return totalDownloads;
	}

	public void setTotalDownloads(int totalDownloads) {
		this.totalDownloads = totalDownloads;
	}

}
