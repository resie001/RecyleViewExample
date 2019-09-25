package com.example.recyleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(HeroData.getListData());
        setActionBarTitle(title);
        showRecyleList();

    }

    private void showRecyleList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemCallBack(new ListHeroAdapter.OnItemCallBack() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showGridList(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this,2 ));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setGridHeroAdapter(new GridHeroAdapter.OnItemCallBack() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showCardView(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardHeroAdapter cardHeroAdapter = new CardHeroAdapter(list);
        rvHeroes.setAdapter(cardHeroAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode (int selectedMode){
        switch (selectedMode){
            case R.id.action_list:
                title = "Mode List";
                setActionBarTitle(title);
                showRecyleList();
                break;
            case R.id.action_grid:
                title = "Mode Grid";
                setActionBarTitle(title);
                showGridList();
                break;
            case R.id.action_cardview:
                title = "Mode CardView";
                setActionBarTitle(title);
                showCardView();
                break;
        }
    }

    private void setActionBarTitle(String title){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(Hero hero){
        Toast.makeText(this,"Kamu Memilih "+hero.getName(), Toast.LENGTH_SHORT).show();
    }
}
