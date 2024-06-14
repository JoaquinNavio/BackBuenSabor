package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.domain.dto.MercadoPago.PedidoMP;
import com.entidades.buenSabor.domain.entities.PreferenceMP;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
public class MercadoPagoController {
    
    public PreferenceMP getPreferenciaIdMercadoPago(PedidoMP pedido){
        try {                                  //el mio: TEST-4239642567746009-052104-984e6def2bdc215684a3e0bd685eed1e-316937308
            MercadoPagoConfig.setAccessToken("TEST-2833143759781786-053111-1293e7f177f5bff2fea5f98ab749fce3-227221994");
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(pedido.getId()))  // Conversión de long a String
               .title(pedido.getTitulo() + pedido.getItems())
               .description("Pedido realizado desde el carrito de compras: " + pedido.getItems())
               .pictureUrl("https://img-global.cpcdn.com/recipes/0709fbb52d87d2d7/1200x630cq70/photo.jpg")
               .quantity(1)
               .currencyId("ARG")
               .unitPrice(new BigDecimal(pedido.getMontoTotal()))
               .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/success")
                    .pending("http://localhost:5173").failure("http://localhost:5173").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);
            
            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;
                
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
