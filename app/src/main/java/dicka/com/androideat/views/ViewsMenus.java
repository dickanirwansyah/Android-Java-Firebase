package dicka.com.androideat.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dicka.com.androideat.R;
import dicka.com.androideat.interfaces.ItemClickListener;

/**
 * Created by java-spring on 02/02/18.
 */

public class ViewsMenus extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textViewNamaMenuFood;
    public ImageView loadViewsImageFood;

    private ItemClickListener itemClickListener;

    public ViewsMenus(View itemView) {
        super(itemView);

        textViewNamaMenuFood = (TextView) itemView.findViewById(R.id.menu_nama);
        loadViewsImageFood = (ImageView) itemView.findViewById(R.id.menu_image);

        //click menu
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
