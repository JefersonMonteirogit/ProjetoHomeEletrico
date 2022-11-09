package com.eletric.projetohomeeletrico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class TelaPrincipal extends AppCompatActivity {


    private double consumo;
    private double totalpotencia;
    private double valorconsumo;
    private double precoconsumo = 0.649;
    private double mesconsumo;
    private double mesvaloconsumo;
    private TextView edit_real1, edit_cons1, edit_real2, edit_cons2;

    private TextView dReal;
    public TextView potencia_t;
    private Button bt_deslogar;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();

        IniciarComponentes();


        DatabaseReference potencia = referencia.child("potencia");





        potencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("FIREBASE",snapshot.getValue().toString());

                consumo = (double) snapshot.getValue();
                //Calculo
                totalpotencia = consumo + totalpotencia;
                valorconsumo = (totalpotencia * precoconsumo)/30;
              
                mesconsumo = totalpotencia * 30;
                mesvaloconsumo = precoconsumo * 30;

                potencia_t.setText(String.valueOf(consumo));
                edit_cons1.setText(String.valueOf(totalpotencia));
                edit_real1.setText(String.valueOf(valorconsumo));
                edit_cons2.setText(String.valueOf(mesconsumo));
                edit_real2.setText(String.valueOf(mesvaloconsumo));

                //Setar nos TextView

                //n1 = Integer.parseInt(String.valueOf(potencia_t.getText()));
               //pp.setText(String.valueOf(n1));
              //  float pot = Integer.parseInt(String.valueOf(snapshot.getValue().toString()));
              //  float res = pot*2;
               // String sres = String.valueOf(res);
               // cReal.setText(sres);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("FIREBASE","erro no banco" + error);

            }
        });

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPrincipal.this,FormLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void IniciarComponentes(){

       // Trasformar();
        edit_cons1 = findViewById(R.id.edit_cons1);
        edit_real2 = findViewById(R.id.edit_real2);
        edit_cons2 = findViewById(R.id.edit_cons2);
        edit_real1 = findViewById(R.id.edit_real1);
        potencia_t = findViewById(R.id.edit_pot);
        bt_deslogar = findViewById(R.id.bt_deslogar);

    }

  /*  public void Trasformar(){
        pp.setText(String.valueOf(n1));
    }
    */

}
