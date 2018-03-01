package com.example.chris.volleyrequest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtWidth,txtHeight;
    Button btnRequest,btnGray,btnSepia;
    ImageView imageView;
    Spinner spinner;
    private String mImageURLString;
    String width,height,categoria,filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeight=findViewById(R.id.txtHeigth);
        txtWidth=findViewById(R.id.txtWidth);
        btnRequest=findViewById(R.id.btnCargar);
        btnGray=findViewById(R.id.btnGray);
        btnSepia=findViewById(R.id.btnSepia);
        imageView=findViewById(R.id.imageView);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categorias_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                width=txtWidth.getText().toString();
                height=txtHeight.getText().toString();
                mImageURLString="https://placeimg.com/"+width+"/"+height+"/"+categoria+"/"+filter;
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                ImageRequest imageRequest = new ImageRequest(mImageURLString, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                );
                requestQueue.add(imageRequest);
            }
        });

        btnGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter="grayscale";
            }
        });

        btnSepia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter="sepia";
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        categoria=adapterView.getItemAtPosition(i).toString();
        filter=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
