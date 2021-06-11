package psk.phone.operator.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.PackageRepository;
import psk.phone.operator.service.PackageService;
import psk.phone.operator.transport.converter.PackageConverter;
import psk.phone.operator.transport.dto.PackageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/packages")
public class PackageRestController {

    private PackageService packageService;
    private PackageRepository packageRepository;

    @Autowired
    public PackageRestController(PackageService packageService, PackageRepository packageRepository) {
        this.packageService = packageService;
        this.packageRepository = packageRepository;
    }

    @GetMapping
    public ResponseEntity<List<PackageDto>> findAllPackages(){
       //List<PackageDto> allPackages = packageRepository.findAll().stream().map(PackageConverter::toDto).collect(Collectors.toList());
        //TODO !!!!!!!!!!!!!!!!!!!!!!!!! ZROBIC findALL nie działa bound of 3
       List<Package> packages = new ArrayList<>();
        for(int i  = 0; i < 9; i++)
        {
            packages.add(packageRepository.findById(1L).get());
        }
        List<PackageDto> collect = packages.stream().map(PackageConverter::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }


    @PutMapping("/{phoneNumber}/{aPackage}")
    public ResponseEntity<?> addPackageForNumber(@PathVariable PhoneNumber phoneNumber, @PathVariable Package aPackage){
        packageService.buyPackage(phoneNumber, aPackage);
        return ResponseEntity.ok().build();
    }
}
