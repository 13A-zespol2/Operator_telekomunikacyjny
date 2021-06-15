package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.PackageRepository;
import psk.phone.operator.database.repository.PhoneNumberRepository;
import psk.phone.operator.service.PackageService;
import psk.phone.operator.transport.converter.PackageConverter;
import psk.phone.operator.transport.dto.PackageDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



/**
 * Klasa obsługująca widok ,,Packages". Zawiera metody obsługujące widok aplikacji wyświetlający pakiety do zakupu.
 */
@RestController
@RequestMapping("/packages")
public class PackageRestController {

    private PackageService packageService;
    private PackageRepository packageRepository;
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PackageRestController(PackageService packageService, PackageRepository packageRepository, PhoneNumberRepository phoneNumberRepository) {
        this.packageService = packageService;
        this.packageRepository = packageRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }


    /**
     * Metoda odpowiedzialna za znalezienie w bazie wszystkich możliwych pakietów.
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PackageDto>> findAllPackages() {
        List<PackageDto> allPackages = packageRepository.findAll().stream().map(PackageConverter::toDto).collect(Collectors.toList());
         return ResponseEntity.ok(allPackages);
    }


    /**
     * Metoda odbierające dane dotyczące zakupu pakietu dokonanego przez użytkownika. Dane przekazywane są do serwisu
     * odpowiedzialnego za dodanie pakietu.
     * @param number
     * @param packageName
     * @return
     */
    @PostMapping("/{number}/{packageName}")
    public ResponseEntity<?> addPackageForNumber(@PathVariable String number, @PathVariable String packageName) {

        Package byNamePackage = packageRepository.findByNamePackage(packageName);
        Optional<PhoneNumber> byNumber = phoneNumberRepository.findByNumber(number);
        packageService.buyPackage(byNumber.get(), byNamePackage);
        return ResponseEntity.ok().build();
    }
}
