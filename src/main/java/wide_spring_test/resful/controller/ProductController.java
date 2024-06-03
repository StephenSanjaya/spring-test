package wide_spring_test.resful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wide_spring_test.resful.model.*;
import wide_spring_test.resful.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
        path = "/api/products",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces =  MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(@RequestBody CreateProductRequest request) {
        ProductResponse productResponse = productService.create(request);
        return WebResponse.<ProductResponse>builder().data(productResponse).message("Success create product").build();
    }

    @GetMapping(
            path = "/api/products/{id}",
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable("id") Long id) {
        ProductResponse productResponse = productService.get(id);
        return WebResponse.<ProductResponse>builder().data(productResponse).message("Success get product").build();
    }

    @PutMapping(
            path = "/api/products/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(@RequestBody UpdateProductRequest request,
                                               @PathVariable("id") Long id) {
        request.setId(id);
        ProductResponse productResponse = productService.update(request);
        return WebResponse.<ProductResponse>builder().data(productResponse).message("Success update product").build();
    }

    @DeleteMapping(
            path = "/api/products/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return WebResponse.<String>builder().message("Success delete product").build();
    }

    @GetMapping(
            path = "/api/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> search(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "type", required = false) String type,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "3") Integer size) {
        SearchProductRequest request = SearchProductRequest.builder()
                .page(page)
                .size(size)
                .name(name)
                .type(type)
                .build();

        Page<ProductResponse> contactResponses = productService.search(request);
        return WebResponse.<List<ProductResponse>>builder()
                .data(contactResponses.getContent())
                .message("Success get products")
                .paging(PagingResponse.builder()
                        .currentPage(contactResponses.getNumber())
                        .totalPage(contactResponses.getTotalPages())
                        .size(contactResponses.getSize())
                        .build())
                .build();
    }
}
