package com.example.book.controller;

import com.example.book.model.ItemDto;
import com.example.book.model.OrderDto;
import com.example.book.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

//    @PostMapping("sale")
//    public ResponseEntity<String> sale(@RequestBody ItemDto itemDto) {
//        String orderCode = saleService.saleBook(itemDto.getVendorCode(), itemDto.getCount(), "Yurii");
//        return ResponseEntity.ok(orderCode);
//    }

    @PostMapping("sale")
    public ResponseEntity<String> sale(@RequestBody List<ItemDto> itemsDto) {
        String orderCode = saleService.saleBooks(itemsDto, "Yurii");
        return ResponseEntity.ok(orderCode);
    }

    @PostMapping( "refund")
    public ResponseEntity<String> refund(@RequestBody OrderDto orderDto) {
        saleService.refundBook(orderDto.getOrderCode(), "Yurii");
        return ResponseEntity.ok().build();
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<String> handleIllegalArgumentException(
//            IllegalArgumentException exception
//    ) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(exception.getMessage());
//    }

}
