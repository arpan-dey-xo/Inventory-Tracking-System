
package com.example.inventorysystem.repository;

import com.example.inventorysystem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
