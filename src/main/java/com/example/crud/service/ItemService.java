package com.example.crud.service;

import com.example.crud.model.Item;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {
    private final Map<Long, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void loadDummyData() {
        create(new Item(null, "Laptop", "14-inch developer laptop"));
        create(new Item(null, "Keyboard", "Mechanical keyboard"));
        create(new Item(null, "Mouse", "Wireless mouse"));
        create(new Item(null, "Monitor", "27-inch 4K monitor"));
    }

    public List<Item> findAll() {
        return List.copyOf(items.values());
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    public Item create(Item item) {
        long id = idCounter.incrementAndGet();
        item.setId(id);
        items.put(id, item);
        return item;
    }

    public Optional<Item> update(Long id, Item updated) {
        if (!items.containsKey(id)) {
            return Optional.empty();
        }
        updated.setId(id);
        items.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        return items.remove(id) != null;
    }
}
