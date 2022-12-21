package com.workshop.filmsApi.service;

import com.workshop.filmsApi.dto.DirectorDto;
import com.workshop.filmsApi.dto.DirectorFilmDto;
import com.workshop.filmsApi.entity.DirectorEntity;
import com.workshop.filmsApi.exception.DirectorNotFoundException;
import com.workshop.filmsApi.mapper.DirectorMapper;
import com.workshop.filmsApi.repository.DirectorRepository;
import com.workshop.filmsApi.request.DirectorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public List<DirectorFilmDto> getAll(){
        return directorRepository.findAll().stream()
                .map(DirectorMapper::toDirectorFilmDto)
                .collect(Collectors.toList());
    }

//    public DirectorEntity getById(Long id) throws Exception{
//        Optional<DirectorEntity> director = directorRepository.findById(id);
//
//        if(director.isEmpty()){
//            throw new Exception();
//        }
//        return director.get();
//    }

    public DirectorEntity getDirectorById(Long id){
        return directorRepository.findById(id)
                .orElseThrow(()-> new DirectorNotFoundException("Director with id " + id + " not found"));
    }

    public DirectorDto addDirector(DirectorRequest request){
        DirectorEntity directorEntity = directorRepository.save(DirectorMapper.toDirector(request));
        return DirectorMapper.toDTODirector(directorEntity);
    }

    public void deleteById(Long id){
        directorRepository.deleteById(id);
    }
}
