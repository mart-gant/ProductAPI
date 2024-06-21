package com.example.productapi.integration;

import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    public void setUp() {
        warehouseRepository.deleteAll();
    }

    @Test
    public void testCreateWarehouse() throws Exception {

        mockMvc.perform(post("/warehouses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Warehouse 1",
                                    "address": "1234 Warehouse St"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Warehouse 1"))
                .andExpect(jsonPath("$.address").value("1234 Warehouse St"));
    }

    @Test
    public void testGetAllWarehouses() throws Exception {
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("Warehouse 1");
        warehouse1.setAddress("Address 1");
        warehouseRepository.save(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setName("Warehouse 2");
        warehouse2.setAddress("Address 2");
        warehouseRepository.save(warehouse2);

        mockMvc.perform(get("/warehouses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Warehouse 1"))
                .andExpect(jsonPath("$[1].name").value("Warehouse 2"));
    }

    @Test
    public void testGetWarehouseById() throws Exception {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setAddress("Address 1");
        warehouse = warehouseRepository.save(warehouse);

        mockMvc.perform(get("/warehouses/{id}", warehouse.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Warehouse 1"))
                .andExpect(jsonPath("$.address").value("Address 1"));
    }

    @Test
    public void testUpdateWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Old Name");
        warehouse.setAddress("Old Address");
        warehouse = warehouseRepository.save(warehouse);

        mockMvc.perform(put("/warehouses/{id}", warehouse.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "New Name",
                                    "address": "New Address"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"))
                .andExpect(jsonPath("$.address").value("New Address"));
    }

    @Test
    public void testDeleteWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setAddress("Address 1");
        warehouse = warehouseRepository.save(warehouse);

        mockMvc.perform(delete("/warehouses/{id}", warehouse.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
