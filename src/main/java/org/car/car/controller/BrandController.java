package org.car.car.controller;

import lombok.RequiredArgsConstructor;
import org.car.car.dto.BrandDto;
import org.car.car.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/get")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping("/add")
    public BrandDto addBrand(@RequestBody BrandDto brandDTO) {
        return brandService.addBrand(brandDTO);
    }

    @GetMapping("/id")
    public BrandDto getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PutMapping("/update")
    public BrandDto updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @DeleteMapping("/deledet")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }

}
