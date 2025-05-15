package com.lifemates.controller;

import com.lifemates.model.Pet;
import com.lifemates.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    @Autowired
    private PetService petService;

    // Registrar una nueva mascota
    @PostMapping("/register")
    public ResponseEntity<?> registerPet(@RequestParam String name,
                                         @RequestParam String species,
                                         @RequestParam String breed,
                                         @RequestParam double weight,
                                         @RequestParam Long ownerId) {
        Pet pet = petService.registerPet(name, species, breed, weight, ownerId);
        return ResponseEntity.ok(pet);
    }

    // Obtener todas las mascotas
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Obtener una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
