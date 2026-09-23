package com.example.crud.controller;

import com.example.crud.model.Item;
import com.example.crud.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.create(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        return itemService.update(id, item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return itemService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
