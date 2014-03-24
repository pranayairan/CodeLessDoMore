package com.dexterapps.codelessdomore;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

import com.dexterapps.codelessdomore.model.BoxOfficeMovies;

public class MainActivity extends ActionBarActivity {

	static final String BoxOfficeURL = "http://api.rottentomatoes.com";
	
	interface BoxOfficeRunningMovie {
		 @GET("/api/public/v1.0/lists/movies/box_office.json")
		 void moviesList(@Query("limit") String limit,@Query("country") String country,@Query("apikey") String apikey, Callback<BoxOfficeMovies> cb);
		 }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create a very simple REST adapter which points the RettenTomato API endpoint.
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(BoxOfficeURL).build();
		
		// Create an instance of our BoxOffice API interface.
		BoxOfficeRunningMovie runningMovies = restAdapter.create(BoxOfficeRunningMovie.class);

		Callback<BoxOfficeMovies> callback = new Callback<BoxOfficeMovies>() {
		    @Override
		    public void success(BoxOfficeMovies o, Response response) {

		    	int moviesNumber = o.getMovies().size();
		    	Toast.makeText(MainActivity.this, "Total Movies running on Box offices are: "+moviesNumber, Toast.LENGTH_LONG).show();
		    }

		    @Override
		    public void failure(RetrofitError retrofitError) {

		    }
		};
		
		runningMovies.moviesList("10","us","9m4nsunc5kasqv9cfd99vnwz",callback);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
