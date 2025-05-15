package com.lifemates.service;

import com.lifemates.model.Pet;
import com.lifemates.model.User;
import com.lifemates.repository.PetRepository;
import com.lifemates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    // Registrar una nueva mascota
    public Pet registerPet(String name, String species, String breed, double weight, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("El usuario propietario no existe."));

        Pet pet = new Pet();
        pet.setName(name);
        pet.setSpecies(species);
        pet.setBreed(breed);
        pet.setWeight(weight);
        pet.setOwner(owner);

        return petRepository.save(pet);
    }

    // Obtener todas las mascotas
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Obtener una mascota por ID
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    // Eliminar una mascota
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
