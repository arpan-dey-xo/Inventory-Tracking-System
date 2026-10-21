
package com.example.inventorysystem.service;

import com.example.inventorysystem.dto.TransactionDTO;
import com.example.inventorysystem.entity.Item;
import com.example.inventorysystem.entity.Transaction;
import com.example.inventorysystem.repository.ItemRepository;
import com.example.inventorysystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            ItemRepository itemRepository) {
        this.transactionRepository = transactionRepository;
        this.itemRepository = itemRepository;
    }

    public TransactionDTO addTransaction(TransactionDTO dto) {
        Item item = itemRepository.findById(dto.itemId()).get();

        if (dto.type().equalsIgnoreCase("IN")) {
            item.setQuantity(item.getQuantity() + dto.quantity());
        } else if (dto.type().equalsIgnoreCase("OUT")) {
            item.setQuantity(item.getQuantity() - dto.quantity());
        }

        itemRepository.save(item);

        Transaction transaction = new Transaction();
        transaction.setType(dto.type().toUpperCase());
        transaction.setDate(dto.date());
        transaction.setQuantity(dto.quantity());
        transaction.setItemId(dto.itemId());
        transaction.setSupplierId(dto.supplierId());

        return convertToDTO(transactionRepository.save(transaction));
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public TransactionDTO getTransactionById(Long id) {
        return convertToDTO(transactionRepository.findById(id).get());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getQuantity(),
                transaction.getItemId(),
                transaction.getSupplierId()
        );
    }
}
