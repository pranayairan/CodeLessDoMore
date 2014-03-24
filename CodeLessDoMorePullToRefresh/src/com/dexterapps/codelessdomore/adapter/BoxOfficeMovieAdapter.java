/*
 * Copyright (C) 2014 Pranay Airan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dexterapps.codelessdomore.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexterapps.codelessdomore.R;
import com.dexterapps.codelessdomore.model.Movies;
import com.squareup.picasso.Picasso;

public class BoxOfficeMovieAdapter extends BaseAdapter {

	private List<Movies> movies;
	Context ctx;

	public BoxOfficeMovieAdapter(Context context, List<Movies> runningMovies) {
		ctx = context;
		movies = runningMovies;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movies.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		class MovieHolderItem {
			TextView movieName;
			TextView movieCriticRating;
			TextView movieAudienceRating;
			TextView movieMPAARating;
			ImageView movieThumbnail;
		}

		MovieHolderItem movieHolder;

		if (view == null) {
			LayoutInflater movieRow = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = movieRow.inflate(R.layout.now_running_list_item, null);
			movieHolder = new MovieHolderItem();
			
			movieHolder.movieName = (TextView)view.findViewById(R.id.movieName);
			movieHolder.movieCriticRating = (TextView)view.findViewById(R.id.movieCriticRating);
			movieHolder.movieAudienceRating = (TextView)view.findViewById(R.id.movieAudienceRating);
			movieHolder.movieMPAARating = (TextView)view.findViewById(R.id.mpaa_rating);
			movieHolder.movieThumbnail = (ImageView)view.findViewById(R.id.movieThumbnail);
			view.setTag(movieHolder);
		} else {
			movieHolder = (MovieHolderItem) view.getTag();
		}

		Movies runningMovie = movies.get(position);
		movieHolder.movieName.setText(runningMovie.getTitle());
		movieHolder.movieCriticRating.setText("Critic Rating: "+runningMovie.getRatings().getCritics_score());
		movieHolder.movieAudienceRating.setText("Audience Rating: "+runningMovie.getRatings().getAudience_rating());
		movieHolder.movieMPAARating.setText(runningMovie.getMpaa_rating());
		movieHolder.movieThumbnail.setImageResource(R.drawable.ic_launcher);
		
		Picasso.with(ctx).load(runningMovie.getPosters().getThumbnail())
				.placeholder(R.drawable.ic_launcher)
				.error(R.drawable.ic_launcher).into(movieHolder.movieThumbnail);
		
		return view;
	}
}
