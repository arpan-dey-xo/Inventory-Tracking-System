
package com.example.inventorysystem.service;

import com.example.inventorysystem.dto.ItemDTO;
import com.example.inventorysystem.entity.Item;
import com.example.inventorysystem.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDTO addItem(ItemDTO dto) {
        Item item = new Item(
                dto.name(),
                dto.quantity(),
                dto.price()
        );

        return convertToDTO(itemRepository.save(item));
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ItemDTO getItemById(Long id) {
        return convertToDTO(itemRepository.findById(id).get());
    }

    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item item = itemRepository.findById(id).get();

        item.setName(dto.name());
        item.setQuantity(dto.quantity());
        item.setPrice(dto.price());

        return convertToDTO(itemRepository.save(item));
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
