package com.example.customdrawer;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;

class chat_Adapter extends RecyclerView.Adapter<chat_Adapter.ViewHolder> {

    Context context;
    String[] imageUrls;

    public chat_Adapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @NonNull
    @Override
    public chat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chat_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_Adapter.ViewHolder holder, int position) {
        holder.textView.setText(imageUrls[position]);
        Glide.with(context).asBitmap().load(imageUrls[position]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.textarea);
        }
    }
}






public class ChatFragment extends Fragment {
    public int getNumberOfColumns() {
        View view = View.inflate(getContext(), R.layout.chat_rv_item, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
        int count = getResources().getDisplayMetrics().widthPixels / width;
        int remaining = getResources().getDisplayMetrics().widthPixels - width * count;
        if (remaining > width - 15)
            count++;
        return count-1;
    }




    String []imageUrls;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        Bundle bundle =getArguments();
        if(bundle!=null)
        {
            imageUrls=bundle.getStringArray("urls");
        }

        RecyclerView recyclerView = v.findViewById(R.id.chatRecyclerView);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(getNumberOfColumns(), LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setAdapter(new chat_Adapter(v.getContext(), imageUrls));

        return v;
    }
}
