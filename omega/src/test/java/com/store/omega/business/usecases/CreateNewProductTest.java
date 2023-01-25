package com.store.omega.business.usecases;

import com.store.omega.business.businessobjects.ProductBO;
import com.store.omega.business.dto.ProductDTO;
import com.store.omega.domain.models.Product;
import com.store.omega.persistence.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class CreateNewProductTest {

    @Mock
    InventoryRepository inventoryRepository;
    CreateNewProduct createNewProduct;

    @BeforeEach
    void init() {
        createNewProduct = new CreateNewProduct(inventoryRepository);
    }


    @Test
    void createNewProductTest() {
        final String id = "456";
        final String name = "name";

        ProductDTO originalDTO = new ProductDTO(id, name, 450, true, 5, 90);

        Product expectedProduct = new Product(new ProductBO(originalDTO));

        Mockito.lenient().when(inventoryRepository
                .createProduct(expectedProduct))
                .thenReturn(Mono.just(expectedProduct));

        Mono<ProductDTO> finalResult = createNewProduct.createNewProduct(Mono.just(originalDTO));


        StepVerifier.create(finalResult)
                .expectNextMatches(productDTO -> productDTO.getName().equals(name))
                        .verifyComplete();

        Mockito.verify(inventoryRepository)
                .createProduct(expectedProduct);
    }

}