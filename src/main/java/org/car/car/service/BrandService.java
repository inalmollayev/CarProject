package org.car.car.service;

import lombok.RequiredArgsConstructor;
import org.car.car.dto.BrandDto;
import org.car.car.model.Brand;
import org.car.car.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public List<BrandDto> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandDto> brandDtos = new ArrayList<>();
        for (Brand brand : brands) {
            brandDtos.add(modelMapper.map(brand, BrandDto.class));
        }
        return brandDtos;
    }

    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(new Brand());
        return modelMapper.map(brand, BrandDto.class);
    }

    public BrandDto addBrand(BrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        brand = brandRepository.save(brand);
        return modelMapper.map(brand, BrandDto.class);
    }

    public BrandDto updateBrand(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null) {
            brand = modelMapper.map(brandDto, Brand.class);
            brand.setId(id);
            brand = brandRepository.save(brand);
            return modelMapper.map(brand, BrandDto.class);
        }
        return new BrandDto();
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}

