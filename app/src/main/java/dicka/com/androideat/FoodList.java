package dicka.com.androideat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import dicka.com.androideat.interfaces.ItemClickListener;
import dicka.com.androideat.model.Food;
import dicka.com.androideat.views.VIewsFood;

public class FoodList extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    //Firebase init the food
    FirebaseDatabase database;
    DatabaseReference foodList;

    FirebaseRecyclerAdapter<Food, VIewsFood> adapter;

    String CategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //load firebase database
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        //fixing recyler layout
        recyclerView = (RecyclerView) findViewById(R.id.recyler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get instance categoryid
        if(getIntent() != null)
            CategoryId = getIntent().getStringExtra("CategoryId");
        if(!CategoryId.isEmpty() && CategoryId != null){
            //load foods
            loadListFoodByCategoryId(CategoryId);
        }
    }

    //load food berdasarkan kategori food
    private void loadListFoodByCategoryId(String categoryid){
        adapter = new FirebaseRecyclerAdapter<Food, VIewsFood>(Food.class,
                R.layout.food_item,
                VIewsFood.class,
                foodList.orderByChild("categoryid").equalTo(categoryid)
                ) {

            @Override
            protected void populateViewHolder(VIewsFood viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getNama());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

                final Food namaFood = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this, ""+namaFood.getNama(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        //set adapter
        recyclerView.setAdapter(adapter);
    }
}
