package com.piyeyus.jago;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.piyeyus.jago.Adapter.CardViewAvatarAdapter;
import com.piyeyus.jago.Adapter.GridAvatarAdapter;
import com.piyeyus.jago.Adapter.ListAvatarAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvAvatar;
    private ArrayList<Avatar> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvAvatar = findViewById(R.id.rv_avatar);
        rvAvatar.setHasFixedSize(true);

        list.addAll(AvatarData.getListData());
        showRecyclerList();
    }
    private void showRecyclerList(){
        rvAvatar.setLayoutManager(new LinearLayoutManager(this));
        ListAvatarAdapter listAvatarAdapter = new ListAvatarAdapter(list);
        rvAvatar.setAdapter(listAvatarAdapter);
        listAvatarAdapter.setOnItemClickCallback(new ListAvatarAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Avatar data) {
                showSelectedAvatar(data);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "List Menu";
                showRecyclerList();
                break;
            case R.id.action_grid:
                title = "Geleri";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                title = "Review Menu";
                showRecyclerCardView();
                break;
        }
        setActionBarTitle(title);
    }
    private void showRecyclerGrid(){
        rvAvatar.setLayoutManager(new GridLayoutManager(this, 2));
        GridAvatarAdapter gridAvatarAdapter = new GridAvatarAdapter(list);
        rvAvatar.setAdapter(gridAvatarAdapter);

        gridAvatarAdapter.setOnItemClickCallback(new GridAvatarAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Avatar data) {
                showSelectedAvatar(data);
            }
        });
    }

    private void showRecyclerCardView(){
        rvAvatar.setLayoutManager(new LinearLayoutManager(this));
        CardViewAvatarAdapter cardViewAvatarAdapter = new CardViewAvatarAdapter(list);
        rvAvatar.setAdapter(cardViewAvatarAdapter);

    }
    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
    private void showSelectedAvatar(Avatar avatar) {
        Toast.makeText(this, "Coba Masak " + avatar.getName(), Toast.LENGTH_SHORT).show();
    }

}