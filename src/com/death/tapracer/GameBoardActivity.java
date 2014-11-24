package com.death.tapracer;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameBoardActivity extends Activity {
	
	class TileClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			processClick((ImageView) v);
		}
		
	}
	
	ArrayList<ImageView> tiles;
	ImageView currentTile;
	
	int score = 0;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game_board);
		
		tiles = new ArrayList<ImageView>();
		populateGameBoard();
		
		changeTile(tiles.get(new Random().nextInt(9)));
	}

	private void populateGameBoard() {
		LinearLayout row1 = (LinearLayout) findViewById(R.id.grid_row1);
		LinearLayout row2 = (LinearLayout) findViewById(R.id.grid_row2);
		LinearLayout row3 = (LinearLayout) findViewById(R.id.grid_row3);
		for (int i=0; i<3; i++) {
			row1.addView(generateTile());
			row2.addView(generateTile());
			row3.addView(generateTile());
		}
	
	}
	
	private ImageView generateTile() {
		ImageView tile = new ImageButton(getApplicationContext());
		tile.setBackgroundResource(R.drawable.tile_blank);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.setMargins(3, 3, 3, 3);
		params.weight = 1;
		tile.setLayoutParams(params);
		
		tile.setOnClickListener(new TileClickListener());
		
		tiles.add(tile);
		return tile;
	}
	
	private void changeTile(ImageView tile) {
		if (currentTile != null) {
			currentTile.setBackgroundResource(R.drawable.tile_blank);
		}
		tile.setBackgroundResource(R.drawable.tile_current);
		currentTile = tile;
	}
	
	private void processClick(ImageView tile) {
		if (tile == currentTile) {
			score++;
			ImageView newTile = tiles.get(new Random().nextInt(9));
			while (newTile == currentTile) {
				newTile = tiles.get(new Random().nextInt(9));
			}
			changeTile(newTile);
		}
	}
	
}
