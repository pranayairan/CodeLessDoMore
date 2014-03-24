package com.dexterapps.codelessdomore;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dexterapps.codelessdomore.adapter.BoxOfficeMovieAdapter;
import com.dexterapps.codelessdomore.model.BoxOfficeMovies;

public class MainActivity extends BaseActivity implements OnRefreshListener {

	private PullToRefreshLayout mPullToRefreshLayout;

	static final String BoxOfficeURL = "http://api.rottentomatoes.com";
	ListView movieList;
	
	interface BoxOfficeRunningMovie {
		 @GET("/api/public/v1.0/lists/movies/box_office.json")
		 void moviesList(@Query("limit") String limit,@Query("country") String country,@Query("apikey") String apikey, Callback<BoxOfficeMovies> cb);
		 }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.common_root_layout);
		
		FrameLayout mainLayoutContainer = (FrameLayout)findViewById(R.id.content_frame);
		LayoutInflater addLayout = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mainLayoutContainer.addView(addLayout.inflate(R.layout.activity_main,null));
		
		getSupportActionBar().setTitle("Box Office");
		populateNavigationDrawer("Box Office");
		
		movieList = (ListView)findViewById(R.id.movieList);
		
		getMovieRatings();
		
		movieList.addFooterView(new View(this), null, false);
		movieList.addHeaderView(new View(this), null, false);
		
		// Now find the PullToRefreshLayout to setup
	    mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.ptr_layout);

	    // Now setup the PullToRefreshLayout
	    ActionBarPullToRefresh.from(this)
	            // Mark All Children as pullable
	            .allChildrenArePullable()
	            // Set the OnRefreshListener
	            .listener(this)
	            // Finally commit the setup to our PullToRefreshLayout
	            .setup(mPullToRefreshLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onRefreshStarted(View view) {
		
		// logic to refresh here
		getMovieRatings();
		
	}

	private void getMovieRatings()
	{
				// Create a very simple REST adapter which points the RettenTomato API endpoint.
				RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(BoxOfficeURL).build();
				
				// Create an instance of our BoxOffice API interface.
				BoxOfficeRunningMovie runningMovies = restAdapter.create(BoxOfficeRunningMovie.class);

				Callback<BoxOfficeMovies> callback = new Callback<BoxOfficeMovies>() {
				    @Override
				    public void success(BoxOfficeMovies movies, Response response) {
				    	
				    	BoxOfficeMovieAdapter movieAdapter = new BoxOfficeMovieAdapter(MainActivity.this, movies.getMovies());
				    	movieList.setAdapter(movieAdapter);

				    	// Notify PullToRefreshLayout that the refresh has finished
	                    mPullToRefreshLayout.setRefreshComplete();

				    }

				    @Override
				    public void failure(RetrofitError retrofitError) {

				    }
				};
				
				runningMovies.moviesList("10","us","9m4nsunc5kasqv9cfd99vnwz",callback);
	}
}
