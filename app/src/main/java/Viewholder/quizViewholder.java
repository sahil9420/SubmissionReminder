package Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.submissionremainder.R;

import Interface.ItemClicklistener;

public class quizViewholder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    public TextView quizname;
    private ItemClicklistener itemClicklistener ;





    public quizViewholder(View itemView) {
        super(itemView);
        quizname = (TextView) itemView.findViewById(R.id.quiztextview);


        itemView.setOnClickListener(this );

    }

    public void setItemClicklistener(ItemClicklistener itemClicklistener) {
        this.itemClicklistener = itemClicklistener;
    }

    @Override
    public void onClick(View v) {
       itemClicklistener.onClick(v , getAdapterPosition() ,false);
    }

}
