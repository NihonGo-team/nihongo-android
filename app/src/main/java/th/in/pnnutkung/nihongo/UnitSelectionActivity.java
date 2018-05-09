package th.in.pnnutkung.nihongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import th.in.pnnutkung.nihongo.UnitSelectComponents.UnitSelectionRecyclerViewHolder;

public class UnitSelectionActivity extends AppCompatActivity {

    private FirebaseRecyclerAdapter<Lesson, UnitSelectionRecyclerViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_selection);
        initInstance();
    }

    private void initInstance() {
        Button logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(UnitSelectionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("lesson");

        FirebaseRecyclerOptions<Lesson> options = new FirebaseRecyclerOptions.Builder<Lesson>()
                .setQuery(query, Lesson.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Lesson, UnitSelectionRecyclerViewHolder>(options) {

            @NonNull
            @Override
            public UnitSelectionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_selection_view_holder, parent, false);
                int height = parent.getMeasuredHeight() / 4;
                view.setMinimumHeight(height);
                return new UnitSelectionRecyclerViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UnitSelectionRecyclerViewHolder holder, int position, @NonNull final Lesson model) {
                holder.setUnitNumber(model.getUnit());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UnitSelectionActivity.this, VocabularyActivity.class);
                        Bundle words = new Bundle();
                        words.putInt("unitNumber", model.getUnit());
                        words.putParcelableArrayList("words", model.getWord());
                        intent.putExtras(words);
                        startActivity(intent);
                    }
                });
            }
        };

        RecyclerView mRecyclerView = findViewById(R.id.recycler_lesson);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 8, true, 0));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
