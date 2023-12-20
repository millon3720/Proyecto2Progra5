package com.uh.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        GetProductos();

    }

    public void GetProductos(){
        String URL="http://www.proyectoprogra5.somee.com/api/ProductosApi/ListaProductos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        String Lista="";
                        final TextView TxtDatos;
                        TxtDatos=findViewById(R.id.TxtDatos);
                        Type listType = new TypeToken<List<Producto>>() {}.getType();
                        List<Producto> productos = gson.fromJson(response.toString(), listType);
                        // Ahora tienes una lista de productos que puedes usar
                        Log.i("Error","Entroooooooooooooooooooooo");
                        for (Producto producto : productos){
                            Lista+=(producto.getNombre() + " " + producto.getDescripcion() +" " + producto.getImagen() +" " + producto.getId() + "\n");
                        }
                        TxtDatos.setText(Lista);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error
                        Log.i("Error",error.getMessage());
                    }
                }
        );

        queue.add(jsonArrayRequest);

    }
    public static String encrypt(String plainText, String key, String iv) throws Exception {
        // Convertir la clave y el vector de inicialización a bytes
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);

        // Crear objetos de clave y vector de inicialización
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Inicializar el cifrador en modo CBC con PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Cifrar la cadena
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Codificar el resultado en Base64 para facilitar la representación y transporte
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

}