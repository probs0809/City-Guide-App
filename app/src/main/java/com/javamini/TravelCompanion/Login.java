package com.javamini.TravelCompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int RC_SIGN_IN = 9001;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner location;
    int exitCounter = 0;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    int locationInt = 0;
    Handler handler;


    ArrayList<String> locationData = new ArrayList<String>();
    private String TAG = "FIREBASE :: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler = new Handler(this.getMainLooper());
        SignInButton signin =(SignInButton) findViewById(R.id.btn_sign_in);

        location = (Spinner)findViewById(R.id.spin);
        location.setOnItemSelectedListener(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();


        mDatabase = FirebaseDatabase.getInstance().getReference("/Location");
        locationData.add("Select Location");

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    locationData.add(ds.getValue().toString());

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        }).start();



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locationData);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        location.setAdapter(aa);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(locationData.get(locationInt).equals("Select Location")){
                    Toast.makeText(getApplicationContext(),"Please Input Location", Toast.LENGTH_LONG).show();
                }else{
                    signIn();
                }
            }
        });

    }





    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            myRef = database.getReference("Users/" + user.getUid() + "/email");
                            myRef.setValue(user.getEmail());
                            myRef = database.getReference("Users/" + user.getUid() + "/name");
                            myRef.setValue(user.getDisplayName());
                            myRef = database.getReference("Users/" + user.getUid() + "/UID");
                            myRef.setValue(user.getUid());
                            myRef = database.getReference("Users/" + user.getUid() + "/photoURL");
                            myRef.setValue(user.getPhotoUrl().toString());
                            myRef = database.getReference("Users/" + user.getUid() + "/location");
                            myRef.setValue(locationData.get(locationInt));
                            startActivity(new Intent(Login.this, MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Error Signing in...", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        locationInt = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
