package com.example.OrderProject_MVC.Utils;

import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.repository.ClientRepository;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public DataLoader(ClientRepository clientRepository,  OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public void loadClients() throws IOException {
    if(clientRepository.count()>0) return;
    try(BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(
                    new ClassPathResource("data/clients.txt").getInputStream(), StandardCharsets.UTF_8)))
    {
        String linie;

        while ((linie = bufferedReader.readLine()) != null){
            String[] parts = linie.split(",");
            String name = parts[0];
            String email = parts[1];

            clientRepository.save(new Client(name, email));
            }
        }
    }

    public void loadOrders() throws IOException {
        if(orderRepository.count()>0) return;

        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("data/orders.txt").getInputStream(), StandardCharsets.UTF_8)))
        {
            String linie;

            while ((linie = bufferedReader.readLine()) != null){
                String[] parts = linie.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                Double price = Double.parseDouble(parts[1]);
                OrderStatus orderStatus = OrderStatus.valueOf(parts[2]);
                String email = parts[3];

                Client client = clientRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("The email does not exists" + email));

                orderRepository.save(new Order(date, price, orderStatus, client));
            }
        }

    }


    @Override
    public void run(String... args) throws Exception {
        loadClients();
        loadOrders();
    }
}
